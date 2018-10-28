package cn.buy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtils {
	
	public static String emailReg = "^\\w+\\@\\w+(\\.[a-zA-Z0-9]{2,3}){1,2}$";
	public static String mobileReg = "^\\d{11}$";
	public static String identityCodeReg = "^\\w{18}$";

	
	
	public static boolean checkEmail(String email){
		Pattern pattern = Pattern.compile(emailReg);//模板，创建规范
		Matcher matcher = pattern.matcher(email);//匹配
		boolean matches = matcher.matches();//匹配成功返回true,否则返回false
		return matches;
	}
	
	public static boolean checkMobile(String mobile){
		Pattern pattern = Pattern.compile(mobileReg);//模板，创建规范
		Matcher matcher = pattern.matcher(mobile);//匹配
		boolean matches = matcher.matches();//匹配成功返回true,否则返回false
		return matches;
	}
	
	public static boolean checkIdentityCode(String identityCode){
		Pattern pattern = Pattern.compile(identityCodeReg);//模板，创建规范
		Matcher matcher = pattern.matcher(identityCode);//匹配
		boolean matches = matcher.matches();//匹配成功返回true,否则返回false
		return matches;
	}
	
	public static void main(String[] args) {
	//	System.out.println(checkEmail("magic63.com"));
	
	System.out.println(checkIdentityCode("123456654321123456"));
	}
	
}
