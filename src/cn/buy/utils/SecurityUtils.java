package cn.buy.utils;

import org.apache.commons.codec.digest.DigestUtils;

//加密
public class SecurityUtils {

	//MD5
	public static String md5Hex(String val){
		return DigestUtils.md5Hex(val);
	}
	
	//3次MD5操作
	public static String md5Hex3(String val){
		for (int i = 0; i < 3; i++) {
			val = md5Hex(val);
		}
		return val;
	}
	
	/*public static void main(String[] args) {
		System.out.println(md5Hex3("1234564543543"));
		System.out.println("c56d0e9a7ccec67b4ea131655038d604".length());
	}
	*/
	
}
