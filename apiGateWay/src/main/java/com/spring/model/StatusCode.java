package com.spring.model;

/**
 * 状态码
 */
public enum StatusCode {
    /**
     * 1xxxx 操作成功但需要客户端进行操作*
     */
    Bind_Cient(1000),

    /**
     * 2xxxx 操作成功*
     */
    Success(2000),

    /**
     * 3xxxx 需要用户重试*
     */
    Try_Again(3000),

    //# 31xx 验证相关
    //# 310x 手机相关
    /**
     * 3100 手机号码不存在*
     */
    Mobile_Not_Exist(3100),
    /**
     * 3101 手机号码格式不正确*
     */
    Invalid_Mobile(3101),
    /**
     * 3102 手机号码不正确*
     */
    Mobile_Error(3102),


    //# 311x 验证码相关
    /**
     * 3110 发送验证码失败，请重试*
     */
    VerifyCode_Send_Fail(3110),
    /**
     * 3111 验证码无效,请重试*
     */
    Invalid_VerifyCode(3111),
    /**
     * 3112 发送验证码太频繁,请稍后重试*
     */
    VerifyCode_Busy(3112),

    //# 318x 邮件相关
    /**
     * 3180 链接已过期,请重试*
     */
    Link_Expire(3180),


    //# 319x 其他验证相关
    /**
     * 3190 数据不存在，请重试*
     */
    Data_Not_Exist(3190),
    /**
     * 3191 提交数据有误,请重试*
     */
    Data_Error(3191),
    /**
     * 3192 提交数据重复,请重试*
     */
    Data_Exist_Try_Again(3192),
    /**
     * 3193 外部接口调用异常*
     */
    API_Call_Fail(3193),
    /**
     * 3194更新失败
     */
    Update_Fail(3194),
    // # 33xx 数据有误相关
    /**
     * 3300 输入的代码有误*
     */
    Invalid_Code(3300),
    /**
     * 3301 提交数据不完整*
     */
    Data_Not_Complete(3301),
    /**
     * 3302 提交数据包含敏感词*
     */
    Data_Exist_Keyword(3302),


    //# 34xx api相关
    //# 340x 账号相关
    /**
     * 3400 用户名或密码错误*
     */
    Invalid_Password(3400),
    //# 341x 用户相关
    /**
     * 3410 账号不存在*
     */
    User_Not_Exist(3410),
    /**
     * 3411 用户名已存在*
     */
    User_Exist(3411),

    // #35xx 文件相关*
    /**
     * 3500 上传文件不能为空*
     */
    File_IsNull(3500),
    /**
     * 3501 上传文件太大*
     */
    File_Too_Large(3501),
    /**
     * 3502 上传文件失败*
     */
    File_Upload_Fail(3502),

    /**
     * 3503 图片格式不对*
     */
    Picture_Format_Error(3503),
    /**
     * 3504 系统找不到指定的文件*
     */
    File_Not_Found(3504),

    //# 39xx 通用异常相关
    /**
     * 3900 功能未开放*
     */
    Function_Disabled(3900),
    /**
     * 3910 请求api非法*
     */
    API_Fail(3910),
    /**
     * 3920 请求action错误*
     */
    Action_Fail(3920),
    /**
     * 3930 请求参数错误*
     */
    Param_Error(3930),
    /**
     * 3940 操作失败*
     */
    Fail_Code(3940),


    /**
     * 4xxxx 需要用户重试*
     */
    ReLogin(4000),

    /**
     * 4100 Token 已过期，请重新登录*
     */
    Invalid_Token_ReLogin(4100),

    /**
     * 预留资源错误
     */
    PartialConfirmerror(5000),

    /**
     * 9xxxx 系统错误*
     */
    System_Error(9000);

    private Integer value;

    // 构造方法
    StatusCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
