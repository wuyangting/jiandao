package com.example.myapplication.home.setting.bean;

public class CheckUpdateBean {
    /**
     * code : 1
     * message : 成功提示
     * data : {"is_upgrade":"是否需要升级，1需要，2不需要","force_upgrade":"是否需要强制升级，1需要，2不需要","upgrade_point":"升级提示","version_id":"版本id","version":"版本号"}
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
         * is_upgrade : 是否需要升级，1需要，2不需要
         * force_upgrade : 是否需要强制升级，1需要，2不需要
         * upgrade_point : 升级提示
         * version_id : 版本id
         * version : 版本号
         */

        private int is_upgrade;
        private String force_upgrade;
        private String upgrade_point;
        private String version_id;
        private String version;

        public int getIs_upgrade() {
            return is_upgrade;
        }

        public void setIs_upgrade(int is_upgrade) {
            this.is_upgrade = is_upgrade;
        }

        public String getForce_upgrade() {
            return force_upgrade;
        }

        public void setForce_upgrade(String force_upgrade) {
            this.force_upgrade = force_upgrade;
        }

        public String getUpgrade_point() {
            return upgrade_point;
        }

        public void setUpgrade_point(String upgrade_point) {
            this.upgrade_point = upgrade_point;
        }

        public String getVersion_id() {
            return version_id;
        }

        public void setVersion_id(String version_id) {
            this.version_id = version_id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
