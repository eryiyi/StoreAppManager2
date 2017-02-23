package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DianpuCommentDao;
import com.liangxunwang.unimanager.model.DianpuComment;
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

@Service("dianpuCommentsExcelService")
public class DianpuCommentsExcelService implements ExecuteService {
    @Autowired
    @Qualifier("dianpuCommentDao")
    private DianpuCommentDao dianpuCommentDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<DianpuComment> empVOs = new ArrayList<DianpuComment>();
            for(int i=0;i<arrs.length;i++){
                DianpuComment empVO = dianpuCommentDao.findById(arrs[i]);
                if(!StringUtil.isNullOrEmpty(empVO.getComment_dateline())){
                    empVO.setComment_dateline(DateUtil.getDate(empVO.getComment_dateline(), "yyyy-MM-dd HH:mm"));
                }
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelDianpusComment(empVOs, request);
            return fileName;
        }
        return null;
    }

}
