package com.common.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注意：
    在@ConfigurationProperties注释中有两个属性：
locations：指定配置文件的所在位置
prefix：指定配置文件中键名称的前缀（我这里配置文件中所有键名都是以author.开头）
    使用@Component是让该类能够在其他地方被依赖使用，即使用@Autowired注释来创建实例。
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")//,locations= "classpath:author.properties") 
public class RedisProperties {
    private int    expireSeconds;
    private String clusterNodes;
    private String password;
    private int    commandTimeout;
	public int getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getClusterNodes() {
		return clusterNodes;
	}
	public void setClusterNodes(String clusterNodes) {
		this.clusterNodes = clusterNodes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCommandTimeout() {
		return commandTimeout;
	}
	public void setCommandTimeout(int commandTimeout) {
		this.commandTimeout = commandTimeout;
	}
    
    
    
}
