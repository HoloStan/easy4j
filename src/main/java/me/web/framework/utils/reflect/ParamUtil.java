package me.web.framework.utils.reflect;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
/**
 * 获取指定方法的参数的「形式名称」，该名称在编译后发布版本获取【可能失效】，慎用！！！
 * @author HooRaGon
 * @need spring-core.jar;
 *       spring-asm.jar;
 *       org.apache.commons.logging.jar;
 */
public class ParamUtil {

    private static final ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    // NOTE might go wrong if missing debug symbols
    /**
     * return all parameter alias for the method
     * @param method
     * @return
     */
    public static String[] getParameterNames(final Method method) {
        return parameterNameDiscoverer.getParameterNames(method);
    }

    public static Class<?>[] getParameterTypes(Method method){
        return method.getParameterTypes();
    }
    
}
