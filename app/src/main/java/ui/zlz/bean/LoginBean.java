package ui.zlz.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {

    /**
     * status : true
     * code : 1
     * message : 登录成功！
     * data : {"data":{"id":"45","nickname":"租来赚13971410254","realname":"","age":"25","header":"/Upload/avatar/avatar.jpg"},"session_token":"cb1310b4967b20e552363e000718ebc1"}
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
         * data : {"id":"45","nickname":"租来赚13971410254","realname":"","age":"25","header":"/Upload/avatar/avatar.jpg"}
         * session_token : cb1310b4967b20e552363e000718ebc1
         */

        private DataBean data;
        private String session_token;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getSession_token() {
            return session_token;
        }

        public void setSession_token(String session_token) {
            this.session_token = session_token;
        }

        public static class DataBean {
            /**
             * id : 45
             * nickname : 租来赚13971410254
             * realname :
             * age : 25
             * header : /Upload/avatar/avatar.jpg
             */

            private String id;
            private String nickname;
            private String realname;
            private String age;
            private String header;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }
        }
    }
}
