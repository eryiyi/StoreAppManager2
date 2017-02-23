package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
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
 * Created by zhl on 2015/3/3.
 */
@Service("indexSjService")
public class IndexSjService implements ListService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Override
    public Object list(Object object) throws ServiceException {
        String emp_id = (String) object;
        //根据用户ID查询商家的数据

        //----------------商品统计----------------
        //全部商品-商家的在售的
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("empId", emp_id);
        map3.put("isUse", "0");
        long countGoodsUse = paopaoGoodsDao.count(map3);
        //商家的下架的商品
        map3.put("isUse", "1");
        long countGoodsUnUse = paopaoGoodsDao.count(map3);
        //即将卖完的商品--少于5件
        long countGoodsZero = paopaoGoodsDao.countZero(map3);
        //--------------订单统计------------------------
        //全部订单
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("empId", emp_id);
        long countOrderAll = orderDao.count(map4);
        //已完成订单
        map4.put("status", "5");
        long countOrderDone = orderDao.count(map4);
        //今日订单统计
        map4.put("startTime",  DateUtil.getStartDay());
        map4.put("endTime",  DateUtil.getEndDay());
        long countOrderDay = orderDao.countDay(map4);
        //---------------------收入统计--------------------
        //营业额统计
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("orderStatus", "5");
        map1.put("empId", emp_id);
        //总的收入
        Float incomeAll = orderDao.income(map1);
        //今天的收入
        map1.put("startTime", DateUtil.getStartDay());
        map1.put("endTime", DateUtil.getEndDay());
        Float incomeDay = orderDao.income(map1);
        //--------------------粉丝统计--------------------------
        Map<String,Object> mapFensi = new HashMap<String, Object>();
        mapFensi.put("param1", emp_id);
        List<MemberVO> listFensi =  memberDao.listAllFensi(mapFensi);


        List<String> list = new ArrayList<String>();
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        list.add(String.valueOf(decimalFormat.format(incomeAll==null?0:incomeAll)));//全部收入
        list.add(String.valueOf(decimalFormat.format(incomeDay==null?0:incomeDay)));//进入收入

        list.add(String.valueOf(countOrderAll));//订单全部
        list.add(String.valueOf(countOrderDone));//订单完成的
        list.add(String.valueOf(countOrderDay));//订单今日的

        list.add(String.valueOf(countGoodsUse));//在售的
        list.add(String.valueOf(countGoodsUnUse));//下架的
        list.add(String.valueOf(countGoodsZero));//即将售罄的
        list.add(String.valueOf(listFensi.size()));//粉丝数量
        return list;
    }
}
