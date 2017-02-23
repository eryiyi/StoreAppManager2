package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CardEmpDao;
import com.liangxunwang.unimanager.dao.ManagerInfoDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.CardEmp;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
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

/**
 */
@Service("dianpuExcelService")
public class DianpuExcelService implements ExecuteService {
    @Autowired
    @Qualifier("managerInfoDao")
    private ManagerInfoDao managerInfoDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<ManagerInfoVo> empVOs = new ArrayList<ManagerInfoVo>();
            for(int i=0;i<arrs.length;i++){
                ManagerInfoVo empVO = managerInfoDao.findByIdInfo(arrs[i]);
                if(!StringUtil.isNullOrEmpty(empVO.getDateline())){
                    empVO.setDateline(DateUtil.getDate(empVO.getDateline(), "yyyy-MM-dd HH:mm"));
                }
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelDianpu(empVOs, request);
            return fileName;
        }
        return null;
    }


}
