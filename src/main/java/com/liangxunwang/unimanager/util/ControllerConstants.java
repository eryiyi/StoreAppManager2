package com.liangxunwang.unimanager.util;

import com.google.gson.Gson;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.model.tip.SuccessTip;
import com.liangxunwang.unimanager.model.tip.Tip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

public class ControllerConstants {

    public static final String QINIU_AK = "LtZvMnYFblPqiOET0ikH3NAZGk5HeSf8Uudx8LMJ";
    public static final String QINIU_SK = "rytoqBQDQbefjqJ2SW76lzWAnUXcFf6dXQWlnBw6";

    public static final String ACCOUNT_KEY = "account";
    public static final String PERMISSIONS = "powers";
    public static final String ADMIN_KEY ="admin";
    public static final String VERIFY_CODE = "verify_code";
//    public static final String USER_KEY = "user";
//    public static final String PERMISSIONS = "powers";

    public static final int PAGE_SIZE = 20;

    public static final Tip TIMEOUT = new ErrorTip(-1);
    public static final Tip SUCCESS = new SuccessTip();

    public static final Tip ERROR_1 = new ErrorTip(1);
    public static final Tip ERROR_2 = new ErrorTip(2);
    public static final Tip ERROR_3 = new ErrorTip(3);
    public static final Tip ERROR_4 = new ErrorTip(4);
    public static final Tip ERROR_5 = new ErrorTip(5);
    public static final Tip ERROR_6 = new ErrorTip(6);
    public static final Tip ERROR_7 = new ErrorTip(7);
    public static final Tip ERROR_8 = new ErrorTip(8);
    public static final Tip ERROR_9 = new ErrorTip(9);

    private static final Gson JSON_HELPER = new Gson();

    public static String toJSONString(Object object) {
        return JSON_HELPER.toJson(object);
    }

    public static Object fromJson(String json, Type clazz){
        return JSON_HELPER.fromJson(json, clazz);
    }

    public static long calculatePageCount(int size, long count) {
        if (count == 0){
            return 1;
        }
        return count % size == 0 ? count / size : count / size + 1;
    }


    public static String  reBack(String str,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");

        PrintWriter out = response.getWriter();
        out.println(str);
        out.close();
        return null;
    }
}
