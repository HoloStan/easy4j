package me.web.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.web.demo.dao.DemoDao;
import me.web.demo.entity.Demo;
import me.web.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private DemoDao dao ;

	public void insert(Demo d) {
		dao.insert(d);
	}
	
	

}
