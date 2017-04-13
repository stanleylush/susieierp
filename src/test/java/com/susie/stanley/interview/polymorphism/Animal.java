package com.susie.stanley.interview.polymorphism;

public class Animal {
	// 类加载时，4种方法块执行顺序为：静态块static{}、非静态块{}、构造函数、synchronized
	int i = 0;
	static int is = 0;
	Integer ier = new Integer(0);
	static Integer iers = new Integer(0);
	String str = "";
	static String strs = "";
	// 1、静态块
	static{
		System.out.println("animal静态块static{}");
		is = 1;
		iers = new Integer(1);
		strs = "1";
		System.out.println("animal is="+is+";iers="+iers+";strs="+strs);
	}
	// 2、非静态块
	{
		System.out.println("animal非静态块{}");
		i = 2;
		is = 2;
		ier = new Integer(2);
		iers = new Integer(2);
		str = "2";
		strs = "2";
		System.out.println("animal i="+i+";is="+is+";ier="+ier+";iers="+iers+";str="+str+";strs="+strs);
	}
	// 3、构造函数
	public Animal(){
		System.out.println("animal构造函数");
		i = 3;
		is = 3;
		ier = new Integer(3);
		iers = new Integer(3);
		str = "3";
		strs = "3";
		System.out.println("animal i="+i+";is="+is+";ier="+ier+";iers="+iers+";str="+str+";strs="+strs);
	}
	// 4、普通方法
	public void run(){
		System.out.println("animal run");
		i = 4;
		is = 4;
		ier = new Integer(4);
		iers = new Integer(4);
		str = "4";
		strs = "4";
		System.out.println("animal i="+i+";is="+is+";ier="+ier+";iers="+iers+";str="+str+";strs="+strs);
	}
}
