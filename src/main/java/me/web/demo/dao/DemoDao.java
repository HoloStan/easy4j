package me.web.demo.dao;

import org.springframework.stereotype.Repository;

import me.web.demo.entity.Demo;
import me.web.framework.mvc.Dao;

@Repository
public interface DemoDao extends Dao<Demo, Integer>{

}
