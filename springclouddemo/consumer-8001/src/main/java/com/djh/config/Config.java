package com.djh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class Config {

	@Bean
	public IRule roundRobinRule() {
		return new RandomRule();//��ribbon�ĸ��ؾ����㷨��Ϊ����㷨
	}
	
}
