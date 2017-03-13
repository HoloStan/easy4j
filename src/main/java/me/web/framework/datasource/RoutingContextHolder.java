package me.web.framework.datasource;

/**
 *  用来存储路由到指定tx的Context
 * 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class RoutingContextHolder<T> {
  
private static final ThreadLocal contextHolder = new ThreadLocal();

public static <T> void setContext(T context) {
    contextHolder.set(context);
  }

  public static <T> T getContext() {
    return (T) contextHolder.get();
  }
  
  public static void clearContext() {  
      contextHolder.remove();  
  }  
}