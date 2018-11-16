package ui.zlz.bean;

public class WebBean {
    /**
     * status : true
     * code : 1
     * message : 登录成功！
     * data : {"data":{"id":"54","nickname":"微博用户柘林莫","header":"http://tvax3.sinaimg.cn/default/images/default_avatar_male_180.gif","age":"25","sex":""},"session_token":"1890bff12266ca203ddfa614cbc23475","ispay_pwd":0}
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
         * data : {"id":"54","nickname":"微博用户柘林莫","header":"http://tvax3.sinaimg.cn/default/images/default_avatar_male_180.gif","age":"25","sex":""}
         * session_token : 1890bff12266ca203ddfa614cbc23475
         * ispay_pwd : 0
         */

        private DataBean data;
        private String session_token;
        private int ispay_pwd;

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

        public int getIspay_pwd() {
            return ispay_pwd;
        }

        public void setIspay_pwd(int ispay_pwd) {
            this.ispay_pwd = ispay_pwd;
        }

        public static class DataBean {
            /**
             * id : 54
             * nickname : 微博用户柘林莫
             * header : http://tvax3.sinaimg.cn/default/images/default_avatar_male_180.gif
             * age : 25
             * sex :
             */

            private String id;
            private String nickname;
            private String header;
            private String age;
            private String sex;

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

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }

    /**
     * status : true
     * code : 1
     * message : 微博登录成功！
     * data : {"data":{"openid":6180091110,"token":"2263bd8cc6d63d27408f03dc8cc952c8","nickname":"微博用户柘林莫","header":"http://tvax3.sinaimg.cn/default/images/default_avatar_male_180.gif","ctime":"2018-11-09 18:06:39"},"session_token":"2263bd8cc6d63d27408f03dc8cc952c8"}
     */

}
