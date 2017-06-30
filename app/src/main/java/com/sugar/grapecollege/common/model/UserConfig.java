package com.sugar.grapecollege.common.model;

import android.text.TextUtils;


/**
 * @CreateBy qsmaxmin
 * @Date 16/7/29
 * @Description 用户配置信息序列化
 */
public class UserConfig {//extends J2WProperties
    /**
     * 单例模式
     */
    private volatile static UserConfig USER_CONFIG;

    private UserConfig() {
    }

    private UserConfig(String configName) {
//        super(configName);
    }

    public static UserConfig getInstance() {
        if (USER_CONFIG == null) {
            synchronized (UserConfig.class) {
                if (USER_CONFIG == null) {
                    USER_CONFIG = new UserConfig("UserConfig" + "userId");//AppConfig.getInstance().userId
                }
            }
        }
        return USER_CONFIG;
    }

//    @Override public String initTag() {
//        return "UserConfig";
//    }
//
//    @Override public int initType() {
//        return J2WProperties.OPEN_TYPE_DATA;
//    }

    private String userId;                // 用户Id
    private String userPhotoUrl;              // 用户头像
    private String userName;              // 用户昵称
    private String userPhone;              // 用户手机号

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
//        super.commit();
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
//        super.commit();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
//        super.commit();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
//        super.commit();
    }

    public void logout() {
        USER_CONFIG = null;//切记置空
    }

    public boolean isLogin() {
        return (!TextUtils.isEmpty(userId));
    }
}
