package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxbankApplyDao;
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

@Service("bankApplyExcelService")
public class BankApplyExcelService implements ExecuteService {
    @Autowired
    @Qualifier("lxbankApplyDao")
    private LxbankApplyDao lxbankApplyDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<lxBankApplyVo> empVOs = new ArrayList<lxBankApplyVo>();
            for(int i=0;i<arrs.length;i++){
                lxBankApplyVo empVO = lxbankApplyDao.findById(arrs[i]);
                if(!StringUtil.isNullOrEmpty(empVO.getDateline_apply())){
                    empVO.setDateline_apply(DateUtil.getDate(empVO.getDateline_apply(), "yyyy-MM-dd HH:mm"));
                }
                if(!StringUtil.isNullOrEmpty(empVO.getDateline_done())){
                    empVO.setDateline_done(DateUtil.getDate(empVO.getDateline_done(), "yyyy-MM-dd HH:mm"));
                }
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelBankApply(empVOs, request);
            return fileName;
        }
        return null;
    }

}
