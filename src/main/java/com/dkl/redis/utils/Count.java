package com.dkl.redis.utils;

/*
 * 计算redis数据丢失概率
 */
public class Count {

	public static void main(String[] args) {
		int a = 18585967+18588713+18585967;
		int b = 100000000 - a;
		System.out.println("a = " + a);
		System.out.println((double)b*1000000/36914045);
		System.out.println(100/2.8);
		System.out.println(16.0/71);
	}

}
