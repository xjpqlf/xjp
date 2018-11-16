package ui.zlz.bean;

public class ContractBean {

    /**
     * status : true
     * code : 1
     * message : 获取合同数据成功
     * data : {"user_data":{"realname":"卧槽","mobile":"13971410254","id_card_number":"420117198911201637"},"good_data":{"id":"3","order_no":"投标A1256"}}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBean data;

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
         * user_data : {"realname":"卧槽","mobile":"13971410254","id_card_number":"420117198911201637"}
         * good_data : {"id":"3","order_no":"投标A1256"}
         */

        private UserDataBean user_data;
        private GoodDataBean good_data;

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public GoodDataBean getGood_data() {
            return good_data;
        }

        public void setGood_data(GoodDataBean good_data) {
            this.good_data = good_data;
        }

        public static class UserDataBean {
            /**
             * realname : 卧槽
             * mobile : 13971410254
             * id_card_number : 420117198911201637
             */

            private String realname;
            private String mobile;
            private String id_card_number;

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getId_card_number() {
                return id_card_number;
            }

            public void setId_card_number(String id_card_number) {
                this.id_card_number = id_card_number;
            }
        }

        public static class GoodDataBean {
            /**
             * id : 3
             * order_no : 投标A1256
             */

            private String id;
            private String order_no;

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
        }
    }
}
