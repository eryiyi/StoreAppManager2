package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/16.
 * 查询全部商品
 */
@Service("paopaoGoodsListAllService")
public class PaopaoGoodsListAllService implements ListService {
    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;


    @Override
    public Object list(Object object) throws ServiceException {
            PaopaoGoodsQuery query = (PaopaoGoodsQuery) object;
            Map<String, Object> map = new HashMap<String, Object>();

        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

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
            if (!StringUtil.isNullOrEmpty(query.getManager_id())) {
                map.put("manager_id", query.getManager_id());
            }
            List<PaopaoGoodsVO> list = paopaoGoodsDao.listGoodsAll(map);
            for (PaopaoGoodsVO vo : list) {
//                //处理图片URL链接
//                StringBuffer buffer = new StringBuffer();
//                String[] pics = new String[]{};
//                if (vo != null && vo.getCover() != null) {
//                    pics = vo.getCover().split(",");
//                }
//                for (int i = 0; i < pics.length; i++) {
//                    if (pics[i].startsWith("upload")) {
//                        buffer.append(Constants.URL + pics[i]);
//                        if (i < pics.length - 1) {
//                            buffer.append(",");
//                        }
//                    } else {
//                        buffer.append(Constants.QINIU_URL + pics[i]);
//                        if (i < pics.length - 1) {
//                            buffer.append(",");
//                        }
//                    }
//                }
//                vo.setCover(buffer.toString());
//                if (!StringUtil.isNullOrEmpty(vo.getEmpCover())) {
//                    if (vo.getEmpCover().startsWith("upload")) {
//                        vo.setEmpCover(Constants.URL + vo.getEmpCover());
//                    } else {
//                        vo.setEmpCover(Constants.QINIU_URL + vo.getEmpCover());
//                    }
//                }
//                if (!StringUtil.isNullOrEmpty(vo.getManagerCover())) {
//                    if (vo.getEmpCover().startsWith("upload")) {
//                        vo.setManagerCover(Constants.URL + vo.getManagerCover());
//                    } else {
//                        vo.setManagerCover(Constants.QINIU_URL + vo.getManagerCover());
//                    }
//                }
                vo.setUpTime(RelativeDateFormat.format(Long.parseLong(vo.getUpTime())));
            }
            return list;
        }
}
