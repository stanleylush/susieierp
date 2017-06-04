package com.susie.stanley.learn.javadoc;

import org.junit.Test;

public class TestInteger {
	
	@Test
	public void testIntegerEquals(){
		Integer i = new Integer(5);
		Integer j = new Integer(5);
		System.out.println(i == j);
		System.out.println(i.equals(j));
		System.out.println(i.MAX_VALUE);
	}
	
	/**
	 * 自动装箱、自动拆箱
	 */
	@Test
	public void testInteger(){
		Integer a = 100; // 自动装箱
		Integer a1 = Integer.valueOf(100);
		int b = a; // 自动拆箱
		int b1 = a1.intValue();
	}
	
	/**
	 * 动装箱时的缓存问题
	 * public static Integer valueOf(int i) {
     *   assert IntegerCache.high >= 127;
     *   if (i >= IntegerCache.low && i <= IntegerCache.high)
     *       return IntegerCache.cache[i + (-IntegerCache.low)];
     *   return new Integer(i);
     * }
     * IntegerCache.high可以在properties文件中设置
	 */
	@Test
	public void testInteger2(){
		Integer a = 127;
		Integer b = 127;
		Integer c = 128;
		Integer d = 128;
		System.out.println(a==b);
		System.out.println(c==d);
	}

}
