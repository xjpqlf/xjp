package ui.zlz.bean;

import java.util.List;

public class QLoginBean {
    /**
     * status : true
     * code : 1
     * message : 登录成功！
     * data : {"data":{"openid":"oRu2q1Tcp29SjW5vMCVpivYPjBt4","nickname":"柘林莫","sex":1,"language":"zh_CN","city":"Wuhan","province":"Hubei","country":"CN","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/5I8BQJbGNDo8tXnF7dLVviburILjXCzMWbroyEw6jADm05s22VcniahvWbaMoPA180bBANVR9l9hcWYaTrol47Jg/132","privilege":[],"unionid":"oLBvh0nM44Fcany62HCXRztZsECo"},"session_token":"58d540baf0c5e11242023ba923b053b9","ispay_pwd":0}
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
         * data : {"openid":"oRu2q1Tcp29SjW5vMCVpivYPjBt4","nickname":"柘林莫","sex":1,"language":"zh_CN","city":"Wuhan","province":"Hubei","country":"CN","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/5I8BQJbGNDo8tXnF7dLVviburILjXCzMWbroyEw6jADm05s22VcniahvWbaMoPA180bBANVR9l9hcWYaTrol47Jg/132","privilege":[],"unionid":"oLBvh0nM44Fcany62HCXRztZsECo"}
         * session_token : 58d540baf0c5e11242023ba923b053b9
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
             * openid : oRu2q1Tcp29SjW5vMCVpivYPjBt4
             * nickname : 柘林莫
             * sex : 1
             * language : zh_CN
             * city : Wuhan
             * province : Hubei
             * country : CN
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/5I8BQJbGNDo8tXnF7dLVviburILjXCzMWbroyEw6jADm05s22VcniahvWbaMoPA180bBANVR9l9hcWYaTrol47Jg/132
             * privilege : []
             * unionid : oLBvh0nM44Fcany62HCXRztZsECo
             */

            private String openid;
            private String nickname;
            private int sex;
            private String language;
            private String city;
            private String province;
            private String country;
            private String headimgurl;
            private String unionid;
            private List<?> privilege;

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public List<?> getPrivilege() {
                return privilege;
            }

            public void setPrivilege(List<?> privilege) {
                this.privilege = privilege;
            }
        }
    }

    /**
     * status : true
     * code : 1
     * message : 登录成功！
     * data : {"data":{"ret":0,"msg":"","is_lost":0,"nickname":"O.0","gender":"男","province":"湖北","city":"武汉","year":"1990","constellation":"","figureurl":"http://qzapp.qlogo.cn/qzapp/1107959498/CFF7F4BC8F373EAE7C72F067B095C678/30","figureurl_1":"http://qzapp.qlogo.cn/qzapp/1107959498/CFF7F4BC8F373EAE7C72F067B095C678/50","figureurl_2":"http://qzapp.qlogo.cn/qzapp/1107959498/CFF7F4BC8F373EAE7C72F067B095C678/100","figureurl_qq_1":"http://thirdqq.qlogo.cn/qqapp/1107959498/CFF7F4BC8F373EAE7C72F067B095C678/40","figureurl_qq_2":"http://thirdqq.qlogo.cn/qqapp/1107959498/CFF7F4BC8F373EAE7C72F067B095C678/100","is_yellow_vip":"0","vip":"0","yellow_vip_level":"0","level":"0","is_yellow_year_vip":"0"},"session_token":"9bc1b3ed3612acfd4139a583b37bb012","ispay_pwd":0}
     */

}
