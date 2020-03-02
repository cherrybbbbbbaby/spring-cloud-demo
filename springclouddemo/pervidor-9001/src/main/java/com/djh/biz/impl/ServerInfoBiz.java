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
	private String serverPort;//��ȡ�����ļ��Ķ˿�
	@Value("${spring.application.name}")
	private String serverName;//��ȡ�����ļ���ʵ����

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
			info.setIpAddress("��ȡʧ��");
		}
		
		return info;
	}

}
