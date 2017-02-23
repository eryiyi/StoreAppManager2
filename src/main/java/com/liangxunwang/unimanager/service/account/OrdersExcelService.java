package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DianpuCommentDao;
import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.model.DianpuComment;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
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

@Service("ordersExcelService")
public class OrdersExcelService implements ExecuteService {
    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<OrdersVO> empVOs = new ArrayList<OrdersVO>();
            for(int i=0;i<arrs.length;i++){
                OrdersVO empVO = orderDao.findById(arrs[i]);
                if(!StringUtil.isNullOrEmpty(empVO.getCreate_time())){
                    empVO.setCreate_time(DateUtil.getDate(empVO.getCreate_time(), "yyyy-MM-dd HH:mm"));
                }

//                if(!StringUtil.isNullOrEmpty(empVO.getPay_time())){
//                    empVO.setPay_time(DateUtil.getDate(empVO.getPay_time(), "yyyy-MM-dd HH:mm"));
//                }
//
//                if(!StringUtil.isNullOrEmpty(empVO.getSend_time())){
//                    empVO.setSend_time(DateUtil.getDate(empVO.getSend_time(), "yyyy-MM-dd HH:mm"));
//                }
//
//                if(!StringUtil.isNullOrEmpty(empVO.getAccept_name())){
//                    empVO.setAccept_name(DateUtil.getDate(empVO.getAccept_name(), "yyyy-MM-dd HH:mm"));
//                }
//
//                if(!StringUtil.isNullOrEmpty(empVO.getCompletion_time())){
//                    empVO.setCompletion_time(DateUtil.getDate(empVO.getCompletion_time(), "yyyy-MM-dd HH:mm"));
//                }
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelOrders(empVOs, request);
            return fileName;
        }
        return null;
    }

}
