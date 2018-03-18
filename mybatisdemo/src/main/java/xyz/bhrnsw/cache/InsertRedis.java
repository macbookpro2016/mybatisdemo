package xyz.bhrnsw.cache;

import java.util.Date;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import xyz.bhrnsw.beans.Employee;

public class InsertRedis {
	public static void insert(Employee e){
		Integer empno = e.getEmpNo();
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.ping());
		RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
		jedis.set(rs.serialize(empno), rs.serialize(e));
		System.out.println("添加"+empno+"成功");
	}
	public static void main(String[] args) {
		Employee e = new Employee();
		e.setEmpNo(10001);
		e.setLastName("Facello");
		insert(e);
	}
}	
