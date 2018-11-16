package ui.zlz.bean;

import java.util.List;

public class WeekBean {

    /**
     * status : true
     * code : 1
     * message : 请求数据完毕
     * data : {"data":[{"tz_money":"3000.00","g_id":"3","name":"租来赚3","money_pond":"13000","expect_rate":"0.15","rate":"11.50","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"3","surplus_tz":10000,"user_tz_id":"30","is_user_tz":1},{"tz_money":"1000.00","g_id":"12","name":"租来赚12","money_pond":"500000","expect_rate":"0.10","rate":"10.40","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"3","surplus_tz":499000,"user_tz_id":"30","is_user_tz":1},{"tz_money":null,"g_id":"15","name":"1","money_pond":"1","expect_rate":"1.00","rate":"1.00","img_url":"/Upload/image/2018-09-30/5bb09fbdf164b.jpg","month":"1","surplus_tz":1,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"6","name":"租来赚6","money_pond":"3000000","expect_rate":"0.30","rate":"10.80","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"2","surplus_tz":3000000,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"17","name":"123","money_pond":"123","expect_rate":"123.00","rate":"123.00","img_url":"/Upload/image/2018-10-09/5bbc6cfda5d48.jpg","month":"1","surplus_tz":123,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"7","name":"租来赚7","money_pond":"50000","expect_rate":"0.50","rate":"10.80","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"2","surplus_tz":50000,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"18","name":"租来赚1","money_pond":"1","expect_rate":"1.00","rate":"1.00","img_url":"/Upload/image/2018-10-10/5bbd6205c0c9c.jpg","month":"1","surplus_tz":1,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"8","name":"租来赚8","money_pond":"20000","expect_rate":"0.60","rate":"10.70","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"2","surplus_tz":20000,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"19","name":"租来赚","money_pond":"1","expect_rate":"1.00","rate":"1.00","img_url":"/Upload/image/2018-10-10/5bbd63a25d91b.jpg","month":"1","surplus_tz":1,"user_tz_id":"30","is_user_tz":0},{"tz_money":null,"g_id":"10","name":"租来赚10","money_pond":"30000","expect_rate":"0.30","rate":"10.80","img_url":"/Upload/image/2018-08-25/5b80fb6c798ca.jpg","month":"1","surplus_tz":30000,"user_tz_id":"30","is_user_tz":0}]}
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
             * tz_money : 3000.00
             * g_id : 3
             * name : 租来赚3
             * money_pond : 13000
             * expect_rate : 0.15
             * rate : 11.50
             * img_url : /Upload/image/2018-08-25/5b80fb6c798ca.jpg
             * month : 3
             * surplus_tz : 10000
             * user_tz_id : 30
             * is_user_tz : 1
             */

            private String tz_money;
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

            public String getTz_money() {
                return tz_money;
            }

            public void setTz_money(String tz_money) {
                this.tz_money = tz_money;
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
