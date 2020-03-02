package com.djh.biz.fallbackfactory;

import org.springframework.stereotype.Component;

import com.djh.biz.IHystrixDemoBiz;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixDemoFallbackFactory implements FallbackFactory<IHystrixDemoBiz> {

	@Override
	public IHystrixDemoBiz create(Throwable cause) {
		//Ä£Äâlog
		cause.printStackTrace();
		return new IHystrixDemoBiz() {
			
			@Override
			public String timeOutDemo(long timeMillis) {
				return "String from fallback method-->"+timeMillis;
			}
			
			@Override
			public int exceptionDemo() {
				return -999;
			}
		};
	}

}
