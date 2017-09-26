package com.spring.common.model.model;

/**
 * @Description RedisKey前缀
 * @Author ErnestCheng
 * @Date 2017/6/6.
 */
public class RedisKey {
    /**
     * expamle :shop:product:id:16
     */
    public  static String product="shop:product:productId:";
    /**
     * expamle:shop:user:id:3
     */
    public static String user="shop:user:userId:";
    /**
     * expamle:shop:user:1:username
     */
    public static String userh="shop:user:";
}
