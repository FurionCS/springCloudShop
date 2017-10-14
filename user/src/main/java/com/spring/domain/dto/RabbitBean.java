package com.spring.domain.dto;

/**
 * 配置rabbitmq 名字
 */
public class RabbitBean {
    private RabbitBean(){};
    /**
     * 队列
     */
    public static final String userLoginQ="Q_D_User_Login";
    /**
     * 交换机
     */
    public static final String userLoginE="E_D_Login";
    /**
     * 路由
     */
    public static final String userLoginR="Q_D_User_Login";


}
