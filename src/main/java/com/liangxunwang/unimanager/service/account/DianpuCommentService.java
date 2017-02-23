package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DianpuCommentDao;
import com.liangxunwang.unimanager.model.DianpuComment;
import com.liangxunwang.unimanager.query.DianpuCommentQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("dianpuCommentService")
public class DianpuCommentService implements ListService,SaveService,DeleteService,FindService {
    @Autowired
    @Qualifier("dianpuCommentDao")
    private DianpuCommentDao dianpuCommentDao;

    @Override
    public Object list(Object object) throws ServiceException {
        DianpuCommentQuery query = (DianpuCommentQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id_seller())){
            map.put("emp_id_seller", query.getEmp_id_seller());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }

        if(!StringUtil.isNullOrEmpty(query.getEmp_mobile())){
            map.put("emp_mobile", query.getEmp_mobile());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }

        List<DianpuComment> lists = dianpuCommentDao.lists(map);
        if(lists != null){
            for(DianpuComment dianPuFavour:lists){

                if(!StringUtil.isNullOrEmpty(dianPuFavour.getEmp_cover())){
                    if(dianPuFavour.getEmp_cover().startsWith("upload")){
                        dianPuFavour.setEmp_cover(Constants.URL + dianPuFavour.getEmp_cover());
                    }else {
                        dianPuFavour.setEmp_cover(Constants.QINIU_URL + dianPuFavour.getEmp_cover());
                    }
                }
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if (dianPuFavour != null && dianPuFavour.getDianpu_comment_pic() != null) {
                    pics = dianPuFavour.getDianpu_comment_pic().split(",");
                }
                for (int i = 0; i < pics.length; i++) {
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    } else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                dianPuFavour.setDianpu_comment_pic(buffer.toString());
                if(!StringUtil.isNullOrEmpty(dianPuFavour.getComment_dateline())){
                    dianPuFavour.setComment_dateline(RelativeDateFormat.format(Long.parseLong(dianPuFavour.getComment_dateline())));
                }
            }
        }
        long count = dianpuCommentDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        DianpuComment dianpuComment = (DianpuComment) object;
        dianpuComment.setDianpu_comment_id(UUIDFactory.random());
        dianpuComment.setComment_dateline(System.currentTimeMillis() + "");
        dianpuCommentDao.save(dianpuComment);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String dianpu_comment_id = (String) object;
        dianpuCommentDao.delete(dianpu_comment_id);
        return null;
    }



    @Override
    public Object findById(Object object) throws ServiceException {
        DianpuComment dianPuFavour = dianpuCommentDao.findById((String) object);
        if(!StringUtil.isNullOrEmpty(dianPuFavour.getEmp_cover())){
            if(dianPuFavour.getEmp_cover().startsWith("upload")){
                dianPuFavour.setEmp_cover(Constants.URL + dianPuFavour.getEmp_cover());
            }else {
                dianPuFavour.setEmp_cover(Constants.QINIU_URL + dianPuFavour.getEmp_cover());
            }
        }
        StringBuffer buffer = new StringBuffer();
        String[] pics = new String[]{};
        if (dianPuFavour != null && dianPuFavour.getDianpu_comment_pic() != null) {
            pics = dianPuFavour.getDianpu_comment_pic().split(",");
        }
        for (int i = 0; i < pics.length; i++) {
            if (pics[i].startsWith("upload")) {
                buffer.append(Constants.URL + pics[i]);
                if (i < pics.length - 1) {
                    buffer.append(",");
                }
            } else {
                buffer.append(Constants.QINIU_URL + pics[i]);
                if (i < pics.length - 1) {
                    buffer.append(",");
                }
            }
        }
        dianPuFavour.setDianpu_comment_pic(buffer.toString());
        if(!StringUtil.isNullOrEmpty(dianPuFavour.getComment_dateline())){
            dianPuFavour.setComment_dateline(RelativeDateFormat.format(Long.parseLong(dianPuFavour.getComment_dateline())));
        }

        return dianPuFavour;
    }
}
