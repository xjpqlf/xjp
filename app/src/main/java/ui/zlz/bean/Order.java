package ui.zlz.bean;

import java.util.List;

public class Order {

    /**
     * status : true
     * code : 1
     * message : 获取订单记录成功
     * data : {"data":[{"id":"3","sn":"333333333333333","goods_name":"cccc","num":"2","total_price":"200","pay_method":null,"status":"1","ctime":"2018-10-25 16:14:13","ptime":"2018-10-25 16:14:13","member_id":"30"},{"id":"1","sn":"11111111111111","goods_name":"支付宝","num":"1","total_price":"100","pay_method":null,"status":"1","ctime":"2018-11-05 17:27:46","ptime":"2018-11-05 17:27:46","member_id":"30"}]}
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
             * sn : 333333333333333
             * goods_name : cccc
             * num : 2
             * total_price : 200
             * pay_method : null
             * status : 1
             * ctime : 2018-10-25 16:14:13
             * ptime : 2018-10-25 16:14:13
             * member_id : 30
             */

            private String id;
            private String sn;
            private String goods_name;
            private String num;
            private String total_price;
            private Object pay_method;
            private String status;
            private String ctime;
            private String ptime;
            private String member_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public Object getPay_method() {
                return pay_method;
            }

            public void setPay_method(Object pay_method) {
                this.pay_method = pay_method;
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

            public String getPtime() {
                return ptime;
            }

            public void setPtime(String ptime) {
                this.ptime = ptime;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }
        }
    }
}
