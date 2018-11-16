package ui.zlz.bean;

import java.util.List;

public class Msg {


    /**
     * status : true
     * code : 1
     * message : 获取消息成功
     * data : {"data":[{"id":"5","title":"qqq","content":"<p>11111111<\/p>","status":"1","ctime":"1541036862","is_delete":"0","read_status":0},{"id":"1","title":"1xiaoxi","content":"<p>1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<\/p>","status":"1","ctime":"1539414999","is_delete":"0","read_status":0}]}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBeanX data;

    public Msg() {
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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 5
             * title : qqq
             * content : <p>11111111</p>
             * status : 1
             * ctime : 1541036862
             * is_delete : 0
             * read_status : 0
             */

            private String id;
            private String title;
            private String content;
            private String status;
            private String ctime;
            private String is_delete;
            private int read_status;

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

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }

            public int getRead_status() {
                return read_status;
            }

            public void setRead_status(int read_status) {
                this.read_status = read_status;
            }
        }
    }
}
