package ui.zlz.bean;

public class AllTzBean {

    /**
     * status : true
     * code : 1
     * message : 获取投资人数成交金额成功
     * data : {"data":{"sum_user":"6","sum_money":"6000.00","all_earn":"9383.17"}}
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
         * data : {"sum_user":"6","sum_money":"6000.00","all_earn":"9383.17"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * sum_user : 6
             * sum_money : 6000.00
             * all_earn : 9383.17
             */

            private String sum_user;
            private String sum_money;
            private String all_earn;

            public String getSum_user() {
                return sum_user;
            }

            public void setSum_user(String sum_user) {
                this.sum_user = sum_user;
            }

            public String getSum_money() {
                return sum_money;
            }

            public void setSum_money(String sum_money) {
                this.sum_money = sum_money;
            }

            public String getAll_earn() {
                return all_earn;
            }

            public void setAll_earn(String all_earn) {
                this.all_earn = all_earn;
            }
        }
    }
}
