package com.djh.biz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djh.biz.fallbackfactory.HystrixDemoFallbackFactory;

@FeignClient(name = "server-info-pervidor", contextId = "IHystrixDemoBiz", fallbackFactory = HystrixDemoFallbackFactory.class)
@ResponseBody
public interface IHystrixDemoBiz {

	@GetMapping("/timeOutDemo/get/{timeMillis}")
	public String timeOutDemo(@PathVariable("timeMillis")long timeMillis);
	
	@GetMapping("/exceptionDemo/get")
	public int exceptionDemo();
	
}
