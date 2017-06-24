package org.liufeng.weixin.util;

public class RandomUtil {

	//生成随机数
	public static String Random(int num ){

		String a = "123456789abcdefghijkmnpqrstuvwxyz";
		char[] rands = new char[num];
		for (int i = 0; i < rands.length; i++)
		{
			int rand = (int) (Math.random() * a.length());
			rands[i] = a.charAt(rand);
		}
		String serial="";
		for(int i=0;i<rands.length;i++){
			serial+=""+rands[i];
		}
		return serial;
	}
}
