demo概述：  
模拟微服务的环境   
访问http://localhost:8001/serverInfo/get获取provider端的ip和端口信息   
访问http://localhost:8001/timeOutFallbackFactoryDemo/get/{timeMillis}模拟超时熔断(大于1000触发)   
访问http://localhost:8001/exceptionDemo/get模拟异常熔断   



架构：  
注册中心：eureka集群（端口7001、7002）  
providor集群（端口9001,9002）微服务名称server-info-pervidor  
consumer单机（端口8001）使用Feign调用微服务，开启Hystrix服务熔断，Ribbon负载均衡算法改为随机访问  
zuul网关单机（端口5001）带同一前缀/djh，屏蔽路径server-info-pervidor转为/server-infomation  
hystrix-dashboard单机（端口6001）  
