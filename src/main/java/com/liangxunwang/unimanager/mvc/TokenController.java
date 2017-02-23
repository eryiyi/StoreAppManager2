package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.data.TokenData;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/9/22.
 */
@Controller
public class TokenController extends ControllerConstants{
    @RequestMapping("/token")
    @ResponseBody
    public String token(String space, String key){
        Auth auth = Auth.create(QINIU_AK, QINIU_SK);
        StringMap map = new StringMap().put("persistentOps","vframe/jpg/offset/1/w/480/h/360");
        String token = "";
        if (StringUtil.isNullOrEmpty(key)) {
            token = auth.uploadToken(space,key, 100, map);
        }else {
            token = auth.uploadToken(space, key, 100, map);
        }
        TokenData tokenData = new TokenData();
        tokenData.setData(token);
        return toJSONString(tokenData);
    }
}
