package me.web.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.web.demo.entity.Demo;
import me.web.demo.service.DemoService;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	@RequestMapping("/demo")
	public void add(Demo d){
		service.insert(d);
	}

}
