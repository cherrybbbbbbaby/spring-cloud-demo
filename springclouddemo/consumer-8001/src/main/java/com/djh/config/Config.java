package com.djh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class Config {

	@Bean
	public IRule roundRobinRule() {
		return new RandomRule();//把ribbon的负载均衡算法改为随机算法
	}
	
}
