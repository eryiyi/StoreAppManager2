package com.liangxunwang.unimanager.alipay;

/**
 * Created by zhl on 2016/7/27.
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2016120804020882";

    //商户PID
    public static final String PARTNER = "2088521406306506";

    //商户收款账号
    public static final String SELLER = "1607290150@qq.com";

    /** 商户私钥，pkcs8格式 */
    public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK4QbEms+K0yPIK+" +
            "bSehFXqjooW72Eq0aOZ1CbcSkhu6aVx3bn5s2Ha9UDr6R8ZQVPqGymHGqrH6hRpD" +
            "B1RwaQrVfK+2TQNeAV6q2zEnu2p7qvHwdutxCWfIr4pJU1Iem+2ZY2tblQ3I5ebT" +
            "yP0rsOqoT1OGGOQYURr2Hp1sGqGNAgMBAAECgYEAq2ks2ljmAtkH0dDo+S+eUUk3" +
            "UO8tDlQfQWNcD9bP31JxU0ehngpeGIBRs+KoL9kH9jYqsqLLkb0cukHrw/F23j6O" +
            "S2DUIEea2Et+bdYdnozrFh7nnoXU6Mf6TGs5EWJbCOknkLu7zqneGNIOuyfwZ2Uc" +
            "Z9dRCF0cA7JlN+0n5MECQQDXAP2vrUkUaqTQ4YZZlVaiO4A+MRf8zEG2DzI5SnQ5" +
            "nLh/ZmH8EUkbTUwSm1jgLWPhWQrdifnrDtJYS1GZKE0VAkEAz0EJai5XJVxec1C6" +
            "4kkfz4cvNRQF31c/M7Q3IiP27SibOe0ROthFtvZbjqcep9v4O7/FME9CVs/l4oQr" +
            "BrZQmQJAKkVqmyCRrq7s6ph8hp5i4xCZvSB/29b40Geac0MdKLqUV4/0kc+ANRr0" +
            "avAaADFMYuDTzwyykzFdz7g3Ocp/pQJBALZzjLQ7A2DShhuCMGNQ+H4bhgV2EQ1i" +
            "ozMDLZ4ShyiUnMJL3uUsy4KcMlHM8YHYdhkzY4Z53+7IqpnUvIP2L9ECQQCDHxcM" +
            "9x4GYm3mlJIaV0L7vDwXiUAvTHc8BhamfsW56VojW1JmWcO4KHuHbY0TK8RtN3wf" +
            "HnwFP2k6CQ492Y1T";

    // 支付宝的公钥，无需修改该值
    public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "RSA";



}
