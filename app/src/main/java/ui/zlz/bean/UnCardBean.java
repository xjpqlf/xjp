package ui.zlz.bean;

import java.util.List;

public class UnCardBean {

    /**
     * status : true
     * code : 1
     * message : 获取银行卡数据成功
     * data : {"data":[{"id":"11","uid":"30","card_name":"中国工商银行","card_num":"6215583202014058411","card_img":"http://images.juheapi.com/banklogo/1.jpg","status":"1","tel":"95562"}]}
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
             * id : 11
             * uid : 30
             * card_name : 中国工商银行
             * card_num : 6215583202014058411
             * card_img : http://images.juheapi.com/banklogo/1.jpg
             * status : 1
             * tel : 95562
             */

            private String id;
            private String uid;
            private String card_name;
            private String card_num;
            private String card_img;
            private String status;
            private String tel;

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

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getCard_img() {
                return card_img;
            }

            public void setCard_img(String card_img) {
                this.card_img = card_img;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }
        }
    }
}
