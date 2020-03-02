package com.djh.biz.impl;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.djh.biz.IServerInfoBiz;
import com.djh.pojo.ServerInfo;


@RestController
public class ServerInfoBiz implements IServerInfoBiz {

	private static final Logger logger = LogManager.getLogger(ServerInfoBiz.class);
	@Value("${server.port}")
	private String serverPort;//读取配置文件的端口
	@Value("${spring.application.name}")
	private String serverName;//读取配置文件的实例名

	@Override
	public ServerInfo doGetServerInfo() {	
		ServerInfo info = new ServerInfo();
		info.setPort(serverPort);
		info.setServerName(serverName);
		try {
			String ipAddress = Inet4Address.getLocalHost().getHostAddress();
			info.setIpAddress(ipAddress);
		} catch (UnknownHostException e) {
			logger.warn(e);
			info.setIpAddress("获取失败");
		}
		
		return info;
	}

}
