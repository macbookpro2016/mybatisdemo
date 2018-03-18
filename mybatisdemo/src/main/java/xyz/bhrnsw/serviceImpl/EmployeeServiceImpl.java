package xyz.bhrnsw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import redis.clients.jedis.exceptions.JedisConnectionException;
import xyz.bhrnsw.beans.Employee;
import xyz.bhrnsw.mapper.EmployeeMapper;
import xyz.bhrnsw.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeMapper em;
	@Autowired
	private JedisConnectionFactory jcf;
	public Employee getInformationById(Integer id) {
		Employee e = null;
		JedisConnection con = null;
		try{
			con = jcf.getConnection();
			RedisSerializer<Object> rs = new JdkSerializationRedisSerializer();
			e = (Employee) rs.deserialize(con.get(rs.serialize(id)));
			System.out.println("查询redis");
			if(e == null){
				System.out.println("从mybatis查询");
				e = em.selectByPrimaryKey(id);
			}

		}catch(JedisConnectionException ex){
			ex.printStackTrace();
		}finally{
			if(con != null){
				con.close();
			}
		}
		
		return e;
		
	}
	
}
