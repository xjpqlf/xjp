package ui.zlz.bean;

public class Asset {

    /**
     * status : true
     * code : 1
     * message : 获取资产成功
     * data : {"user_money":"0.00","user_earnings":null,"all_asset":0}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBean data;

    public Asset() {
    }

    @Override
    public String toString() {
        return "Asset{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_money : 0.00
         * user_earnings : null
         * all_asset : 0
         */

        private String user_money;
        private String user_earnings;
        private String all_asset;

        public DataBean() {
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "user_money='" + user_money + '\'' +
                    ", user_earnings=" + user_earnings +
                    ", all_asset=" + all_asset +
                    '}';
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getUser_earnings() {
            return user_earnings;
        }

        public void setUser_earnings(String user_earnings) {
            this.user_earnings = user_earnings;
        }

        public String getAll_asset() {
            return all_asset;
        }

        public void setAll_asset(String all_asset) {
            this.all_asset = all_asset;
        }
    }
}
