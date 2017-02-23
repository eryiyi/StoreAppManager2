package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.dao.LxbankApplyDao;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.mvc.vo.lxBankApplyVo;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.CreateSimpleExcelToDisk;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("goodsCommentsExcelService")
public class GoodsCommentsExcelService implements ExecuteService {
    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<GoodsComment> empVOs = new ArrayList<GoodsComment>();
            for(int i=0;i<arrs.length;i++){
                GoodsComment empVO = goodsCommentDao.find(arrs[i]);
                if(!StringUtil.isNullOrEmpty(empVO.getDateline())){
                    empVO.setDateline(DateUtil.getDate(empVO.getDateline(), "yyyy-MM-dd HH:mm"));
                }
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelGoodsComment(empVOs, request);
            return fileName;
        }
        return null;
    }

}
