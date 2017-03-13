package me.web.framework.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 指明拦截器拦截的类和方法名 将拦截StatementHandler中参数类型为Connection的prepare方法
 */
// 只拦截select部分
@Intercepts({
  @Signature(
      type = Executor.class,
      method = "query",
      args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
  ,@Signature(
      type = Executor.class,
      method = "update",
      args = {MappedStatement.class, Object.class})
})
public class GenericPlugin implements Interceptor {

	public Object intercept(Invocation ivk) throws Throwable {
		//获取MappedStatement和parameter
//		MappedStatement mappedStatement = (MappedStatement) ivk.getArgs()[0];
//		Object parameter = ivk.getArgs()[1];
		
		//进入下级链
		return ivk.proceed();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties properties) {
//		pageList = properties.getProperty("pageList");
	}

}
