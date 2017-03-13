package me.web.framework.utils.reflect;

import java.lang.reflect.Field;

/**
 * 包含对象属性参数的处理，可以查询，修改，获取目标对象的指定属性
 * @author HooRaGon
 *
 */
public class FieldUtil {
    
    /**
     * 获取指定对象的指定属性Field，如果该对象上未找到则会去其父类寻找，直到最后仍然未找到则抛出NoSuchFielException
     * @param object 目标对象
     * @param name 属性名
     * @return
     */
    public static Field getField(Object object, String name) {
        Field field = null;
        try {
            if (object != null) {
                if(object instanceof Class){
                    field = ((Class<?>) object).getDeclaredField(name);
                }else{
                    field = object.getClass().getDeclaredField(name);
                }
            }
        } catch (NoSuchFieldException e) {
            Class<?> clazz = object.getClass().getSuperclass();
            if(clazz != Object.class){
                field = getField(clazz, name);
            }else{
                try {
                    throw new NoSuchFieldException();
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return field;
    }
    
    /**
     * 获取指定对象的指定属性的值，如果该对象上未找到则会去其父类寻找，直到最后仍然未找到则抛出NoSuchFielException
     * @param obj 目标对象
     * @param name 属性名
     * @return
     */
    public static Object getFieldValue(Object obj, String name) {
        Object o = null;
        Field field = getField(obj, name);
        if (field != null) {
            try{
                if (field.isAccessible()) {
                    o = field.get(obj);
                } else {
                    field.setAccessible(true);
                    o = field.get(obj);
                    field.setAccessible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return o;
    }
    
    /**
     * 设定指定对象的指定属性的值，如果该对象上未找到则会去其父类寻找，直到最后仍然未找到则抛出NoSuchFielException
     * @param obj 目标对象
     * @param name 属性名
     * @param value 属性值
     * @return
     */
    public static void setFieldValue(Object obj,String name,Object value){
        Field field = getField(obj, name);
        if (field != null) {
            try{
                if (field.isAccessible()) {
                    field.set(obj, value);
                } else {
                    field.setAccessible(true);
                    field.set(obj, value);
                    field.setAccessible(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
