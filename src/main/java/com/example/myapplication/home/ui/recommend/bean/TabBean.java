package com.example.myapplication.home.ui.recommend.bean;

import java.util.List;

public class TabBean {
    /**
     * code : 1
     * data : {"list":[{"back_color":"E04968","id":"recommend","name":"推荐","type":1},{"back_color":"003372","id":"6","name":"战略","type":2},{"back_color":"4A8950","id":"14","name":"工程","type":2},{"back_color":"2883B0","id":"10","name":"一带一路","type":2},{"back_color":"A18A6D","id":"29","name":"机械","type":2},{"back_color":"C85306","id":"28","name":"特写","type":2},{"back_color":"F6B051","id":"27","name":"社评","type":2},{"back_color":"E03A2E","id":"42","name":"即时","type":2},{"back_color":"9149B4","id":"39","name":"传承","type":2}]}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * back_color : E04968
             * id : recommend
             * name : 推荐
             * type : 1
             */

            private String back_color;
            private String id;
            private String name;
            private int type;

            public String getBack_color() {
                return back_color;
            }

            public void setBack_color(String back_color) {
                this.back_color = back_color;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
