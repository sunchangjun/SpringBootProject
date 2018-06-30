package com.common.redis.service;

import redis.clients.jedis.Jedis;

public interface RedisService {
	  public Jedis getResource();  
	  
	    public void returnResource(Jedis jedis);  
	  
	    public void set(String key, String value);  
	  
	    public String get(String key); 
	    boolean exist(String key) ;
}
