package com.susie.ierp.cache;

import java.util.List;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

/**
 * redis分布式锁实现思想
 * 1、获取锁的时候，使用setnx加锁，并使用expire命令为锁添加一个超时时间，超过该时间则自动释放锁，
 * 	锁的value值为一个随机生成的UUID，通过此在释放锁的时候进行判断。
 * 2、获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁。
 * 3、释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放。
 * @author lu
 */
public class DistributedLock {
	private final JedisPool jedisPool; 
	
	public DistributedLock(JedisPool jedisPool) { 
		this.jedisPool = jedisPool; 
	} 
	/** 
	 * 加锁 
	 * @param locaName 锁的key 
	 * @param acquireTimeout 获取超时时间 
	 * @param timeout 锁的超时时间 
	 * @return 锁标识 
	 */ 
	public String lockWithTimeout(String locaName, long acquireTimeout, long timeout) { 
		Jedis conn = null; 
		String retIdentifier = null; 
		try { 
			// 获取连接 
			conn = jedisPool.getResource(); 
			// 随机生成一个value 
			String identifier = UUID.randomUUID().toString(); 
			// 锁名，即key值 
			String lockKey = "lock:" + locaName; 
			// 超时时间，上锁后超过此时间则自动释放锁 
			int lockExpire = (int)(timeout / 1000); 
			// 获取锁的超时时间，超过这个时间则放弃获取锁 
			long end = System.currentTimeMillis() + acquireTimeout; 
			
			while (System.currentTimeMillis() < end) { 
				// SETNX key val 当且仅当key不存在时，set一个key为val的字符串，返回1；若key存在，则什么都不做，返回0。
				if (conn.setnx(lockKey, identifier) == 1) { 
					// expire key timeout 为key设置一个超时时间，单位为second，超过这个时间锁会自动释放，避免死锁。
					conn.expire(lockKey, lockExpire); 
					// 返回value值，用于释放锁时间确认 
					retIdentifier = identifier; 
					return retIdentifier; 
				} 
				// 返回-1代表key没有设置超时时间，为key设置一个超时时间 
				if (conn.ttl(lockKey) == -1) { 
					conn.expire(lockKey, lockExpire); 
				} 
				try { 
					Thread.sleep(10); 
				} catch (InterruptedException e) { 
					Thread.currentThread().interrupt(); 
				} 
			} 
		} catch (JedisException e) { 
			e.printStackTrace(); 
		} finally { 
			if (conn != null) { 
				conn.close(); 
			} 
		} 
		return retIdentifier; 
	} 
	
	/** 
	 * * 释放锁 
	 * * @param lockName 锁的key 
	 * * @param identifier 释放锁的标识 
	 * * @return 
	 */ public boolean releaseLock(String lockName, String identifier) { 
		 Jedis conn = null; 
		 String lockKey = "lock:" + lockName; 
		 boolean retFlag = false; 
		 try { 
			 conn = jedisPool.getResource(); 
			 while (true) { 
				 // 监视lock，准备开始事务 
				 conn.watch(lockKey); 
				 // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁 
				 if (identifier.equals(conn.get(lockKey))) { 
					 Transaction transaction = conn.multi(); 
					 // delete key 删除key
					 transaction.del(lockKey); 
					 List<Object> results = transaction.exec(); 
					 if (results == null) { 
						 continue; 
					 } 
					 retFlag = true; 
				 } 
				 conn.unwatch(); 
				 break; 
			 } 
		 } catch (JedisException e) { 
			 e.printStackTrace(); 
		 } finally { 
			 if (conn != null) { 
				 conn.close(); 
			 } 
		 } 
		 return retFlag;
	 }
}
