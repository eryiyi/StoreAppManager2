package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.dao.LxClassDao;
import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.query.LikeQuery;
import com.liangxunwang.unimanager.query.NearbyDianpuQuery;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/30.
 */
@Service("appManagerInfoService")
public class AppManagerInfoService implements  FindService,ListService {

    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;
    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String emp_id = (String) object;
            ManagerInfoVo managerInfoVo =  managerInfoDao.getEmpMsg(emp_id);
            if(managerInfoVo!= null){
                if(!StringUtil.isNullOrEmpty(managerInfoVo.getEmp_cover())){
                    if (managerInfoVo.getEmp_cover().startsWith("upload")) {
                        managerInfoVo.setEmp_cover(Constants.URL + managerInfoVo.getEmp_cover());
                    }else {
                        managerInfoVo.setEmp_cover(Constants.QINIU_URL + managerInfoVo.getEmp_cover());
                    }
                }
                if(!StringUtil.isNullOrEmpty(managerInfoVo.getCompany_pic())){
                    if (managerInfoVo.getCompany_pic().startsWith("upload")) {
                        managerInfoVo.setCompany_pic(Constants.URL + managerInfoVo.getCompany_pic());
                    }else {
                        managerInfoVo.setCompany_pic(Constants.QINIU_URL + managerInfoVo.getCompany_pic());
                    }
                }

                //计算星级
                Map<String,Object> map1 = new HashMap<String, Object>();
                map1.put("goods_emp_id", managerInfoVo.getEmp_id());
                DecimalFormat df=new DecimalFormat(".##");
                //全部星级
                Long countAll = goodsCommentDao.countStarNumber(map1);
                //评论数量
                Long countNum = goodsCommentDao.count(map1);
                if(countNum == 0){
                    managerInfoVo.setCompany_star("0");
                }else {
                    managerInfoVo.setCompany_star(String.valueOf(countAll==null?0:countAll/countNum));
                }

            }
            return managerInfoVo;
        }
        return null;
    }


    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Autowired
    @Qualifier("lxClassDao")
    private LxClassDao lxClassDao;


    @Override
    public Object list(Object object) throws ServiceException {
        NearbyDianpuQuery query = (NearbyDianpuQuery) object;
        Map<String,Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getCont())){
            map.put("cont", query.getCont());
        }
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        map.put("index", index);
        map.put("size", size);


        if(!StringUtil.isNullOrEmpty(query.getIs_time())){
            map.put("is_time", query.getIs_time());
        }else
        if(!StringUtil.isNullOrEmpty(query.getIs_count())){
            map.put("is_count", query.getIs_count());
        }else {
            if(!StringUtil.isNullOrEmpty(query.getLat_company())){
                map.put("lat_company", query.getLat_company());
            }
            if(!StringUtil.isNullOrEmpty(query.getLng_company())){
                map.put("lng_company", query.getLng_company());
            }
        }

//        if(!StringUtil.isNullOrEmpty(query.getLx_class_id())){
//            map.put("lx_class_id", query.getLx_class_id());
//        }


        if (!StringUtil.isNullOrEmpty(query.getLx_class_id())) {
            //说明商品类别不为空 ,根据类别ID查询小类别集合
            Map<String, Object> mapType = new HashMap<String, Object>();
            mapType.put("f_lx_class_id", query.getLx_class_id());
            List<LxClass> listTypes= lxClassDao.list(mapType);
            if(listTypes != null && listTypes.size() > 0){
                String[] schoolIds = new String[listTypes.size()];
                for (int i = 0; i < listTypes.size(); i++) {
                    schoolIds[i] = listTypes.get(i).getLx_class_id();
                }
                if(schoolIds != null && schoolIds.length > 0){
                    map.put("schoolIds", schoolIds);
                }
            }else{
                String[] schoolIds ={query.getLx_class_id()};
                map.put("schoolIds", schoolIds);
            }
        }

        if(!StringUtil.isNullOrEmpty(query.getIs_dxk())){
            map.put("is_dxk", query.getIs_dxk());
        }
        List<ManagerInfoVo>  lists = managerInfoDao.listsLocation(map);
        for(ManagerInfoVo managerInfoVo1:lists){
            if(!StringUtil.isNullOrEmpty(managerInfoVo1.getCompany_pic())){
                if (managerInfoVo1.getCompany_pic().startsWith("upload")) {
                    managerInfoVo1.setCompany_pic(Constants.URL + managerInfoVo1.getCompany_pic());
                }else {
                    managerInfoVo1.setCompany_pic(Constants.QINIU_URL + managerInfoVo1.getCompany_pic());
                }
            }

           if(!StringUtil.isNullOrEmpty(managerInfoVo1.getEmp_cover())){
               if (managerInfoVo1.getEmp_cover().startsWith("upload")) {
                   managerInfoVo1.setEmp_cover(Constants.URL + managerInfoVo1.getEmp_cover());
               }else {
                   managerInfoVo1.setEmp_cover(Constants.QINIU_URL + managerInfoVo1.getEmp_cover());
               }
           }

            //计算星级
            Map<String,Object> map1 = new HashMap<String, Object>();
            map1.put("goods_emp_id", managerInfoVo1.getEmp_id());
            DecimalFormat df=new DecimalFormat(".##");
            //全部星级
            Long countAll = goodsCommentDao.countStarNumber(map1);
            //评论数量
            Long countNum = goodsCommentDao.count(map1);
            if(countNum == 0){
                managerInfoVo1.setCompany_star("0");
            }else {
                managerInfoVo1.setCompany_star(String.valueOf(countAll==null?0:countAll/countNum));
            }


        }
        return lists;
    }
}
