package xyz.bhrnsw.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCache implements Cache{
	private static JedisConnectionFactory jcf;
	private final ReadWriteLock rwl = new ReentrantReadWriteLock();
	private final String id;
	
	public RedisCache(){
		id = null;
	}
	public RedisCache(final String id){
		if(id == null){
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}
	
	public void clear() {
		JedisConnection con = null;
		try{
			con = jcf.getConnection();
			//清除当前db下的数据
			con.flushDb();
			//清除所有db数据
			con.flushAll();
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				con.close();
			}
		}
		
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(Object obj) {
		JedisConnection con = null;
		Object result = null;
		try{
			con = jcf.getConnection();
			RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
			//利用反序列化获取值
			result = rs.deserialize(con.get(rs.serialize(obj)));
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				con.close();
			}
		}
		
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.rwl;
	}

	@Override
	public int getSize() {
		int result = 0;
		JedisConnection con = null;
		try{
			con = jcf.getConnection();
			result = Integer.valueOf(con.dbSize().toString());
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				con.close();
			}
		}
		
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		// TODO Auto-generated method stub
		JedisConnection con = null;
		try{
			con = jcf.getConnection();
			RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
			con.set(rs.serialize(key), rs.serialize(value));
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			if(con != null){
				con.close();
			}
		}
	}

	//通过将该key的过期时间设置为0来删除该元素
	public Object removeObject(Object key) {
		JedisConnection con = null;
		Object result = null;
		try{
			con = jcf.getConnection();
			RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
			result = con.expire(rs.serialize(key), 0);
		}catch(JedisConnectionException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return result;
	}
	
}
