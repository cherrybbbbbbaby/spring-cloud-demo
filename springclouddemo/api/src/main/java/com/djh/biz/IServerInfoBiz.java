package com.djh.biz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djh.pojo.ServerInfo;

@FeignClient(name = "server-info-pervidor", contextId = "IServerInfoBiz")
@ResponseBody
public interface IServerInfoBiz {
	
	/**
	 * »ñÈ¡¶Ë¿Ú
	 * @return
	 */
	@GetMapping("/serverInfo/get")
	ServerInfo doGetServerInfo();
	
}
