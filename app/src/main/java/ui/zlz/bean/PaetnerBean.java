package ui.zlz.bean;

import java.util.List;

public class PaetnerBean {

    /**
     * status : true
     * code : 1
     * message : 获取合作合办成功
     * data : {"data":[{"id":"14","name":"机构2","img_url":"/Upload/image/2018-10-31/5bd94fa3c847f.jpg"},{"id":"13","name":"百度","img_url":"/Upload/image/2018-10-31/5bd94e6ededa7.jpg"},{"id":"9","name":"机构6","img_url":"/Upload/image/2018-09-19/5ba1eba472b04.jpg"},{"id":"8","name":"6969","img_url":"/Upload/image/2018-09-12/5b9890381868f.jpg"}]}
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
             * id : 14
             * name : 机构2
             * img_url : /Upload/image/2018-10-31/5bd94fa3c847f.jpg
             */

            private String id;
            private String name;
            private String img_url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
