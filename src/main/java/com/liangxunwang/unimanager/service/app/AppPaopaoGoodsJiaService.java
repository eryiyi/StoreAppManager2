package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/2.
 */
@Service("appPaopaoGoodsJiaService")
public class AppPaopaoGoodsJiaService implements UpdateService {

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Override
    public Object update(Object object) {
        Map<String, String> map = (Map<String, String>) object;
        String id = map.get("id");
        String goods_is_use = map.get("status");
        paopaoGoodsDao.updatePostionById(id, goods_is_use);
        return null;
    }
}
