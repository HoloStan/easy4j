package me.web.framework.utils.string;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by lxu on 6/28/2016.
 */
public class ResourceServiceUtil {
    public static String getMessage(String key, Locale locale){
        ResourceBundle labels = ResourceBundle.getBundle("messages/messages", locale);
        String result="";
        try {
            result = labels.getString(key);
        } catch (Exception e) {
            return result;
        }
        try {
            //as getBundle will get the message with "ISO-8859-1" code by default, it may be cause messy code, need translate it to UTF-8
            return new String(result.getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception e) {
            return result;
        }
    }
}
