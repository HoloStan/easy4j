package me.web.framework.utils.string;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	public static String parseList(List<?> list){
		return parseList(list,",");
	}
	public static String parseList(List<?> list,String separate){
		StringBuffer buffer = null;
		if(list != null){
			buffer = new StringBuffer();
		}else{
			return null;
		}
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			buffer.append(object);
			if(iterator.hasNext()){
				buffer.append(separate);
			}
		}
		return buffer.toString();
	}

}
