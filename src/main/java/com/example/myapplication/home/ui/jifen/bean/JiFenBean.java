package com.example.myapplication.home.ui.jifen.bean;

public class JiFenBean {
    /**
     * code : 1
     * message : 成功提示
     * data : {"my_integral":"我的积分总数","check_in_status":"今⽇是否签到,0未签到,1已签到","read_article_count":"今⽇阅读⽂章的数量","share_article_count":"今天分享⽂章的数量"}
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
         * my_integral : 我的积分总数
         * check_in_status : 今⽇是否签到,0未签到,1已签到
         * read_article_count : 今⽇阅读⽂章的数量
         * share_article_count : 今天分享⽂章的数量
         */

        private String my_integral;
        private String check_in_status;
        private String read_article_count;
        private String share_article_count;

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

        public String getRead_article_count() {
            return read_article_count;
        }

        public void setRead_article_count(String read_article_count) {
            this.read_article_count = read_article_count;
        }

        public String getShare_article_count() {
            return share_article_count;
        }

        public void setShare_article_count(String share_article_count) {
            this.share_article_count = share_article_count;
        }
    }
}
