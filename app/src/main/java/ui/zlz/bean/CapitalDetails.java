package ui.zlz.bean;

import java.util.List;

public class CapitalDetails {

    /**
     * status : true
     * code : 1
     * message : 获取资金明细成功
     * data : {"already_tz":"45021.00","data":[{"all_tz_money":"23000.00","gcount":"4","user_id":"30","status":"1"},{"all_tz_money":"21021.00","gcount":"4","user_id":"30","status":"2"},{"all_tz_money":"1000.00","gcount":"1","user_id":"30","status":"3"}]}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBeanX data;

    public CapitalDetails() {
    }

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
         * already_tz : 45021.00
         * data : [{"all_tz_money":"23000.00","gcount":"4","user_id":"30","status":"1"},{"all_tz_money":"21021.00","gcount":"4","user_id":"30","status":"2"},{"all_tz_money":"1000.00","gcount":"1","user_id":"30","status":"3"}]
         */

        private String already_tz;
        private List<DataBean> data;

        public DataBeanX() {
        }

        public String getAlready_tz() {
            return already_tz;
        }

        public void setAlready_tz(String already_tz) {
            this.already_tz = already_tz;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * all_tz_money : 23000.00
             * gcount : 4
             * user_id : 30
             * status : 1
             */

            private String all_tz_money;
            private String gcount;
            private String user_id;
            private String status;

            public DataBean() {
            }

            public String getAll_tz_money() {
                return all_tz_money;
            }

            public void setAll_tz_money(String all_tz_money) {
                this.all_tz_money = all_tz_money;
            }

            public String getGcount() {
                return gcount;
            }

            public void setGcount(String gcount) {
                this.gcount = gcount;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
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
