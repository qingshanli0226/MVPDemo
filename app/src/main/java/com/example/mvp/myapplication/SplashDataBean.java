package com.example.mvp.myapplication;

public class SplashDataBean {

    private int retCode;
    private SplashBean splash;
    private String requestId;
    private String retMsg;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public SplashBean getSplash() {
        return splash;
    }

    public void setSplash(SplashBean splash) {
        this.splash = splash;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    /**
     * retCode : 0
     * splash : {"isDownload":"1","resolutionWidth":"1080","jopUrl":"https://h5.m.taopiaopiao.com/app/moviemain/pages/show-detail/index.html?showid=226358","osType":"2","contentType":"0","endTime":"1519401540","type":"0","transparency":"0","id":"5","displayTime":"3.0","title":"安卓分辨率1080*1920","ifShading":"1","resolutionHeight":"1920","imgurl":"http://img.moviebase.cn/img/other/2018/02/4cc9281c1e544514a71dd408e99ac3ca.jpg"}
     * requestId : 069ca790-d693-4326-98ec-1d14129abfc2
     * retMsg :
     */


    public static class SplashBean {
        /**
         * isDownload : 1
         * resolutionWidth : 1080
         * jopUrl : https://h5.m.taopiaopiao.com/app/moviemain/pages/show-detail/index.html?showid=226358
         * osType : 2
         * contentType : 0
         * endTime : 1519401540
         * type : 0
         * transparency : 0
         * id : 5
         * displayTime : 3.0
         * title : 安卓分辨率1080*1920
         * ifShading : 1
         * resolutionHeight : 1920
         * imgurl : http://img.moviebase.cn/img/other/2018/02/4cc9281c1e544514a71dd408e99ac3ca.jpg
         */

        private String isDownload;
        private String resolutionWidth;
        private String jopUrl;
        private String osType;
        private String contentType;
        private String endTime;
        private String type;
        private String transparency;
        private String id;
        private String displayTime;
        private String title;
        private String ifShading;
        private String resolutionHeight;
        private String imgurl;

        public String getIsDownload() {
            return isDownload;
        }

        public void setIsDownload(String isDownload) {
            this.isDownload = isDownload;
        }

        public String getResolutionWidth() {
            return resolutionWidth;
        }

        public void setResolutionWidth(String resolutionWidth) {
            this.resolutionWidth = resolutionWidth;
        }

        public String getJopUrl() {
            return jopUrl;
        }

        public void setJopUrl(String jopUrl) {
            this.jopUrl = jopUrl;
        }

        public String getOsType() {
            return osType;
        }

        public void setOsType(String osType) {
            this.osType = osType;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTransparency() {
            return transparency;
        }

        public void setTransparency(String transparency) {
            this.transparency = transparency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayTime() {
            return displayTime;
        }

        public void setDisplayTime(String displayTime) {
            this.displayTime = displayTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIfShading() {
            return ifShading;
        }

        public void setIfShading(String ifShading) {
            this.ifShading = ifShading;
        }

        public String getResolutionHeight() {
            return resolutionHeight;
        }

        public void setResolutionHeight(String resolutionHeight) {
            this.resolutionHeight = resolutionHeight;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
