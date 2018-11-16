package ui.zlz.bean;

import java.util.List;

public class CouponBean {

    /**
     * status : true
     * code : 1
     * message : 获取劵包成功
     * data : {"data":[{"id":"2","uid":"30","is_use":"1","c_id":"16","ctime":"2018-09-28 17:37:24","past_time":"2020-04-29 10:30:44","type":"2","astrict_sum":"100.00","money":"0.20","day":"3","content":"加利息","name":"加息劵","due_time":"2018-09-30 00:00:00","use_time":"2018-09-28 00:00:00","status":"1"},{"id":"1","uid":"30","is_use":"0","c_id":"20","ctime":"2018-09-28 17:37:24","past_time":"2019-09-10 22:57:24","type":"2","astrict_sum":"1000.00","money":"0.50","day":"10","content":"加息劵0.5%","name":"可以增加0.5%的利率","due_time":"2018-10-11 00:00:00","use_time":"2018-10-10 00:00:00","status":"0"},{"id":"7","uid":"30","is_use":"1","c_id":"18","ctime":"2018-09-28 17:37:24","past_time":"2018-11-29 00:00:00","type":"1","astrict_sum":"500.00","money":"500.00","day":"3","content":"代金劵500","name":"500元代金劵0","due_time":"2018-09-30 00:00:00","use_time":"2018-09-30 00:00:06","status":"0"},{"id":"8","uid":"30","is_use":"0","c_id":"19","ctime":"2018-09-28 17:38:28","past_time":"2018-11-29 00:00:00","type":"1","astrict_sum":"1.00","money":"1.00","day":"1","content":"1","name":"daij","due_time":"2018-10-10 00:00:00","use_time":"2018-10-10 00:00:00","status":"1"},{"id":"11","uid":"30","is_use":"0","c_id":"22","ctime":"2018-11-01 16:51:53","past_time":"2018-11-06 16:51:53","type":"2","astrict_sum":"1000.00","money":"10.00","day":"5","content":"加息劵10%加息劵10%加息劵10%","name":"加息劵10%","due_time":null,"use_time":null,"status":"1"},{"id":"9","uid":"30","is_use":"0","c_id":"17","ctime":"2018-10-13 17:23:23","past_time":"2018-10-23 17:23:23","type":"1","astrict_sum":"500.00","money":"50.00","day":"10","content":"代金劵50","name":"50元代金劵","due_time":"2018-09-30 00:00:00","use_time":"2018-09-28 00:00:00","status":"1"},{"id":"10","uid":"30","is_use":"1","c_id":"15","ctime":"2018-10-20 17:09:00","past_time":"2018-10-23 17:09:00","type":"1","astrict_sum":"0.00","money":"10.00","day":"3","content":"代金劵","name":"10元代金劵","due_time":"2018-09-30 00:00:00","use_time":"2018-09-28 00:00:00","status":"1"},{"id":"3","uid":"30","is_use":"1","c_id":"16","ctime":"2018-09-28 17:37:24","past_time":"2018-10-01 17:38:28","type":"2","astrict_sum":"100.00","money":"0.20","day":"3","content":"加利息","name":"加息劵","due_time":"2018-09-30 00:00:00","use_time":"2018-09-28 00:00:00","status":"1"}]}
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
             * id : 2
             * uid : 30
             * is_use : 1
             * c_id : 16
             * ctime : 2018-09-28 17:37:24
             * past_time : 2020-04-29 10:30:44
             * type : 2
             * astrict_sum : 100.00
             * money : 0.20
             * day : 3
             * content : 加利息
             * name : 加息劵
             * due_time : 2018-09-30 00:00:00
             * use_time : 2018-09-28 00:00:00
             * status : 1
             */

            private String id;
            private String uid;
            private String is_use;
            private String c_id;
            private String ctime;
            private String past_time;
            private String type;
            private String astrict_sum;
            private String money;
            private String day;
            private String content;
            private String name;
            private String due_time;
            private String use_time;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getIs_use() {
                return is_use;
            }

            public void setIs_use(String is_use) {
                this.is_use = is_use;
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getPast_time() {
                return past_time;
            }

            public void setPast_time(String past_time) {
                this.past_time = past_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAstrict_sum() {
                return astrict_sum;
            }

            public void setAstrict_sum(String astrict_sum) {
                this.astrict_sum = astrict_sum;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDue_time() {
                return due_time;
            }

            public void setDue_time(String due_time) {
                this.due_time = due_time;
            }

            public String getUse_time() {
                return use_time;
            }

            public void setUse_time(String use_time) {
                this.use_time = use_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
