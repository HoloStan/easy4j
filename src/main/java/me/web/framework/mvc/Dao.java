package me.web.framework.mvc;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;

import me.web.framework.utils.mapper.MapperProvider;

/**
 * 通用DAO/Mapper接口，为数据库提供通用CRUD操作
 * @author HoloStan
 */
public interface Dao<T extends Entity,PK extends Serializable> {
	
	/**
     * 新增(不会将序列生成的ID,注入)
     * 效率较save(T t)高
     * @param t
     */
	@InsertProvider(type = MapperProvider.class,method="insert")
    void insert(T entity);
    
    /**
     * 批量新增(不会将序列生成的ID,注入)
     * 效率较saveOfBatch(List<T> tList)高
     * @param tList
     */
	@InsertProvider(type = MapperProvider.class,method="batchInsert")
    void batchInsert(List<T> entities);
    
    /**
     * 新增(会将序列生成的ID,注入)
     * @param t
     */
    void save(T t);
    
    /**
     * 批量新增(会将序列生成的ID,注入)
     * @param tList
     */
    void batchSave(List<T> tList);
    
    /**
     * 根据ID进行删除
     * @param id
     */
    void delete(PK id);
    
    /**
     * 根据ids进行批量删除
     * @param ids
     */
    void batchDelete(List<PK> ids);
    
    /**
     * 更新,字段为空，则不进行更新
     * @param t
     */
    void update(T t);
    
    /**
     * 批量更新
     * @param tList
     */
    void batchUpdate(List<T> tList);
    
    /**
     * 根据ID获取对象
     * @param id
     * @return
     */
    T findOneById(PK id);
    
    /**
     * 获取所有的对象
     * @return
     */
    List<T> findAll();
    
    /**
     * 获取记录数
     * @return
     */
    Long count();
    
    void pageList();

}
