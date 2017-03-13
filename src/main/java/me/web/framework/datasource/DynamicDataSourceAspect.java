package me.web.framework.datasource;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import me.web.common.annotation.DataSource;
import me.web.common.enums.DataSources;

/**
 * 通过spring-aop机制拦截所有DAO中的方法
 * 根据@DataSource注解设置方法默认的dataSource
 * 未指定dataSource则默认选择dataSource数据源
 * @author HooRaGon 2016-04-13
 *
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

	/**
	 * 拦截所有dao中的方法和注解datasource方法
	 * @param point
	 */
	@Before("execution(public * me.web..dao.*.*(..)) || @annotation(me.web.common.annotation.DataSource)")
	public void setDynamicDataSource(JoinPoint point){
		//处理逻辑 ，根据具体的境况选取适合的DataSource
		String dataSource = readDataSource(point).getValue();
		//使用@DataSource
		RoutingContextHolder.setContext(dataSource);
		
	}

	/**
	 * 读取目标方法上的DataSource注解,如果没有则默认返回DATA_SOURCE_ONE
	 * @param point
	 * @return
	 */
	private DataSources readDataSource(JoinPoint point) {
		//获取方法上的注解集合
		Annotation[] annotations = ((MethodSignature)point.getSignature()).getMethod().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof DataSource) {
            	DataSource dataSource = (DataSource) annotation;
            	return dataSource.value();
            }
        }
        return DataSources.DATA_SOURCE_ONE;
	}
	
}
