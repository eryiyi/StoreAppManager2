package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.BrowsingDao;
import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.model.BrowsingObj;
import com.liangxunwang.unimanager.model.PaopaoGoods;
import com.liangxunwang.unimanager.mvc.vo.BrowsingVo;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/16.
 */
@Service("paopaoGoodsService")
public class PaopaoGoodsService implements ListService, SaveService, DeleteService, FindService, UpdateService {
    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object delete(Object object) throws ServiceException {
        if (object instanceof String){
            String id = (String) object;
            paopaoGoodsDao.deleteById(id);
            //删除商品之后，要记得删除推荐的产品
            paihangObjDao.deleteByGoodsId(id);
        }
        return null;
    }

    @Autowired
    @Qualifier("browsingDao")
    private BrowsingDao browsingDao;

    @Override
    public Object findById(Object object) throws ServiceException {
            Object[] params = (Object[]) object;
            String id = (String) params[0];
            String empid = (String) params[1];
            PaopaoGoodsVO vo =  paopaoGoodsDao.findGoodsVO(id);
            if(vo != null){
                if (!StringUtil.isNullOrEmpty(vo.getCover())) {
                    if (vo.getCover().startsWith("upload")) {
                        vo.setCover(Constants.URL + vo.getCover());
                    }else {
                        vo.setCover(Constants.QINIU_URL + vo.getCover());
                    }
                }
                if (!StringUtil.isNullOrEmpty(vo.getGoods_cover1())) {
                    if (vo.getGoods_cover1().startsWith("upload")) {
                        vo.setGoods_cover1(Constants.URL + vo.getGoods_cover1());
                    }else {
                        vo.setGoods_cover1(Constants.QINIU_URL + vo.getGoods_cover1());
                    }
                }
                if (!StringUtil.isNullOrEmpty(vo.getGoods_cover2())) {
                    if (vo.getGoods_cover2().startsWith("upload")) {
                        vo.setGoods_cover2(Constants.URL + vo.getGoods_cover2());
                    }else {
                        vo.setGoods_cover2(Constants.QINIU_URL + vo.getGoods_cover2());
                    }
                }
                if (!StringUtil.isNullOrEmpty(vo.getEmpCover())) {
                    if (vo.getEmpCover().startsWith("upload")) {
                        vo.setEmpCover(Constants.URL + vo.getEmpCover());
                    }else {
                        vo.setEmpCover(Constants.QINIU_URL + vo.getEmpCover());
                    }
                }
                if (!StringUtil.isNullOrEmpty(vo.getManagerCover())) {
                    if (vo.getEmpCover().startsWith("upload")) {
                        vo.setManagerCover(Constants.URL + vo.getManagerCover());
                    }else {
                        vo.setManagerCover(Constants.QINIU_URL + vo.getManagerCover());
                    }
                }
                if(vo != null){
                    if(!StringUtil.isNullOrEmpty(vo.getUpTime())){
                        vo.setUpTime(RelativeDateFormat.format(Long.parseLong(vo.getUpTime())));
                    }
                }
            }
            //插入浏览记录
            if(!StringUtil.isNullOrEmpty(empid) && !StringUtil.isNullOrEmpty(id)){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("index", 0);
                map.put("size", 10);
                map.put("empid", empid);
                map.put("goodsid", id);
                List<BrowsingVo> lists = browsingDao.lists(map);
                BrowsingObj browsingObj = new BrowsingObj();
                if(lists != null && lists.size()>0){
                    //说明已经存在 更新记录
                    browsingObj.setBrowsingid(lists.get(0).getBrowsingid());
                    browsingObj.setDateline(System.currentTimeMillis()+"");
                    browsingDao.update(browsingObj);
                }else {
                    browsingObj.setBrowsingid(UUIDFactory.random());
                    browsingObj.setEmpid(empid);
                    browsingObj.setGoodsid(id);
                    browsingObj.setDateline(System.currentTimeMillis()+"");
                    //根据商品id查询商家id
//                    emp_id_dianpu
                    PaopaoGoodsVO paopaoGoodsVO = paopaoGoodsDao.findGoodsVO(id);
                    if(paopaoGoodsVO != null){
                        browsingObj.setEmp_id_dianpu(paopaoGoodsVO.getEmpId());
                    }
                    browsingDao.save(browsingObj);
                }
            }
            return vo;
    }

    @Override
    public Object list(Object object) throws ServiceException {
            PaopaoGoodsQuery query = (PaopaoGoodsQuery) object;
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("index", index);
            map.put("size", size);
            if (!StringUtil.isNullOrEmpty(query.getTypeId())) {
                map.put("typeId", query.getTypeId());
            }
            if (!StringUtil.isNullOrEmpty(query.getCont())) {
                map.put("cont", query.getCont());
            }
            if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
                map.put("empId", query.getEmpId());
            }
            if (!StringUtil.isNullOrEmpty(query.getIs_zhiying())) {
                map.put("is_zhiying", query.getIs_zhiying());
            }

            if (!StringUtil.isNullOrEmpty(query.getManager_id())) {
                map.put("manager_id", query.getManager_id());
            }

            if (!StringUtil.isNullOrEmpty(query.getIsUse())) {
                    map.put("isUse", query.getIsUse());
            }
            if (!StringUtil.isNullOrEmpty(query.getIs_dxk())) {
                    map.put("is_dxk", query.getIs_dxk());
            }

            //查询的是我的商品
            if (!StringUtil.isNullOrEmpty(query.getIsMine()) && query.getIsMine().equals("1")) {
                //查询会员下的所有商品
                if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
                    map.put("empId", query.getEmpId());
                }
            }

            if (!StringUtil.isNullOrEmpty(query.getGoods_position())) {
                map.put("goods_position", query.getGoods_position());
            }else {
                if (!StringUtil.isNullOrEmpty(query.getIs_time())) {
                    map.put("is_time", query.getIs_time());
                }else {
                    if (!StringUtil.isNullOrEmpty(query.getDianpu_number())) {
                        map.put("dianpu_number", query.getDianpu_number());
                    }
                }
            }

            List<PaopaoGoodsVO> list = paopaoGoodsDao.listGoods(map);
        if(list != null){
            for(PaopaoGoodsVO paopaoGoods:list){
                if(!StringUtil.isNullOrEmpty(paopaoGoods.getMm_paihang_id())){
                    //说明推荐了
                    paopaoGoods.setIs_tuijian("1");
                }else {
                    paopaoGoods.setIs_tuijian("0");
                }
            }
        }
            long count = paopaoGoodsDao.count(map);
            return new Object[]{list, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
            PaopaoGoods goods = (PaopaoGoods) object;
            goods.setId(UUIDFactory.random());
            goods.setIsUse("0");
            goods.setIsDel("0");
            goods.setUpTime(System.currentTimeMillis() + "");
            paopaoGoodsDao.save(goods);
            return null;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof PaopaoGoods){
            PaopaoGoods goods = (PaopaoGoods) object;
            paopaoGoodsDao.update(goods);
        }
        return null;
    }
}
