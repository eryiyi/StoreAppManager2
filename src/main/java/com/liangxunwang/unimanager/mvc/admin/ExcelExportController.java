package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//到处excel相关
@Controller
public class ExcelExportController extends ControllerConstants {

    @Autowired
    @Qualifier("empExcelService")
    private ExecuteService empExcelService;

    //会员导出Excel表格数据
    @RequestMapping("memberExportExcel")
    @ResponseBody
    public String memberExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) empExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }

    @Autowired
    @Qualifier("countExcelService")
    private ExecuteService countExcelService;

    //积分导出Excel表格数据
    @RequestMapping("countExportExcel")
    @ResponseBody
    public String countExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) countExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }



    @Autowired
    @Qualifier("cardEmpExcelService")
    private ExecuteService cardEmpExcelService;

    //积分导出Excel表格数据
    @RequestMapping("cardEmpExportExcel")
    @ResponseBody
    public String cardEmpExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) cardEmpExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }

    @Autowired
    @Qualifier("consumptionExcelService")
    private ExecuteService consumptionExcelService;

    //消费记录导出Excel表格数据
    @RequestMapping("consumptionExportExcel")
    @ResponseBody
    public String consumptionExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) consumptionExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


    @Autowired
    @Qualifier("bankApplyExcelService")
    private ExecuteService bankApplyExcelService;

    //申请记录导出Excel表格数据
    @RequestMapping("bankApplyExportExcel")
    @ResponseBody
    public String bankApplyExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) bankApplyExcelService.execute(objects);

            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }



    @Autowired
    @Qualifier("goodsCommentsExcelService")
    private ExecuteService goodsCommentsExcelService;

    //商品评论导出Excel表格数据
    @RequestMapping("goodsCommentsExportExcel")
    @ResponseBody
    public String goodsCommentsExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) goodsCommentsExcelService.execute(objects);
            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }

    @Autowired
    @Qualifier("dianpuCommentsExcelService")
    private ExecuteService dianpuCommentsExcelService;

    //店铺评论导出Excel表格数据
    @RequestMapping("dianpuCommentsExportExcel")
    @ResponseBody
    public String dianpuCommentsExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) dianpuCommentsExcelService.execute(objects);
            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


    @Autowired
    @Qualifier("ordersExcelService")
    private ExecuteService ordersExcelService;
    //订单导出Excel表格数据
    @RequestMapping("OrdersExportExcel")
    @ResponseBody
    public String OrdersExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) ordersExcelService.execute(objects);
            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


    @Autowired
    @Qualifier("dianpuExcelService")
    private ExecuteService dianpuExcelService;
    //店铺导出Excel表格数据
    @RequestMapping("dianpuExportExcel")
    @ResponseBody
    public String dianpuExportExcel(HttpSession session,String ids, HttpServletRequest request) {
        try {
            Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
            Object[] objects = new Object[2];
            objects[0]=ids;
            objects[1]=request;
            String fileName = (String) dianpuExcelService.execute(objects);
            DataTip tip = new DataTip();
            tip.setData(fileName);
            return toJSONString(tip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toJSONString(ERROR_1);
    }


}
