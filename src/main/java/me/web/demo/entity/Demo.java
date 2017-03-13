package me.web.demo.entity;

import javax.persistence.Table;

import me.web.framework.mvc.Entity;

@Table(name="user_tmp")
public class Demo extends Entity{
	
	Integer id;
	String uuid;
	String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
