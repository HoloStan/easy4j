package me.web.framework.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型工具类
 * @author HooRaGon
 *
 */
public class GenericsUtil {
    
    /**
     * 获取指定类的父类的泛型
     * @return
     */
    public static Class<?>[] getSuperClassGenricType(Class<?> clazz){
        Type type = clazz.getGenericSuperclass();// 得到泛型父类
        // 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
        return analysType(type);
    }
    
    /**
     * 获取指定类的父类的泛型的第index个
     * @return
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz,int index){
        return getSuperClassGenricType(clazz)[index];
    }
    
    /**
     * 获取指定方法的返回值的泛型
     * @return
     */
    public static Class<?>[] getMethodReturnGenricType(Method method){
        Type type = method.getGenericReturnType(); 
        return analysType(type);
    }
    /**
     * 获取指定方法的返回值的泛型的第index个
     * @return
     */
    public static Class<?> getMethodReturnGenricType(Method method,int index){
        return getMethodReturnGenricType(method)[index];
    }
    
    /**
     * 获取指定方法的参数的泛型
     * @return
     */
    public static Class<?>[] getMethodParamGenricType(Method method){
        Type[] typeArray = method.getGenericParameterTypes();
        List<Class<?>> list = new ArrayList<Class<?>>();
        for (Type type : typeArray) {
            for (Class<?> c : analysType(type)) {
                list.add(c);
            }
        }
        return (Class<?>[]) list.toArray();
    }
    
    /**
     * 获取指定方法的第index个参数的泛型
     * @return
     */
    public static Class<?>[] getMethodParamGenricType(Method method,int index){
        Type[] typeArray = method.getGenericParameterTypes();
        Type type = typeArray[index];
        return analysType(type);
    }
    
    /**
     * 获取指定属性的泛型，如Map<V,K>
     * @return
     */
    public static Class<?>[] getFieldGenericType(Field field){
        Type type = field.getGenericType();
        return analysType(type);
    }
    /**
     * 获取指定属性的泛型的第index个，如Map<V,K>
     * @return
     */
    public static Class<?> getFieldGenericType(Field field,int index){
        return getFieldGenericType(field)[index];
    }
    
    /**
     * 分析Type的泛型类，如果不存在返回[Object.class]
     * @param type
     * @return
     */
    private static Class<?>[] analysType(Type type){
        if (!(type instanceof ParameterizedType)) {
            return new Class<?>[]{Object.class};  
        } 
        Type[] params = ((ParameterizedType) type).getActualTypeArguments(); 
        Class<?>[] clazzArray = new Class<?>[params.length];
        int index = 0;
        for (Type t : params) {
            if(t instanceof Class){
                clazzArray[index++] = (Class<?>) t;
            }else{
                clazzArray[index++] = Object.class;
            }
        }
        return clazzArray;
    }
    
    public List<String> get(List<String> list){
    	return null;
    }
    
    public static void main(String[] args) {
		Method[] methods = GenericsUtil.class.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().contains("get")){
				System.out.println(getMethodReturnGenricType(method));
			}
		}
	}
}
