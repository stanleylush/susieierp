package com.susie.ierp.cache;

/**
 * 模拟线程进行秒杀服务
 * @author lu
 */
public class DistibutedLockThread extends Thread { 
	
	private DistibutedLockService service; 
	
	public DistibutedLockThread(DistibutedLockService service) { 
		this.service = service; 
	} 
	@Override 
	public void run() { 
		service.seckill(); 
	} 
}