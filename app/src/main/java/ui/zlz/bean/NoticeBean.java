package ui.zlz.bean;

import java.util.List;

public class NoticeBean {

    /**
     * status : true
     * code : 1
     * message : 获取网站公告信息成功
     * data : {"data":[{"id":"1","title":"租来赚保证美味用户资金安全","type":"2","create_time":"2018-11-01 14:31:13","content":"<p>123                 <\/p>","status":"1"},{"id":"5","title":"t4动态","type":"2","create_time":"2018-10-11 09:44:24","content":"<p>aaa<\/p>","status":"1"},{"id":"4","title":"动态方法","type":"2","create_time":"2018-10-09 10:31:28","content":"<p>tetset<\/p>","status":"1"}]}
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
             * id : 1
             * title : 租来赚保证美味用户资金安全
             * type : 2
             * create_time : 2018-11-01 14:31:13
             * content : <p>123                 </p>
             * status : 1
             */

            private String id;
            private String title;
            private String type;
            private String create_time;
            private String content;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
