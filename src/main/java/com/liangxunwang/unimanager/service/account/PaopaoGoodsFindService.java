package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.model.PaopaoGoods;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/8/16.
 */
@Service("paopaoGoodsFindService")
public class PaopaoGoodsFindService implements FindService , UpdateService{
    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Override
    public Object findById(Object object) throws ServiceException {
            String id = (String) object;
            PaopaoGoodsVO vo =  paopaoGoodsDao.findGoodsVO(id);
            if(vo != null){
//                if (!StringUtil.isNullOrEmpty(vo.getCover())) {
//                    if (vo.getCover().startsWith("upload")) {
//                        vo.setCover(Constants.URL + vo.getCover());
//                    }else {
//                        vo.setCover(Constants.QINIU_URL + vo.getCover());
//                    }
//                }
//                if (!StringUtil.isNullOrEmpty(vo.getGoods_cover1())) {
//                    if (vo.getGoods_cover1().startsWith("upload")) {
//                        vo.setGoods_cover1(Constants.URL + vo.getGoods_cover1());
//                    }else {
//                        vo.setGoods_cover1(Constants.QINIU_URL + vo.getGoods_cover1());
//                    }
//                }
//                if (!StringUtil.isNullOrEmpty(vo.getGoods_cover2())) {
//                    if (vo.getGoods_cover2().startsWith("upload")) {
//                        vo.setGoods_cover2(Constants.URL + vo.getGoods_cover2());
//                    }else {
//                        vo.setGoods_cover2(Constants.QINIU_URL + vo.getGoods_cover2());
//                    }
//                }
//                if (!StringUtil.isNullOrEmpty(vo.getEmpCover())) {
//                    if (vo.getEmpCover().startsWith("upload")) {
//                        vo.setEmpCover(Constants.URL + vo.getEmpCover());
//                    }else {
//                        vo.setEmpCover(Constants.QINIU_URL + vo.getEmpCover());
//                    }
//                }
//                if (!StringUtil.isNullOrEmpty(vo.getManagerCover())) {
//                    if (vo.getEmpCover().startsWith("upload")) {
//                        vo.setManagerCover(Constants.URL + vo.getManagerCover());
//                    }else {
//                        vo.setManagerCover(Constants.QINIU_URL + vo.getManagerCover());
//                    }
//                }
                if(!StringUtil.isNullOrEmpty(vo.getUpTime())){
                    vo.setUpTime(RelativeDateFormat.format(Long.parseLong(vo.getUpTime())));
                }
            }

            return vo;
    }

    @Override
    public Object update(Object object) {
        PaopaoGoods goods = (PaopaoGoods) object;
        paopaoGoodsDao.updatePosition(goods);
        return 200;
    }
}
