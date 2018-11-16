package ui.zlz.bean;

import java.util.List;

public class RecommendBean {

    /**
     * status : true
     * code : 1
     * message : 获取租品数据成功
     * data : {"data":[{"id":"3","order_no":"投标A1256","name":"租来赚3",
     * "img_url":"image/2018-08-25/5b80fb6c798ca.jpg","rate":"11.50",
     * "expect_rate":"0.15","money_pond":"13000","least_money":"1000",
     * "service_content":"1","type":"1","is_stick":"1","month":"3",
     * "ctime":"0","status":"0","full_time":"0","is_delete":"1"},
     * {"id":"9","order_no":"投标A123","name":"租来赚9热门","img_url":"image/2018-08-25/5b80fb6c798ca.jpg","rate":"10.80","expect_rate":"0.20","money_pond":"200000","least_money":"1000","service_content":"1","type":"3","is_stick":"1","month":"8","ctime":"0","status":"0","full_time":"0","is_delete":"1"}]}
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 3
             * order_no : 投标A1256
             * name : 租来赚3
             * img_url : image/2018-08-25/5b80fb6c798ca.jpg
             * rate : 11.50
             * expect_rate : 0.15
             * money_pond : 13000
             * least_money : 1000
             * service_content : 1
             * type : 1
             * is_stick : 1
             * month : 3
             * ctime : 0
             * status : 0
             * full_time : 0
             * is_delete : 1
             */

            private String id;
            private String order_no;
            private String name;
            private String img_url;
            private String rate;
            private String expect_rate;
            private String money_pond;
            private String least_money;
            private String service_content;
            private String type;
            private String is_stick;
            private String month;
            private String ctime;
            private String status;
            private String full_time;
            private String is_delete;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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

            public String getLeast_money() {
                return least_money;
            }

            public void setLeast_money(String least_money) {
                this.least_money = least_money;
            }

            public String getService_content() {
                return service_content;
            }

            public void setService_content(String service_content) {
                this.service_content = service_content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIs_stick() {
                return is_stick;
            }

            public void setIs_stick(String is_stick) {
                this.is_stick = is_stick;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getFull_time() {
                return full_time;
            }

            public void setFull_time(String full_time) {
                this.full_time = full_time;
            }

            public String getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }
        }
    }
}
