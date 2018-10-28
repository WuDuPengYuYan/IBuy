package cn.buy.utils;

import java.util.Collection;
import java.util.Map;

//判断是否是空的工具类
public class EmptyUtils {
	//判断空
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}
		//字符  字符串  数组  集合  map
		if(obj instanceof CharSequence){
			return ((CharSequence) obj).length() == 0;
		}
		if(obj instanceof Collection){
			return ((Collection)obj).isEmpty();
		}
		if(obj instanceof Map){
			return ((Map)obj).isEmpty();
		}
		if(obj instanceof Object[]){
			Object[] object = (Object[])obj;
			if(object.length == 0){
				return true;
			}
		}
		return false;
	}
	//判断非空
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	
}
