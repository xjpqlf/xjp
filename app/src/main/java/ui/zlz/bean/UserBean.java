package ui.zlz.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {


    /**
     * status : true
     * code : 1
     * message : 登录成功！
     * data : {"data":{"id":"45","nickname":"租来赚13971410254","realname":"卧槽","age":"25","header":"/Upload/avatar/2018-11-05/5bdfb75a4d71d.jpg"},"session_token":"fd45a96da018e9afaeafac4a4f75d4d7","ispay_pwd":0}
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static class DataBeanX {
        /**
         * data : {"id":"45","nickname":"租来赚13971410254","realname":"卧槽","age":"25","header":"/Upload/avatar/2018-11-05/5bdfb75a4d71d.jpg"}
         * session_token : fd45a96da018e9afaeafac4a4f75d4d7
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
             * id : 45
             * nickname : 租来赚13971410254
             * realname : 卧槽
             * age : 25
             * header : /Upload/avatar/2018-11-05/5bdfb75a4d71d.jpg
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
