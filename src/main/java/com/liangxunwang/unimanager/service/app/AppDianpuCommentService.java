package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.DianpuCommentDao;
import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.model.DianpuComment;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.query.DianpuCommentQuery;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appDianpuCommentService")
public class AppDianpuCommentService implements FindService,ListService {

    @Autowired
    @Qualifier("dianpuCommentDao")
    private DianpuCommentDao dianpuCommentDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        String emp_id_seller = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("emp_id_seller", emp_id_seller);
        DecimalFormat df=new DecimalFormat(".##");
        //全部星级
        Long countAll = dianpuCommentDao.countStarNumber(map);
        //评论数量
        Long countNum = dianpuCommentDao.count(map);

        Double aDouble =(double)(Math.round(Double.valueOf(countAll==null?0:countAll)/Double.valueOf(countNum))/100.0)*2*1000;//这样为保持2位

        return new Object[] {countNum, aDouble};
    }

    @Override
    public Object list(Object object) throws ServiceException {
        DianpuCommentQuery query = (DianpuCommentQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id_seller())){
            map.put("emp_id_seller", query.getEmp_id_seller());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        List<DianpuComment> list = dianpuCommentDao.lists(map);
        for (DianpuComment vo : list){
            if(!StringUtil.isNullOrEmpty(vo.getEmp_cover())){
                if (vo.getEmp_cover().startsWith("upload")) {
                    vo.setEmp_cover(Constants.URL + vo.getEmp_cover());
                }else {
                    vo.setEmp_cover(Constants.QINIU_URL + vo.getEmp_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(vo.getEmp_cover_seller())){
                if (vo.getEmp_cover_seller().startsWith("upload")) {
                    vo.setEmp_cover_seller(Constants.URL + vo.getEmp_cover_seller());
                }else {
                    vo.setEmp_cover_seller(Constants.QINIU_URL + vo.getEmp_cover_seller());
                }
            }

            if(!StringUtil.isNullOrEmpty(vo.getDianpu_comment_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(vo!=null && vo.getDianpu_comment_pic()!=null){
                    pics = vo.getDianpu_comment_pic().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                vo.setDianpu_comment_pic(buffer.toString());
            }

            if(!StringUtil.isNullOrEmpty(vo.getComment_dateline())){
                vo.setComment_dateline(RelativeDateFormat.format(Long.parseLong(vo.getComment_dateline())));
            }

        }
        return list;
    }
}
