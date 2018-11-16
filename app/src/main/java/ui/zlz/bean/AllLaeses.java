package ui.zlz.bean;

import java.util.List;

public class AllLaeses {


    /**
     * status : true
     * code : 1
     * message : 请求数据完毕
     * data : {"data":[{"tz_money":"20000.00","status":"1","ctime":"1970-01-01 08:00:00","order_no":"投标A125","g_id":"7","name":"租来赚7","money_pond":"50000","expect_rate":"0.50","rate":"10.80","img_url":"image/2018-08-25/5b80fb6c798ca.jpg","month":"2","surplus_tz":30000,"user_tz_id":"30","is_user_tz":1}]}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBeanX data;

    public boolean isStatus() {
        return status;
    }

    public AllLaeses() {
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
             * tz_money : 20000.00
             * status : 1
             * ctime : 1970-01-01 08:00:00
             * order_no : 投标A125
             * g_id : 7
             * name : 租来赚7
             * money_pond : 50000
             * expect_rate : 0.50
             * rate : 10.80
             * img_url : image/2018-08-25/5b80fb6c798ca.jpg
             * month : 2
             * surplus_tz : 30000
             * user_tz_id : 30
             * is_user_tz : 1
             */

            private String tz_money;
            private String status;
            private String ctime;
            private String order_no;
            private String g_id;
            private String name;
            private String money_pond;
            private String expect_rate;
            private String rate;
            private String img_url;
            private String month;
            private int surplus_tz;
            private String user_tz_id;
            private int is_user_tz;

            public DataBean() {
            }

            public String getTz_money() {
                return tz_money;
            }

            public void setTz_money(String tz_money) {
                this.tz_money = tz_money;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney_pond() {
                return money_pond;
            }

            public void setMoney_pond(String money_pond) {
                this.money_pond = money_pond;
            }

            public String getExpect_rate() {
                return expect_rate;
            }

            public void setExpect_rate(String expect_rate) {
                this.expect_rate = expect_rate;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public int getSurplus_tz() {
                return surplus_tz;
            }

            public void setSurplus_tz(int surplus_tz) {
                this.surplus_tz = surplus_tz;
            }

            public String getUser_tz_id() {
                return user_tz_id;
            }

            public void setUser_tz_id(String user_tz_id) {
                this.user_tz_id = user_tz_id;
            }

            public int getIs_user_tz() {
                return is_user_tz;
            }

            public void setIs_user_tz(int is_user_tz) {
                this.is_user_tz = is_user_tz;
            }
        }
    }
}
