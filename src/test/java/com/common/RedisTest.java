/**
 * 
 */
package com.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.BaseJunitTest;
import com.common.redis.service.RedisService;

/**
 * @author sunchangjunn
 * 2018年8月27日下午2:40:27
 */
public class RedisTest extends BaseJunitTest{
	
	@Autowired
	RedisService redisService;
	@Test
	public  void  test() {
		System.out.println();
		System.out.println(redisService.get("sun"));;
	}

}
