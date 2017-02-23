package com.liangxunwang.unimanager.util;

/**
 * Created by zhl on 2015/1/29.
 */
public class Constants {
    //服务器地址

//      public static final String URL = "http://192.168.0.225:8080/";
    public static final String URL = "http://157j1274e3.iask.in/";
//        public static final String URL = "http://114.215.41.142:8080/";

    public static final String DOWNLOAD_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.lbins.myapp";
    //注册用默认会员等级--青铜会员
    public static final String DEFAULT_LEVEL = "d42535ff62e147ae80dba7bc9d8ea0d4";

    //招商部ID
    public static final String TOP_DXK_LEVEL = "1111ea9a3c0849dc9d4c0c0a215adff6";

    //七牛云存储
    public static final String QINIU_URL = "http://7xt74j.com1.z0.glb.clouddn.com/";
    public static final String QINIU_SPACE = "paopao-pic";
    public static final String FILE_PATH = "D://recordfile";

    //百度推送
    public static final String API_KEY = "Y2YvCibBy54Tathk9FkDCDVe";
    public static final String SECRET_KEY = "C926MVuk6yOOPdm0ObsLOVEnsGCwLsYV";
    public static final String IOS_API_KEY = "iQADn1Ngzxq6CzCCmj26z3Pm";
    public static final String IOS_SECRET_KEY = "DeUu6dlWUFKtWSHgu9i3NGUiQC4T72wz";

    public static final int IOS_TYPE = 1;

    public static final String SAVE_ERROR = "save_error";

    public static final String HAS_ZAN = "has_zan";
    public static final String MOBILE_UP_DEFAULT = "10000000000";
    public static final String MOBILE_UP_DEFAULT_id = "b530cca19dba4509867477a3d9fc85d1";

    public static final String HAS_CODE = "has_code";

    public static final String NO_SEND_CODE = "no_send_code";

    public static final String SEND_SMS_ERROR = "send_sms_error";

    public static final String HAS_EXISTS = "has_exists";

    public static final String TOO_MANY_CODE = "too_many_code";

    public static final String CODE_NOT_EQUAL = "code_not_equal";

    public static final String PHONE_ERROR = "phone_error";
    public static final String HX_ERROR = "hx_error";

    public static final String SMS_MESSAGE_URL = "http://60.209.7.78:8080/smsServer/submit";

    //默认头像
    public static final String[] PHOTOURLS = {
            "upload/pic1.jpg",
            "upload/pic2.jpg",
            "upload/pic3.jpg",
            "upload/pic4.jpg",
            "upload/pic5.jpg",
            "upload/pic6.jpg",
            "upload/pic7.jpg",
            "upload/pic8.jpg",
            "upload/pic9.jpg",
            "upload/pic10.jpg",
            "upload/pic11.jpg",
            "upload/pic12.jpg",
            "upload/pic13.jpg",
            "upload/pic14.jpg",
            "upload/pic15.jpg",
            "upload/pic16.jpg",
            "upload/pic17.jpg",
            "upload/pic18.jpg",
            "upload/pic19.jpg",
            "upload/pic20.jpg",
            "upload/pic21.jpg",
            "upload/pic22.jpg",
            "upload/pic23.jpg",
            "upload/pic24.jpg",
            "upload/pic25.jpg",
            "upload/pic26.jpg",
            "upload/pic27.jpg",
            "upload/pic28.jpg",
            "upload/pic29.jpg",
            "upload/pic30.jpg",
            "upload/pic31.jpg",
            "upload/pic32.jpg",
            "upload/pic33.jpg",
            "upload/pic34.jpg",
            "upload/pic35.jpg",
            "upload/pic36.jpg",
            "upload/pic37.jpg",
            "upload/pic38.jpg",
            "upload/pic39.jpg",
            "upload/pic40.jpg",
            "upload/pic41.jpg",
            "upload/pic42.jpg",
            "upload/pic43.jpg",
            "upload/pic44.jpg",
            "upload/pic45.jpg",
            "upload/pic46.jpg",
            "upload/pic47.jpg",
            "upload/pic48.jpg",
            "upload/pic49.jpg",
            "upload/pic50.jpg",
            "upload/pic51.jpg",
            "upload/pic52.jpg",
            "upload/pic53.jpg",
            "upload/pic54.jpg",
            "upload/pic55.jpg",
            "upload/pic56.jpg",
            "upload/pic57.jpg",
            "upload/pic58.jpg",
            "upload/pic59.jpg",
            "upload/pic60.jpg",
            "upload/pic61.jpg"
    };

    public static final Long DAY_MILLISECOND = 86400000L;

    //----------------支付宝------------------

    //微信统一下单notify_url
    public static final String WEIXIN_NOTIFY_URL = URL + "payWxNotifyAction.do";
    public static final String WEIXIN_NOTIFY_URL_DXK = URL + "payWxNotifyActionDxk.do";
    public static final String WEIXIN_NOTIFY_URL_LQ = URL + "payWxNotifyActionLq.do";

    //支付宝回调页面
    public static final String ZFB_NOTIFY_URL = URL + "pay/notify_url_alipay.jsp";

    //原先的
//    public static final String WX_APP_ID = "wx9769250919c81901";//yum
//    public static final String WX_MCH_ID = "1393020902";//yum
//    public static final  String WX_API_KEY="PnG4IEkvkqfIDT0UJisgwDDCoxP3kvGH";//yum

    //后来的
    //appid
    public static final String WX_APP_ID = "wxa86f64fcca9f3806";//yum
    //商户号
    public static final String WX_MCH_ID = "1424464002";//yum
    //  API密钥，在商户平台设置
    public static final  String WX_API_KEY="13473ea7a46ba9238cf1ecaa6cad26a1";//yum

    public static final  String WX_APP_SECRET="611fcae2cb0a43381be9ee527de1c406";//yum
//        7289232292675542ceb8e6d7b68015ef
}
