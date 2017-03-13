package me.web.framework.utils.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import me.web.framework.mvc.Entity;

/**
 * Table @Table column @Column fieldName
 * @author HoloStan
 *
 */
public class MapperSqlHelper {
	
	/**
	 * 根据实体类获取表名
	 * @param clazz
	 * @return
	 */
	public static <T extends Entity> String getTable(Class<T> clazz){
		String name = null;
		if(!clazz.isAnnotationPresent(Table.class)){
			name = clazz.getSimpleName();
		}else{
			Table table = clazz.getAnnotation(Table.class);
			if(StringUtils.isBlank(table.name())){
				name = clazz.getSimpleName();
			}else{
				name = table.name();
			}
		}
		return name;
	}

	/**
	 * 根据属性获取列名
	 * @param field
	 * @return
	 */
	public static String getColumn(Field field){
		String name = null;
		field.setAccessible(true);
		if(!field.isAnnotationPresent(Column.class)){
			name = field.getName();
		}else{
			Column column = field.getAnnotation(Column.class);
			if(StringUtils.isBlank(column.name())){
				name = field.getName();
			}else{
				name = column.name();
			}
		}
		return name;
	}
	
	public static String getFieldName(Field field){
		return "#{"+field.getName()+"}";
	}
	
	/**
	 * 根据属性获取对象的值
	 * @param field
	 * @param entity
	 * @return
	 */
	public static <T extends Entity> Object getValue(Field field,T entity){
		Object value = null;
		if (field != null) {
            try{
                if (field.isAccessible()) {
                	value = field.get(entity);
                } else {
                    field.setAccessible(true);
                    value = field.get(entity);
                    field.setAccessible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
		return value;
	}
	
	/**
	 * 验证值的有效性
	 * @param value
	 * @return
	 */
	public static boolean validate(Object value){
		return value != null;
	}
	
	/**
	 * 根据实体类获取列名清单
	 * @param clazz
	 * @return
	 */
	public static <T extends Entity> List<String> getColumns(Class<T> clazz){
		List<String> columns = null;
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length > 0){
			columns = new ArrayList<String>();
		}else{
			throw new IllegalArgumentException(clazz.getName()+":获取列名失败，未找到有效的列");
		}
		for (Field field : fields) {
			columns.add(MapperSqlHelper.getColumn(field));
		}
		return columns;
	}
	
	public static <T extends Entity> List<String> getFieldNames(Class<T> clazz){
		List<String> fieldNames = null;
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length > 0){
			fieldNames = new ArrayList<String>();
		}else{
			throw new IllegalArgumentException(clazz.getName()+":获取属性名失败，未找到有效的属性");
		}
		for (Field field : fields) {
			fieldNames.add("#{"+field.getName()+"}");
		}
		return fieldNames;
	}
	
	public static <T extends Entity> Map<String,String> getColumnsAndFieldNames(T entity,boolean validate){
		Map<String,String> columnsAndFieldNames = null;
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length > 0){
			columnsAndFieldNames = new HashMap<String,String>();
		}else{
			throw new IllegalArgumentException(clazz.getName()+":未找到有效的列");
		}
		for (Field field : fields) {
			String column = MapperSqlHelper.getColumn(field);
			String fieldName = MapperSqlHelper.getFieldName(field);
			if(!validate){
				Object value = MapperSqlHelper.getValue(field, entity);
				if(!validate(value)){
					continue;
				}
			}
			columnsAndFieldNames.put(column, fieldName);
		}
		return columnsAndFieldNames;
	}

	/**
	 * 解析实体对象，根据列名和值形成键值关系
	 * @param validate [true]返回所有列 [false]过滤null列
	 * @return
	 */
	public static <T extends Entity> Map<String,Object> getValues(T entity,boolean validate){
		Map<String,Object> values = null;
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length > 0){
			values = new HashMap<String,Object>();
		}else{
			throw new IllegalArgumentException(clazz.getName()+":获取列名失败，未找到有效的列");
		}
		for (Field field : fields) {
			String name = MapperSqlHelper.getColumn(field);
			Object value = MapperSqlHelper.getValue(field, entity);
			if(!validate && validate(value)){
				continue;
			}
			values.put(name, value);
		}
		return values;
	}
	
	public static <T extends Entity> Map<String,Object> getValues(T entity){
		return MapperSqlHelper.getValues(entity, true);
	}
	
}
