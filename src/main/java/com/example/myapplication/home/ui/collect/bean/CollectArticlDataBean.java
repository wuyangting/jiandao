package com.example.myapplication.home.ui.collect.bean;

import java.util.List;

public class CollectArticlDataBean {

    /**
     * code : 1
     * message : 操作成功
     * data : {"start":"0","point_time":1589289965,"more":0,"list":[{"id":"24875","theme":"交通运输行业2019年全年完成投资3.25万亿元，稳中有进","description":"2019年中国交通运输行业发展稳中有进，得益于政府多措并举稳投资，多渠道吸引社会资金，全力保障建设项目资金需求，推动交通固定资产投资继续保持高位运行。2019年全年完成投资3.25万亿元，较2018年增长3.1%","is_good":1,"is_collect":1,"read_count":"43","image_url":"https://s.seetao.com/Public/Uploads/thumbnail/2020-05-12/y_s_98z9wdpojw516.png","link":"https://www.seetao.com/app_details/24875/zh/android/version/1.html","share_link":"https://www.seetao.com/m_details/24875/zh.html","collect_id":"1618","time":"05月12日"},{"id":"24283","theme":"中国建筑企业如何提高在中东欧市场的投标竞争力？","description":"中国企业在建筑行业的地位不仅要依靠中国市场的支撑，更需要扩大海外市场，国内国外市场相互结合，相互发展。中国同中东欧合作不断向着全方位、多层次、宽领域方向发展，呈现出难得的发展机遇，提高中国企业在中东欧市场的投标竞争力，将是一个很好地切入点。","is_good":1,"is_collect":1,"read_count":"203","image_url":"https://s.seetao.com/Public/Uploads/thumbnail/2020-05-07/y_s_1dg6n8puvblom.png","link":"https://www.seetao.com/app_details/24283/zh/android/version/1.html","share_link":"https://www.seetao.com/m_details/24283/zh.html","collect_id":"1616","time":"05月12日"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * start : 0
         * point_time : 1589289965
         * more : 0
         * list : [{"id":"24875","theme":"交通运输行业2019年全年完成投资3.25万亿元，稳中有进","description":"2019年中国交通运输行业发展稳中有进，得益于政府多措并举稳投资，多渠道吸引社会资金，全力保障建设项目资金需求，推动交通固定资产投资继续保持高位运行。2019年全年完成投资3.25万亿元，较2018年增长3.1%","is_good":1,"is_collect":1,"read_count":"43","image_url":"https://s.seetao.com/Public/Uploads/thumbnail/2020-05-12/y_s_98z9wdpojw516.png","link":"https://www.seetao.com/app_details/24875/zh/android/version/1.html","share_link":"https://www.seetao.com/m_details/24875/zh.html","collect_id":"1618","time":"05月12日"},{"id":"24283","theme":"中国建筑企业如何提高在中东欧市场的投标竞争力？","description":"中国企业在建筑行业的地位不仅要依靠中国市场的支撑，更需要扩大海外市场，国内国外市场相互结合，相互发展。中国同中东欧合作不断向着全方位、多层次、宽领域方向发展，呈现出难得的发展机遇，提高中国企业在中东欧市场的投标竞争力，将是一个很好地切入点。","is_good":1,"is_collect":1,"read_count":"203","image_url":"https://s.seetao.com/Public/Uploads/thumbnail/2020-05-07/y_s_1dg6n8puvblom.png","link":"https://www.seetao.com/app_details/24283/zh/android/version/1.html","share_link":"https://www.seetao.com/m_details/24283/zh.html","collect_id":"1616","time":"05月12日"}]
         */

        private String start;
        private String point_time;
        private int more;
        private List<ListBean> list;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getPoint_time() {
            return point_time;
        }

        public void setPoint_time(String point_time) {
            this.point_time = point_time;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 24875
             * theme : 交通运输行业2019年全年完成投资3.25万亿元，稳中有进
             * description : 2019年中国交通运输行业发展稳中有进，得益于政府多措并举稳投资，多渠道吸引社会资金，全力保障建设项目资金需求，推动交通固定资产投资继续保持高位运行。2019年全年完成投资3.25万亿元，较2018年增长3.1%
             * is_good : 1
             * is_collect : 1
             * read_count : 43
             * image_url : https://s.seetao.com/Public/Uploads/thumbnail/2020-05-12/y_s_98z9wdpojw516.png
             * link : https://www.seetao.com/app_details/24875/zh/android/version/1.html
             * share_link : https://www.seetao.com/m_details/24875/zh.html
             * collect_id : 1618
             * time : 05月12日
             */

            private String id;
            private String theme;
            private String description;
            private int is_good;
            private int is_collect;
            private String read_count;
            private String image_url;
            private String link;
            private String share_link;
            private String collect_id;
            private String time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public int getIs_good() {
                return is_good;
            }

            public void setIs_good(int is_good) {
                this.is_good = is_good;
            }

            public int getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(int is_collect) {
                this.is_collect = is_collect;
            }

            public String getRead_count() {
                return read_count;
            }

            public void setRead_count(String read_count) {
                this.read_count = read_count;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getShare_link() {
                return share_link;
            }

            public void setShare_link(String share_link) {
                this.share_link = share_link;
            }

            public String getCollect_id() {
                return collect_id;
            }

            public void setCollect_id(String collect_id) {
                this.collect_id = collect_id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
