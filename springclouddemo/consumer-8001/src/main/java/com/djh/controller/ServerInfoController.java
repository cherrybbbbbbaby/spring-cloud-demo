package com.djh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.djh.biz.IHystrixDemoBiz;
import com.djh.biz.IServerInfoBiz;
import com.djh.pojo.ServerInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
public class ServerInfoController {

	@Autowired
	private IServerInfoBiz serverInfoBiz;
	@Autowired
	private IHystrixDemoBiz hystrixDemoBiz;
	
	
	@GetMapping("/serverInfo/get")
	public ServerInfo doGetServerInfo() {
		return serverInfoBiz.doGetServerInfo();
	}
	

	@GetMapping("/exceptionDemo/get")
	@HystrixCommand(fallbackMethod = "exceptionDemoFallBack")//注解式熔断
	public int exceptionDemo() {
		return 1/0;//模拟异常
	}
	
	public int exceptionDemoFallBack() {
		return -1;
	}
	
	
	@GetMapping("/fallbackDemo/get")
	public int fallbackDemo() {
		return hystrixDemoBiz.exceptionDemo();//实现FallbackFactory的熔断
	}
	
	
	@GetMapping("/timeOutDemo/get/{timeMillis}")
	@HystrixCommand(fallbackMethod = "timeOutDemoFallback",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")//配置超过一秒算超时
	})//注解式熔断
	public String timeOutDemo(@PathVariable("timeMillis")long timeMillis) {
		try {
			Thread.sleep(timeMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello world";
	}
	
	public String timeOutDemoFallback(long timeMillis) {
		return "String from time out fallback method -->" + timeMillis;
	}
	
	
	@GetMapping("/timeOutFallbackFactoryDemo/get/{timeMillis}")
	public String timeOutFallbackFactoryDemo(@PathVariable("timeMillis")long timeMillis) {
		long currentTimeMillis = System.currentTimeMillis();
		String result = hystrixDemoBiz.timeOutDemo(timeMillis);
		System.err.println("调用耗时"+(System.currentTimeMillis()-currentTimeMillis));
		return result;//超时触发FallbackFactory的熔断
	}
	
}
