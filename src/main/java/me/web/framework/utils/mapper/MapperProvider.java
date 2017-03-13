package me.web.framework.utils.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import me.web.framework.mvc.Entity;
import me.web.framework.utils.string.StringUtil;

public class MapperProvider {
	
	private Logger logger = Logger.getLogger(MapperProvider.class);
	
	public <T extends Entity> String insert(T entity){
		Class<? extends Entity> clazz = entity.getClass();
		StringBuffer sqlBuffer = new StringBuffer("INSERT INTO ");
		String tableName = MapperSqlHelper.getTable(clazz);
		//解析实体
		Map<String, String> columnsAndFieldNames = MapperSqlHelper.getColumnsAndFieldNames(entity, false);
		List<String> columns = new ArrayList<String>();
		List<Object> fieldNames = new ArrayList<Object>();
		for (Entry<String, String> entry : columnsAndFieldNames.entrySet()) {
			columns.add(entry.getKey());
			fieldNames.add(entry.getValue());
		}
		//拼接表名，可通过[JPA]@Table(name="")注解，默认使用类名
		//拼接列名
		//拼接值
		sqlBuffer.append(tableName).append(" ( ").append(StringUtil.parseList(columns)).append(" ) VALUES ( ").append(StringUtil.parseList(fieldNames)).append(" ) ");
		logger.debug(sqlBuffer);
		return sqlBuffer.toString();
	}

	public String batchInsert(List<Entity> entities) {
		return "";
		
	}

	public String save(Entity t) {
		return "";
		
	}

	public String batchSave(List<Entity> tList) {
		return "";
		
	}

	public String delete(Integer id) {
		return "";
		
	}

	public String batchDelete(List<Integer> ids) {
		return "";
		
	}

	public String update(Entity t) {
		return "";
		
	}

	public String batchUpdate(List<Entity> tList) {
		return "";
		
	}

	public String findOneById(Integer id) {
		return "";
	}

	public String findAll() {
		return "";
	}

	public String count() {
		return "";
	}

	public String pageList() {
		return "";
		
	}

}
