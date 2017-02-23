package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/21.
 *
 * 后台操作订单
 */
@Repository("orderDao")
public interface OrderDao {

    /**
     * 查询所有的订单
     * @param map
     * @return
     */
    List<OrdersVO> listOrders(Map<String,Object> map);

    long count(Map<String,Object> map);

    long countDay(Map<String,Object> map);

    /**
     * 根据订单号
     * @param id
     * @return
     */
    OrdersVO findById(String id);

    /**
     * 查询营业额总收入
     * @param map
     * @return
     */
    Float income(Map<String,Object> map);

    /**
     * 查询某一天的结算
     * @param map
     * @return
     */
    Settlement settlement(Map<String,Object> map);

    void updateOrderStatus(@Param(value = "time") String time, @Param(value = "period") String period);

    void updateOrderAccount(Map<String,Object> map);

    //查询即将到期的订单
    List<OrdersVO> listOrdersEnd(@Param(value = "time") String time, @Param(value = "period") String period);

}
