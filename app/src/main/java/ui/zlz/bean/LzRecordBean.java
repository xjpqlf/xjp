package ui.zlz.bean;

import java.util.List;

public class LzRecordBean {

    /**
     * status : true
     * code : 1
     * message : 请求数据完毕
     * data : {"data":[{"nickname":"租来赚15393****723","tz_money":"1000.00","tz_time":"2018-11-09 18:08:09"},{"nickname":"租来赚15393****723","tz_money":"1000.00","tz_time":"2018-10-01 00:00:01"}]}
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
             * nickname : 租来赚15393****723
             * tz_money : 1000.00
             * tz_time : 2018-11-09 18:08:09
             */

            private String nickname;
            private String tz_money;
            private String tz_time;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTz_money() {
                return tz_money;
            }

            public void setTz_money(String tz_money) {
                this.tz_money = tz_money;
            }

            public String getTz_time() {
                return tz_time;
            }

            public void setTz_time(String tz_time) {
                this.tz_time = tz_time;
            }
        }
    }
}
