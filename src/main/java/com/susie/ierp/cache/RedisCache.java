package com.susie.ierp.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 使用第三方缓存数据库redis，作为二级缓存
 * @author lu
 */
@Resource
public class RedisCache implements Cache {
	
	private static final Logger log = LoggerFactory.getLogger(RedisCache.class);
	// Spring-redis连接池管理工厂
	@Autowired
	private static JedisConnectionFactory jedisConnectionFactory;

	private String id;
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public RedisCache(final String id){
		if(id == null){
			throw new IllegalArgumentException("Cache instances require an id!");
		}
		log.debug("mybatis-redis-cache-id=" + id);
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisConnection redisConnection = null;
		try{
			redisConnection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();
			redisConnection.set(redisSerializer.serialize(key),redisSerializer.serialize(value));
		}catch(JedisConnectionException e){
			e.getStackTrace();
		}finally{
			if(redisConnection != null){
				redisConnection.close();
			}
		}
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		RedisConnection redisConnection = null;
		try{
			redisConnection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();
			result = redisSerializer.deserialize(redisConnection.get(redisSerializer.serialize(key)));
		}catch(JedisConnectionException e){
			e.getStackTrace();
		}finally{
			if(redisConnection != null){
				redisConnection.close();
			}
		}
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		Object result = null;
		RedisConnection redisConnection = null;
		try{
			redisConnection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> redisSerializer = new JdkSerializationRedisSerializer();
			result = redisConnection.expire(redisSerializer.serialize(key), 0);
		}catch(JedisConnectionException e){
			e.getStackTrace();
		}finally{
			if(redisConnection != null){
				redisConnection.close();
			}
		}
		return result;
	}

	@Override
	public void clear() {
		RedisConnection redisConnection = null;
		try{
			redisConnection = jedisConnectionFactory.getConnection();
			redisConnection.flushDb();
			redisConnection.flushAll();
		}catch(JedisConnectionException e){
			e.getStackTrace();
		}finally{
			if(redisConnection != null){
				redisConnection.close();
			}
		}

	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection redisConnection = null;
		try{
			redisConnection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(redisConnection.dbSize().toString());
		}catch(JedisConnectionException e){
			e.getStackTrace();
		}finally{
			if(redisConnection != null){
				redisConnection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
//	
//	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
//        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
//    }

}
