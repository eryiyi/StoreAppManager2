package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.NoticeHoutaiDao;
import com.liangxunwang.unimanager.model.NoticeHoutai;
import com.liangxunwang.unimanager.query.NoticeHoutaiQuery;
import com.liangxunwang.unimanager.service.*;
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
@Service("noticeHoutaiService")
public class NoticeHoutaiService implements ListService,SaveService,UpdateService,DeleteService,FindService {
    @Autowired
    @Qualifier("noticeHoutaiDao")
    private NoticeHoutaiDao noticeHoutaiDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NoticeHoutaiQuery query = (NoticeHoutaiQuery
                ) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getManager_id())){
            map.put("manager_id", query.getManager_id());
        }

        List<NoticeHoutai> lists = noticeHoutaiDao.lists(map);
        long count = noticeHoutaiDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        NoticeHoutai noticeHoutai = (NoticeHoutai) object;
        noticeHoutai.setNotice_id(UUIDFactory.random());
        noticeHoutai.setDateline(System.currentTimeMillis() + "");
        noticeHoutaiDao.save(noticeHoutai);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        noticeHoutaiDao.delete((String) object);
        return 200;
    }

    @Override
    public Object update(Object object) {
        NoticeHoutai noticeHoutai = (NoticeHoutai) object;
        noticeHoutaiDao.update(noticeHoutai);
        return null;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        NoticeHoutai noticeHoutai = noticeHoutaiDao.findById((String) object);
        return noticeHoutai;
    }
}
