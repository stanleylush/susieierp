package com.susie.ierp.cache;

/**
 * 下面就用一个简单的例子测试刚才实现的分布式锁。 
 * 例子中使用50个线程模拟秒杀一个商品，使用--运算符来实现商品减少，从结果有序性就可以看出是否为加锁状态。
 * @author lu
 */
public class DistibutedLockTest {
	public static void main(String[] args) { 
		DistibutedLockService service = new DistibutedLockService(); 
		for (int i = 0; i < 50; i++) { 
			DistibutedLockThread thread = new DistibutedLockThread(service); 
			thread.start(); 
		} 
	}
}
