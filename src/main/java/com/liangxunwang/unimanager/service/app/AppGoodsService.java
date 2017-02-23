package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.LikeQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appGoodsService")
public class AppGoodsService implements ListService {
    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LikeQuery query = (LikeQuery) object;
        Map<String,Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getCont())){
            map.put("cont", query.getCont());
        }
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        map.put("index", index);
        map.put("size", size);

        List<PaopaoGoodsVO> lists= paopaoGoodsDao.listLikes(map);
        for (PaopaoGoodsVO vo : lists) {
            //处理图片URL链接
//            StringBuffer buffer = new StringBuffer();
//            String[] pics = new String[]{};
//            if (vo != null && vo.getCover() != null) {
//                pics = vo.getCover().split(",");
//            }
//            for (int i = 0; i < pics.length; i++) {
//                if (pics[i].startsWith("upload")) {
//                    buffer.append(Constants.URL + pics[i]);
//                    if (i < pics.length - 1) {
//                        buffer.append(",");
//                    }
//                } else {
//                    buffer.append(Constants.QINIU_URL + pics[i]);
//                    if (i < pics.length - 1) {
//                        buffer.append(",");
//                    }
//                }
//            }
//            vo.setCover(buffer.toString());
            if (!StringUtil.isNullOrEmpty(vo.getEmpCover())) {
                if (vo.getEmpCover().startsWith("upload")) {
                    vo.setEmpCover(Constants.URL + vo.getEmpCover());
                } else {
                    vo.setEmpCover(Constants.QINIU_URL + vo.getEmpCover());
                }
            }
            if (!StringUtil.isNullOrEmpty(vo.getManagerCover())) {
                if (vo.getEmpCover().startsWith("upload")) {
                    vo.setManagerCover(Constants.URL + vo.getManagerCover());
                } else {
                    vo.setManagerCover(Constants.QINIU_URL + vo.getManagerCover());
                }
            }

            if (!StringUtil.isNullOrEmpty(vo.getGoods_cover1())) {
                if (vo.getGoods_cover1().startsWith("upload")) {
                    vo.setGoods_cover1(Constants.URL + vo.getGoods_cover1());
                } else {
                    vo.setGoods_cover1(Constants.QINIU_URL + vo.getGoods_cover1());
                }
            }

            if (!StringUtil.isNullOrEmpty(vo.getGoods_cover2())) {
                if (vo.getGoods_cover2().startsWith("upload")) {
                    vo.setGoods_cover2(Constants.URL + vo.getGoods_cover2());
                } else {
                    vo.setGoods_cover2(Constants.QINIU_URL + vo.getGoods_cover2());
                }
            }

            if (!StringUtil.isNullOrEmpty(vo.getCover())) {
                if (vo.getCover().startsWith("upload")) {
                    vo.setCover(Constants.URL + vo.getCover());
                } else {
                    vo.setCover(Constants.QINIU_URL + vo.getCover());
                }
            }
            vo.setUpTime(RelativeDateFormat.format(Long.parseLong(vo.getUpTime())));
        }
        return lists;
    }
}
