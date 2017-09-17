package com.susie.stanley.learn.javadoc;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestInteger {

	@Test
	public void testIntegerEquals() {
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
	public void testInteger() {
		Integer a = 100; // 自动装箱，相当于 Integer.valueOf(100);
		Integer a1 = Integer.valueOf(100);
		int b = a; // 自动拆箱，相当于 a1.intValue();
		int b1 = a1.intValue();
	}

	/**
	 * 动装箱时的缓存问题 public static Integer valueOf(int i) { assert IntegerCache.high
	 * >= 127; // 断言Integer缓存最大值>=127 if (i >= IntegerCache.low && i <=
	 * IntegerCache.high) // 1、如果输入参数在Integer缓存范围内 return IntegerCache.cache[i +
	 * (-IntegerCache.low)]; // 2、取缓存中的值 return new Integer(i); // 否则new一个值 }
	 * IntegerCache.high可以在properties文件中设置
	 */
	@Test
	public void testInteger2() {
		Integer a = 127;
		Integer b = 127;
		Integer c = 128;
		Integer d = 128;
		System.out.println(a == b); // ab都是从缓存中取的值，所以==为true
		System.out.println(c == d); // 超过了缓存最大值，c、d 是两个对象，所以==为false
	}

	@Test
	public void testIsNotBlank() {
		System.out.println(StringUtils.isNotBlank("   "));
		System.out.println(StringUtils.isNotEmpty("   "));
	}

	@Test
	public void testChar(){
		String str = "ABCD";
		String xxnr = "DCBA";// ABDC
		char[] cs = str.toCharArray();
		int pxxmgs = cs.length;//排序项目个数
		int tmxfs = 0;
		for(int i=0; i<cs.length; i++){
			int xxgzs = pxxmgs - (i+1);//对应选项规则数
			int xxnrwz = xxnr.indexOf(cs[i]);//正确选项在提交答案内容的位置
			int tmxf = xxgzs-(xxnrwz<=i?0:xxnrwz-i); // 选项对应题目小分=正确选项内容位置-对应选项规则数
			System.out.println(cs[i]+" 规则数: "+xxgzs+"； 原位置："+ i +"； 选项内容位置："+ xxnrwz+"; 题目小分："+tmxf);
			if(tmxf > 0){
				tmxfs = tmxfs+tmxf;
			}
		}
		System.out.println(tmxfs);
	}

	@Test
	public void test2() {
		String tkda = "ABCD";
		String xxnr = "DCBA";// ABDC
		Double tmf = new Double(0);
		char[] cs = tkda.toCharArray();
		int pxxmgs = cs.length;// 排序项目个数
		int sdxf = 0;// 实得小分
		int gzzs = 0;// 规则总数
		for (int i = 0; i < cs.length; i++) {// i为正确选项的位置
			int gzs = pxxmgs - (i + 1);// 对应选项规则数
			gzzs = gzzs + gzs;
			int xxnrwz = xxnr.indexOf(cs[i]);// 正确选项内容的位置
			int tmxf = 0; // 题目小分
			for(int j=xxnrwz+1;j<xxnr.length();j++){
				int tempXxnrwz = xxnr.indexOf(cs[j]);// 正确选项内容后面选项的位置
				if(i<tempXxnrwz){
					tmxf = tmxf + 1;
				}
				System.out.println("    选项："+cs[j]+"；内容位置："+tempXxnrwz);
			}
			if (tmxf > 0) {// 题目分数大于0时，计入实得小分
				sdxf = sdxf + tmxf;
			}
			System.out.println(cs[i]+" 规则数: "+gzs+"； 原位置："+ i +"； 选项内容位置："+ xxnrwz+"; 题目小分："+tmxf);
		}
		System.out.println(sdxf);
		if (gzzs > 0) {
			tmf = (double) (sdxf / gzzs);
		}
		//return tmf;
	}
	
	@Test
	public void test3() {
		String tkda = "ABCD";
		String xxnr = "BADC";// ABCD BADC ABDC DCBA
		Double tmf = new Double(0);
		char[] cs = tkda.toCharArray();
		char[] xxnrCs = xxnr.toCharArray();
		int pxxmgs = cs.length;// 排序项目个数
		int sdxf = 0;// 实得小分
		int gzzs = 0;// 规则总数
		for (int i = 0; i < cs.length; i++) {// i为正确选项的位置
			int gzs = pxxmgs - (i + 1);// 对应选项规则数
			gzzs = gzzs + gzs;
			int xxnrwz = xxnr.indexOf(cs[i]);// 正确选项内容的位置
			int tmxf = 0; // 题目小分
			for(int j=xxnrwz+1;j<xxnr.length();j++){
				int tempTkdawz = tkda.indexOf(xxnrCs[j]);// 正确选项内容后面选项的位置
				int tempXxnrwz = xxnr.indexOf(xxnrCs[j]);// 正确选项内容后面选项的位置
				int tempTmxf = 0;
				if(i<tempTkdawz && i<tempXxnrwz){
					tempTmxf = 1;
					tmxf = tmxf + 1;
				}
				System.out.println("    选项："+xxnrCs[j]+"；的位置："+tempXxnrwz+"；得分："+tempTmxf);
			}
			if (tmxf > 0) {// 题目分数大于0时，计入实得小分
				sdxf = sdxf + tmxf;
			}
			System.out.println(cs[i]+" 规则数: "+gzs+"； 原位置："+ i +"； 选项内容位置："+ xxnrwz+"; 题目小分："+tmxf);
		}
		System.out.println(sdxf);
		if (gzzs > 0) {
			tmf = (double) (sdxf / gzzs);
		}
		//return tmf;
	}
	
	@Test
	public void testString(){
		String a = "a";
		System.out.println(a.split(",").length);
	}
}
