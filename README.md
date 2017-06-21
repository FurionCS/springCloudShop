# Spring cloud shop

本系统参考[spring-cloud-rest-tcc](https://github.com/FurionCS/spring-cloud-rest-tcc) 项目进行学习
Spring Cloud为开发者提供了快速构建分布式系统中的一些常见工具, 如分布式配置中心, 服务发现与注册中心, 智能路由, 服务熔断及降级, 消息总线, 分布式追踪的解决方案等.
本次实战以模拟下单流程为背景,采用分布式事务中的Try-Confirm-Cancel即TCC模式作为实战演示.

## 开发环境
-  MySQL 5.7.17
-  RabbitMQ 3.6.6
-  Java8 with JCE
-  Spring Cloud Camden.SR6
-  redis 3.0
-  mongodb

## 项目结构

| 模块名称|     作用|   备注|
| :-------- | --------:| :------: |
| admin|   spring-boot-admin,用于监控|  |
|apiGateWay|spring-cloud-zuul,用于做路由，负载均衡|
|common|整个项目的工具类|
|config|配置中心|
|hystrixdashboard|hystrix,断融监控|
|order|订单模块|
|proudct|产品模块|
|server|eureka-server,注册中心|
|user|用户模块|
|tcc|tcc事务模块|


##启动方式
为了保证开箱即用，我们将config 的需要加载的配置文件放入码云，https://git.oschina.net/believecs/SpringcloudConfig
fork一份到自己的码云。
1：先准备环境，启动mysql,rabbitmq,redis,mongodb,准备带有jce的java环境（加密需要用到），本地系统host加上
```
127.0.0.1 eureka1
127.0.0.1 eureka2
```
2：启动两个server,peer1,peer2
3：我们采用非对称加密，需要本地生成证书，参考http://blog.csdn.net/u014792352/article/details/73163714
获得证书后复制到classpath路径下，在application.yml中配置,然后启动，调用加密接口，将获得密文拷贝到你码云对应的需要替换的地方。
```
encrypt:
  key-store:
    location: server.jks
    password: letmein
    alias: mytestkey
    secret: changeme
```
4：启动order模块=》产品模块=》用户模块=》tcc模块=》剩余检测模块

## Try Confirm Cancel补偿模式

本实例遵循的是Atomikos公司对微服务的分布式事务所提出的[RESTful TCC](https://www.atomikos.com/Blog/TransactionManagementAPIForRESTTCC)解决方案

RESTful TCC模式分3个阶段执行

1. Trying阶段主要针对业务系统检测及作出预留资源请求, 若预留资源成功, 则返回确认资源的链接与过期时间
2. Confirm阶段主要是对业务系统的预留资源作出确认, 要求TCC服务的提供方要对确认预留资源的接口实现幂等性, 若Confirm成功则返回204, 资源超时则证明已经被回收且返回404
3. Cancel阶段主要是在业务执行错误或者预留资源超时后执行的资源释放操作, Cancel接口是一个可选操作, 因为要求TCC服务的提供方实现自动回收的功能, 所以即便是不认为进行Cancel, 系统也会自动回收资源

