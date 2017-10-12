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
    public  static final String product="shop:product:productId:";
    /**
     * expamle:shop:user:id:3
     */
    public static final String user="shop:user:userId:";
    /**
     * expamle:shop:user:1:username
     */
    public static final String userh="shop:user:";
    /**
     * expamle:shop:product:1:productId
     */
    public static final String producth="shop:product:";
    /**
     * expamle:shop:product:category:1(状态)
     */
    public static final String productCategory="shop:product:category:";
    /**
     * expamle:shop:user:addr:1
     */
    public static final String userAddr="shop:user:addr:";
    /**
     * expamle:shop:integral:
     */
    public static final String integralChange="shop:integral:";
}
