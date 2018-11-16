package ui.zlz.bean;

import java.util.List;

public class BannerBean {

    /**
     * status : true
     * code : 1
     * message : 获取banner成功
     * data : {"data":[{"id":"17","name":"默认内容1","img_url":"/Upload/image/2018-10-31/5bd95a36d9f2c.gif"},{"id":"16","name":"123","img_url":"/Upload/image/2018-10-10/5bbd4ba16bdbd.jpg"},{"id":"11","name":"租来赚默认","img_url":"/Upload/image/2018-10-09/5bbc0fb15ffb1.jpg"}]}
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
             * id : 17
             * name : 默认内容1
             * img_url : /Upload/image/2018-10-31/5bd95a36d9f2c.gif
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
