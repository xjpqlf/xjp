package ui.zlz.bean;

import java.util.List;

public class AboutMediaBean {
    /**
     * status : true
     * code : 1
     * message : 获取媒体报道信息成功
     * data : {"data":[{"id":"1","title":"新浪媒体","show_img":"/Upload/image/2018-09-28/5badefdb38576.jpg","type":"新浪官方","create_time":"2018-11-01 14:22:08","content":"<p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE软雅黑\", \"STHeiti Light\", \"\\\\534E文细黑\", SimSun, \"\\\\5B8B体\", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);\">第一条 订立、领受在中华人民共和国境内具有法律效力的应税凭证，或者在中华人民共和国境内进行证券交易的单位和个人，为印花税的纳税人，应当依照本法规定缴纳印花税。<\/p><p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE软雅黑\", \"STHeiti Light\", \"\\\\534E文细黑\", SimSun, \"\\\\5B8B体\", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);\">　　第二条 本法所称应税凭证，是指本法所附《印花税税目税率表》规定的书面形式的合同、产权转移书据、营业账簿和权利、许可证照。<\/p><p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE软雅黑\", \"STHeiti Light\", \"\\\\534E文细黑\", SimSun, \"\\\\5B8B体\", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);\">　　第三条 本法所称证券交易，是指在依法设立的证券交易所上市交易或者在国务院批准的其他证券交易场所转让公司股票和以股票为基础发行的存托凭证。<\/p><p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE软雅黑\", \"STHeiti Light\", \"\\\\534E文细黑\", SimSun, \"\\\\5B8B体\", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);\">　　第四条 印花税的税目、税率，依照本法所附《印花税税目税率表》执行。<\/p><p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE软雅黑\", \"STHeiti Light\", \"\\\\534E文细黑\", SimSun, \"\\\\5B8B体\", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);\">　　第五条 印花税的计税依据，按照下列方法确定：<\/p><p>       <br/><\/p>","is_delete":"1"},{"id":"2","title":"媒体报告1","show_img":"/Upload/image/2018-09-29/5baecd55e6fc2.jpg","type":"官方声明","create_time":"2018-11-01 14:21:14","is_delete":"1","content":"<p style=\"margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: \"Microsoft Yahei\", \"\\\\5FAE%u8"}]}
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
             * title : 新浪媒体
             * show_img : /Upload/image/2018-09-28/5badefdb38576.jpg
             * type : 新浪官方
             * create_time : 2018-11-01 14:22:08
             * content : <p style="margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: "Microsoft Yahei", "\\5FAE软雅黑", "STHeiti Light", "\\534E文细黑", SimSun, "\\5B8B体", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);">第一条 订立、领受在中华人民共和国境内具有法律效力的应税凭证，或者在中华人民共和国境内进行证券交易的单位和个人，为印花税的纳税人，应当依照本法规定缴纳印花税。</p><p style="margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: "Microsoft Yahei", "\\5FAE软雅黑", "STHeiti Light", "\\534E文细黑", SimSun, "\\5B8B体", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);">　　第二条 本法所称应税凭证，是指本法所附《印花税税目税率表》规定的书面形式的合同、产权转移书据、营业账簿和权利、许可证照。</p><p style="margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: "Microsoft Yahei", "\\5FAE软雅黑", "STHeiti Light", "\\534E文细黑", SimSun, "\\5B8B体", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);">　　第三条 本法所称证券交易，是指在依法设立的证券交易所上市交易或者在国务院批准的其他证券交易场所转让公司股票和以股票为基础发行的存托凭证。</p><p style="margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: "Microsoft Yahei", "\\5FAE软雅黑", "STHeiti Light", "\\534E文细黑", SimSun, "\\5B8B体", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);">　　第四条 印花税的税目、税率，依照本法所附《印花税税目税率表》执行。</p><p style="margin-top: 0px; margin-bottom: 18px; padding: 0px; color: rgb(77, 79, 83); font-family: "Microsoft Yahei", "\\5FAE软雅黑", "STHeiti Light", "\\534E文细黑", SimSun, "\\5B8B体", Arial, sans-serif; font-size: 18px; letter-spacing: 1px; white-space: normal; background-color: rgb(255, 255, 255);">　　第五条 印花税的计税依据，按照下列方法确定：</p><p>       <br/></p>
             * is_delete : 1
             */

            private String id;
            private String title;
            private String show_img;
            private String type;
            private String create_time;
            private String content;
            private String is_delete;

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

            public String getShow_img() {
                return show_img;
            }

            public void setShow_img(String show_img) {
                this.show_img = show_img;
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

            public String getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }
        }
    }


    /**{
     * status : true
     * code : 1
     * message : 获取公司简介成功
     * data : {"data":{"id":"1",
     * "title":"\u65b0\u6d6a\u5a92\u4f53",
     * "show_img":"\/Upload\/image\/2018-09-28\/5badefdb38576.jpg",
     * "type":"\u65b0\u6d6a\u5b98\u65b9","create_time":"2018-11-01 14:22:08",
     * "content":"1122"}}}
     */


}
