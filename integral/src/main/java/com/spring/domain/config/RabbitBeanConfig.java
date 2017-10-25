package com.spring.domain.config;

/**
 * 配置rabbitmq 名字
 */
public class RabbitBeanConfig {
    private RabbitBeanConfig(){};

    /**
     * 队列
     */
    public static final String USER_LOGIN_Q="Q_User_login_Integral";
    /**
     * 交换机
     */
    public static final String USER_LOGIN_E="E_F_Login";
    /**
     * 路由
     */
    public static final String USRE_LOGIN_R="R.User.Login";
}
