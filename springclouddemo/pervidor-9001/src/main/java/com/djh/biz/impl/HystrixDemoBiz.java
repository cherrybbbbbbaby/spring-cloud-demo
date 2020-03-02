package com.djh.biz.impl;

import org.springframework.web.bind.annotation.RestController;

import com.djh.biz.IHystrixDemoBiz;

@RestController
public class HystrixDemoBiz implements IHystrixDemoBiz {

	/**
	 * ģ�ⳬʱ
	 */
	@Override
	public String timeOutDemo(long timeMillis) {
		try {
			Thread.sleep(timeMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "after sleeping  "+timeMillis+"ms";
	}


	/**
	 * ģ���׳��쳣
	 */
	@Override
	public int exceptionDemo() {
		return 1/0;
	}

	

}
