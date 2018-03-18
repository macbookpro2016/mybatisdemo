package xyz.bhrnsw.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xyz.bhrnsw.beans.User;
import xyz.bhrnsw.mapper.TestDao;
import xyz.bhrnsw.service.TestService;
@Service
public class TestServiceImpl implements TestService{
	@Resource
	private TestDao td;
	@Override
	public User test() {
		User u = td.Test();
		return u;
	}
	
}
