package com.spring.domain.dto;

/**
 * 配置rabbitmq 名字
 */
public class RabbitBean {
    private RabbitBean(){};
    /**
     * 队列
     */
    public static final String userLoginT="Q_T_User_Login";
    /**
     * 交换机
     */
    public static final String userLoginE="E_T_Login";
    /**
     * 路由
     */
    public static final String userLoginR="Q_T_User_Login";


}
