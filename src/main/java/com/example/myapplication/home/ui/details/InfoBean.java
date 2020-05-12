package com.example.myapplication.home.ui.details;

public class InfoBean {
    /**
     * code : 1
     * message : 成功提示
     * data : {"theme":"⽂章标题","description":"⽂章描述","image_url":"⽂章预览图","share_url":"⽂章分享链接","is_good":"是否被点赞，1是，0否","is_diff":"是否被点踩，1是，0否","is_collect":"是否被收藏，1是，0否"}
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
         * theme : ⽂章标题
         * description : ⽂章描述
         * image_url : ⽂章预览图
         * share_url : ⽂章分享链接
         * is_good : 是否被点赞，1是，0否
         * is_diff : 是否被点踩，1是，0否
         * is_collect : 是否被收藏，1是，0否
         */

        private String theme;
        private String description;
        private String image_url;
        private String share_url;
        private String is_good;
        private String is_diff;
        private String is_collect;

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getIs_good() {
            return is_good;
        }

        public void setIs_good(String is_good) {
            this.is_good = is_good;
        }

        public String getIs_diff() {
            return is_diff;
        }

        public void setIs_diff(String is_diff) {
            this.is_diff = is_diff;
        }

        public String getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }
    }
}
