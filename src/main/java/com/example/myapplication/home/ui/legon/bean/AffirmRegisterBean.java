package com.example.myapplication.home.ui.legon.bean;

public class AffirmRegisterBean {

    /**
     * code : 1
     * message : 成功提示
     * data : {"token":{"value":"⽤户登录成功之后的身份标识","expire_time":"token过期时间"},"user_info":{"head_url":"头像","nickname":"昵称","mobile":"⼿机号","qq_bind":"qq是否绑定，1绑定，0未绑定","qq_openid":"扣扣的openid","qq_unionid":"值为空，可以忽略","sina_bind":"新浪是否绑定，1绑定，0未绑定","sina_openid":"新浪的uid","sina_unionid":"值为空，可以忽略","wechat_bind":"微信是否绑定，1绑定，0未绑定","wechat_openid":"微信的openid","wechat_unionid":"微信的unionid","notice_count":"未读消息数量","my_integral":"我的积分","check_in_status":"状态：0未签到，1已签到"}}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : {"value":"⽤户登录成功之后的身份标识","expire_time":"token过期时间"}
         * user_info : {"head_url":"头像","nickname":"昵称","mobile":"⼿机号","qq_bind":"qq是否绑定，1绑定，0未绑定","qq_openid":"扣扣的openid","qq_unionid":"值为空，可以忽略","sina_bind":"新浪是否绑定，1绑定，0未绑定","sina_openid":"新浪的uid","sina_unionid":"值为空，可以忽略","wechat_bind":"微信是否绑定，1绑定，0未绑定","wechat_openid":"微信的openid","wechat_unionid":"微信的unionid","notice_count":"未读消息数量","my_integral":"我的积分","check_in_status":"状态：0未签到，1已签到"}
         */

        private TokenBean token;
        private UserInfoBean user_info;

        public TokenBean getToken() {
            return token;
        }

        public void setToken(TokenBean token) {
            this.token = token;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public static class TokenBean {
            /**
             * value : ⽤户登录成功之后的身份标识
             * expire_time : token过期时间
             */

            private String value;
            private String expire_time;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(String expire_time) {
                this.expire_time = expire_time;
            }
        }

        public static class UserInfoBean {
            /**
             * head_url : 头像
             * nickname : 昵称
             * mobile : ⼿机号
             * qq_bind : qq是否绑定，1绑定，0未绑定
             * qq_openid : 扣扣的openid
             * qq_unionid : 值为空，可以忽略
             * sina_bind : 新浪是否绑定，1绑定，0未绑定
             * sina_openid : 新浪的uid
             * sina_unionid : 值为空，可以忽略
             * wechat_bind : 微信是否绑定，1绑定，0未绑定
             * wechat_openid : 微信的openid
             * wechat_unionid : 微信的unionid
             * notice_count : 未读消息数量
             * my_integral : 我的积分
             * check_in_status : 状态：0未签到，1已签到
             */

            private String head_url;
            private String nickname;
            private String mobile;
            private String qq_bind;
            private String qq_openid;
            private String qq_unionid;
            private String sina_bind;
            private String sina_openid;
            private String sina_unionid;
            private String wechat_bind;
            private String wechat_openid;
            private String wechat_unionid;
            private String notice_count;
            private String my_integral;
            private String check_in_status;

            public String getHead_url() {
                return head_url;
            }

            public void setHead_url(String head_url) {
                this.head_url = head_url;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getQq_bind() {
                return qq_bind;
            }

            public void setQq_bind(String qq_bind) {
                this.qq_bind = qq_bind;
            }

            public String getQq_openid() {
                return qq_openid;
            }

            public void setQq_openid(String qq_openid) {
                this.qq_openid = qq_openid;
            }

            public String getQq_unionid() {
                return qq_unionid;
            }

            public void setQq_unionid(String qq_unionid) {
                this.qq_unionid = qq_unionid;
            }

            public String getSina_bind() {
                return sina_bind;
            }

            public void setSina_bind(String sina_bind) {
                this.sina_bind = sina_bind;
            }

            public String getSina_openid() {
                return sina_openid;
            }

            public void setSina_openid(String sina_openid) {
                this.sina_openid = sina_openid;
            }

            public String getSina_unionid() {
                return sina_unionid;
            }

            public void setSina_unionid(String sina_unionid) {
                this.sina_unionid = sina_unionid;
            }

            public String getWechat_bind() {
                return wechat_bind;
            }

            public void setWechat_bind(String wechat_bind) {
                this.wechat_bind = wechat_bind;
            }

            public String getWechat_openid() {
                return wechat_openid;
            }

            public void setWechat_openid(String wechat_openid) {
                this.wechat_openid = wechat_openid;
            }

            public String getWechat_unionid() {
                return wechat_unionid;
            }

            public void setWechat_unionid(String wechat_unionid) {
                this.wechat_unionid = wechat_unionid;
            }

            public String getNotice_count() {
                return notice_count;
            }

            public void setNotice_count(String notice_count) {
                this.notice_count = notice_count;
            }

            public String getMy_integral() {
                return my_integral;
            }

            public void setMy_integral(String my_integral) {
                this.my_integral = my_integral;
            }

            public String getCheck_in_status() {
                return check_in_status;
            }

            public void setCheck_in_status(String check_in_status) {
                this.check_in_status = check_in_status;
            }
        }
    }
}
