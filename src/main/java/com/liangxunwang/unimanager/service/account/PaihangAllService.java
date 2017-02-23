package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("paihangAllService")
public class PaihangAllService implements ListService {
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        if (!StringUtil.isNullOrEmpty(query.getIs_del())) {
            map.put("is_del", query.getIs_del());
        }

        List<PaihangObjVO> lists = paihangObjDao.listRecordVoAll(map);

        return lists;
    }


}
