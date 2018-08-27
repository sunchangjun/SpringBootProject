package com.common.redis.service.Impl;

import com.common.redis.service.RedisService;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;
import org.springframework.stereotype.Service;    


/** 
 *  
 * @author vic 
 * @desc resdis service 
 * 
 */  
@Service  
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

    private static JedisPool jedisPool = null;

    //Redis服务器IP
    private static String ADDR = "127.0.0.1";

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码
    private static String AUTH = "";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
//            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取资源
     * @return
     * @throws JedisException
     */
    public  Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

        } catch (JedisException e) {
            logger.warn("getResource.", e);
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    public  void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }



    public  void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    @Override  
    public void set(String key, String value) {  
        Jedis jedis=null;  
        try{  
            jedis = getResource();  
            jedis.set(key, value);  
            logger.info("Redis set success - " + key + ", value:" + value);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);  
        }finally{  
            returnResource(jedis);  
        }  
    }  
      
    @Override  
    public String get(String key) {  
        String result = null;  
        Jedis jedis=null;  
        try{  
            jedis = getResource();  
            result = jedis.get(key);  
            logger.info("Redis get success - " + key + ", value:" + result);  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);  
        }finally{  
            returnResource(jedis);  
        }  
        return result;  
    }  
    
    public  boolean exist(String key) {
    	 boolean result = false;  
         Jedis jedis=null;  
         try{  
             jedis = getResource();  
             result=  jedis.exists(key);
             logger.info("Redis get success - " + key + ", value:" + result);  
         } catch (Exception e) {  
             e.printStackTrace();  
             logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);  
         }finally{  
             returnResource(jedis);  
         }  
         return result;
    }


  
}  
