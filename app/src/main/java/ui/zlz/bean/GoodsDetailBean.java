package ui.zlz.bean;

import java.util.List;

public class GoodsDetailBean {

    /**
     * status : true
     * code : 1
     * message : 请求数据完毕
     * data : {"data":{"id":"3","service_content":"1","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg",
     * "order_no":"投标A1256","name":"租来赚3","rate":"11.50","expect_rate":"0.15",
     * "money_pond":"13000","month":"3","ctime":"1970-01-01","surplus_money":12000,
     * "user_tz_status":"3"},"goods_img":["/Upload/image/2018-10-31/5bd96fd5097fc.png"]}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBeanX data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"id":"3","service_content":"1","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","order_no":"投标A1256","name":"租来赚3","rate":"11.50","expect_rate":"0.15","money_pond":"13000","month":"3","ctime":"1970-01-01","surplus_money":12000,"user_tz_status":"3"}
         * goods_img : ["/Upload/image/2018-10-31/5bd96fd5097fc.png"]
         */

        private DataBean data;
        private List<String> goods_img;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public List<String> getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(List<String> goods_img) {
            this.goods_img = goods_img;
        }

        public static class DataBean {
            /**
             * id : 3
             * service_content : 1
             * img_url : /Upload/image/2018-08-25/5b80fb6c798ca.jpg
             * order_no : 投标A1256
             * name : 租来赚3
             * rate : 11.50
             * expect_rate : 0.15
             * money_pond : 13000
             * month : 3
             * ctime : 1970-01-01
             * surplus_money : 12000
             * user_tz_status : 3
             */

            private String id;
            private String service_content;
            private String img_url;
            private String order_no;
            private String name;
            private String rate;
            private String expect_rate;
            private String money_pond;
            private String month;
            private String ctime;
            private int surplus_money;
            private String user_tz_status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getService_content() {
                return service_content;
            }

            public void setService_content(String service_content) {
                this.service_content = service_content;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getExpect_rate() {
                return expect_rate;
            }

            public void setExpect_rate(String expect_rate) {
                this.expect_rate = expect_rate;
            }

            public String getMoney_pond() {
                return money_pond;
            }

            public void setMoney_pond(String money_pond) {
                this.money_pond = money_pond;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public int getSurplus_money() {
                return surplus_money;
            }

            public void setSurplus_money(int surplus_money) {
                this.surplus_money = surplus_money;
            }

            public String getUser_tz_status() {
                return user_tz_status;
            }

            public void setUser_tz_status(String user_tz_status) {
                this.user_tz_status = user_tz_status;
            }
        }
    }
}
