package com.liangxunwang.unimanager.util;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.*;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghailong on 2016/3/19.
 */
public class CreateSimpleExcelToDisk {
    /**
     * @功能：手工构建一个简单格式的Excel
     */

    public static String toExcelEmp(List<MemberVO> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("会员表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("账号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("头像");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("是否禁用");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("注册日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("生日");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("经纬度");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("经纬度");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("购买等级");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("上级手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 13);
        cell.setCellValue("上级昵称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 14);
        cell.setCellValue("定向卡会员");
        cell.setCellStyle(style);
        cell = row.createCell((short) 15);
        cell.setCellValue("分销等级");
        cell.setCellStyle(style);
        cell = row.createCell((short) 16);
        cell.setCellValue("零钱");
        cell.setCellStyle(style);
        cell = row.createCell((short) 17);
        cell.setCellValue("积分");
        cell.setCellStyle(style);

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            MemberVO stu = (MemberVO) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getEmpId());
            row.createCell((short) 1).setCellValue(stu.getEmp_number());
            row.createCell((short) 2).setCellValue(stu.getEmpMobile());
            row.createCell((short) 3).setCellValue(stu.getEmpCover());
            row.createCell((short) 4).setCellValue(stu.getEmpName());
            if("0".equals(stu.getEmpSex())){
                row.createCell((short) 5).setCellValue("女");
            }else {
                row.createCell((short) 5).setCellValue("男");
            }
            if("0".equals(stu.getEmpSex())){
                row.createCell((short) 6).setCellValue("否");
            }else {
                row.createCell((short) 6).setCellValue("是");
            }
            row.createCell((short) 7).setCellValue(stu.getDateline());
            row.createCell((short) 8).setCellValue(stu.getEmp_birthday());
            row.createCell((short) 9).setCellValue(stu.getLat());
            row.createCell((short) 10).setCellValue(stu.getLng());
            row.createCell((short) 11).setCellValue(stu.getLevelName());
            row.createCell((short) 12).setCellValue(stu.getEmp_mobile_up());
            row.createCell((short) 13).setCellValue(stu.getEmp_name_up());

            if("0".equals(stu.getIs_card_emp())){
                row.createCell((short) 14).setCellValue("否");
            }else {
                row.createCell((short) 14).setCellValue("是");
            }

            if("0".equals(stu.getLx_attribute_id())){
                row.createCell((short) 15).setCellValue("普通会员");
            }else if("1".equals(stu.getLx_attribute_id())){
                row.createCell((short) 15).setCellValue("分销会员");
            }else if("2".equals(stu.getLx_attribute_id())){
                row.createCell((short) 15).setCellValue("店长");
            }
            row.createCell((short) 16).setCellValue(stu.getPackage_money());
            row.createCell((short) 17).setCellValue(stu.getJfcount());

        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/huiyuan_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/huiyuan_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }



    public static String toExcelCount(List<CountVo> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("积分表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("账号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("头像");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("积分");
        cell.setCellStyle(style);


        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            CountVo stu = (CountVo) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getEmpId());
            row.createCell((short) 1).setCellValue(stu.getEmp_number());
            row.createCell((short) 2).setCellValue(stu.getEmp_mobile());
            row.createCell((short) 3).setCellValue(stu.getEmp_cover());
            row.createCell((short) 4).setCellValue(stu.getEmp_name());
            row.createCell((short) 5).setCellValue(stu.getCount());
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/count_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/count_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String toExcelCardEmp(List<CardEmp> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("定向充值卡表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("账号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("头像");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("第几年");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("有效期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("开始日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("是否可用");
        cell.setCellStyle(style);

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            CardEmp stu = (CardEmp) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getEmp_id());
            row.createCell((short) 1).setCellValue(stu.getEmp_number());
            row.createCell((short) 2).setCellValue(stu.getEmp_mobile());
            row.createCell((short) 3).setCellValue(stu.getEmp_cover());
            row.createCell((short) 4).setCellValue(stu.getEmp_name());
            row.createCell((short) 5).setCellValue(stu.getLx_card_emp_year());
            row.createCell((short) 6).setCellValue(stu.getLx_card_emp_end_time());
            row.createCell((short) 7).setCellValue(stu.getLx_card_emp_start_time());
            if("0".equals(stu.getIs_use())){
                row.createCell((short) 8).setCellValue("可用");
            }else {
                row.createCell((short) 8).setCellValue("不可用");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/cardEmp_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/cardEmp_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelConsumption(List<LxConsumption> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("消费记录表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("消费描述");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("消费金额");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("消费类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("消费时间");
        cell.setCellStyle(style);

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            LxConsumption stu = (LxConsumption) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getEmp_id());
            row.createCell((short) 1).setCellValue(stu.getEmp_mobile());
            row.createCell((short) 2).setCellValue(stu.getEmp_name());
            row.createCell((short) 3).setCellValue(stu.getLx_consumption_cont());
            row.createCell((short) 4).setCellValue(stu.getLx_consumption_count());
            row.createCell((short) 5).setCellValue(stu.getOrder_no());
            if("0".equals(stu.getLx_consumption_type())){
                row.createCell((short) 6).setCellValue("购买商品");
            }else if("1".equals(stu.getLx_consumption_type())){
                row.createCell((short) 6).setCellValue("零钱充值");
            }else if("2".equals(stu.getLx_consumption_type())){
                row.createCell((short) 6).setCellValue("手机端充值");
            }else if("3".equals(stu.getLx_consumption_type())){
                row.createCell((short) 6).setCellValue("定向卡充值");
            }
            row.createCell((short) 7).setCellValue(stu.getDateline());
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/consumption_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/consumption_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }



    public static String toExcelBankApply(List<lxBankApplyVo> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("提现申请记录表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("会员账户");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("提现金额");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("银行");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("开户人姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("银行预留手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("卡号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("开户行");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("处理时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("是否处理");
        cell.setCellStyle(style);

        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            lxBankApplyVo stu = (lxBankApplyVo) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getLx_bank_apply_id());
            row.createCell((short) 1).setCellValue(stu.getEmp_number());
            row.createCell((short) 2).setCellValue(stu.getEmp_name());
            row.createCell((short) 3).setCellValue(stu.getEmp_mobile());
            row.createCell((short) 4).setCellValue(stu.getLx_bank_apply_count());
            row.createCell((short) 5).setCellValue(stu.getBank_name());
            row.createCell((short) 6).setCellValue(stu.getBank_emp_name());
            row.createCell((short) 7).setCellValue(stu.getBank_mobile());
            row.createCell((short) 8).setCellValue(stu.getBank_card());
            row.createCell((short) 9).setCellValue(stu.getBank_kaihu_name());
            row.createCell((short) 10).setCellValue(stu.getDateline_apply()==null?"":stu.getDateline_apply());
            row.createCell((short) 11).setCellValue(stu.getDateline_done()==null?"":stu.getDateline_done());
            if("0".equals(stu.getIs_check())){
                row.createCell((short) 12).setCellValue("否");
            }else if("1".equals(stu.getIs_check())){
                row.createCell((short) 12).setCellValue("是");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/bankApply_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/bankApply_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelGoodsComment(List<GoodsComment> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("商品评论表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("评论人昵称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("商品");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("评论内容");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("星级");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("是否显示");
        cell.setCellStyle(style);


        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            GoodsComment stu = (GoodsComment) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getId()==null?"":stu.getId());
            row.createCell((short) 1).setCellValue(stu.getNickName()==null?"":stu.getNickName());
            row.createCell((short) 2).setCellValue(stu.getGoods_name()==null?"":stu.getGoods_name());
            row.createCell((short) 3).setCellValue(stu.getContent()==null?"":stu.getContent());
            row.createCell((short) 4).setCellValue(stu.getStarNumber());
            row.createCell((short) 5).setCellValue(stu.getDateline()==null?"":stu.getDateline());
            if(0 == stu.getIs_del()){
                row.createCell((short) 6).setCellValue("是");
            }else{
                row.createCell((short) 6).setCellValue("否");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/comments_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/comments_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelDianpusComment(List<DianpuComment> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("店铺评论表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("评论人昵称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("评论人手机号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("评论内容");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("星级");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("是否显示");
        cell.setCellStyle(style);


        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            DianpuComment stu = (DianpuComment) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getDianpu_comment_id()==null?"":stu.getDianpu_comment_id());
            row.createCell((short) 1).setCellValue(stu.getEmp_name()==null?"":stu.getEmp_name());
            row.createCell((short) 2).setCellValue(stu.getEmp_mobile()==null?"":stu.getEmp_mobile());
            row.createCell((short) 3).setCellValue(stu.getDianpu_comment_cont()==null?"":stu.getDianpu_comment_cont());
            row.createCell((short) 4).setCellValue(stu.getStarNumber());
            row.createCell((short) 5).setCellValue(stu.getComment_dateline()==null?"":stu.getComment_dateline());
            if("0".equals(stu.getIs_del())){
                row.createCell((short) 6).setCellValue("是");
            }else{
                row.createCell((short) 6).setCellValue("否");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/comments_dianpu_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/comments_dianpu_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelOrders(List<OrdersVO> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("订单表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("支付平台订单号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("买家账号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("买家名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("买家电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("收货人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("收货人电话");
        cell.setCellStyle(style);

        cell = row.createCell((short) 7);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 8);
        cell.setCellValue("购买数量");
        cell.setCellStyle(style);

        cell = row.createCell((short) 9);
        cell.setCellValue("商品总金额");
        cell.setCellStyle(style);


        cell = row.createCell((short) 10);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);

        cell = row.createCell((short) 11);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 12);
        cell.setCellValue("付款状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 13);
        cell.setCellValue("配送状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 14);
        cell.setCellValue("卖家名称");
        cell.setCellStyle(style);

        cell = row.createCell((short) 15);
        cell.setCellValue("是否0元订单");
        cell.setCellStyle(style);

        for (int i = 0; i < empVOs.size(); i++)
        {


            row = sheet.createRow((int) i + 1);
            OrdersVO stu = (OrdersVO) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getOut_trade_no()==null?"":stu.getOut_trade_no());
            if("0".equals(stu.getTrade_type())){
                row.createCell((short) 1).setCellValue("支付宝");
            }else if("1".equals(stu.getTrade_type())){
                row.createCell((short) 1).setCellValue("微信");
            }else if("2".equals(stu.getTrade_type())){
                row.createCell((short) 1).setCellValue("零钱");
            }
            row.createCell((short) 2).setCellValue(stu.getEmp_number()==null?"":stu.getEmp_number());
            row.createCell((short) 3).setCellValue(stu.getEmpName()==null?"":stu.getEmpName());
            row.createCell((short) 4).setCellValue(stu.getEmp_mobile()==null?"":stu.getEmp_mobile());
            row.createCell((short) 5).setCellValue(stu.getAccept_name()==null?"":stu.getAccept_name());
            row.createCell((short) 6).setCellValue(stu.getPhone()==null?"":stu.getPhone());
            row.createCell((short) 7).setCellValue(stu.getGoodsName()==null?"":stu.getGoodsName());
            row.createCell((short) 8).setCellValue(stu.getGoods_count()==null?"":stu.getGoods_count());
            row.createCell((short) 9).setCellValue(String.valueOf(stu.getPayable_amount()));
            row.createCell((short) 10).setCellValue(stu.getCreate_time()==null?"":stu.getCreate_time());
//            row.createCell((short) 5).setCellValue(stu.getPay_time()==null?"":stu.getPay_time());
//            row.createCell((short) 5).setCellValue(stu.getSend_time()==null?"":stu.getSend_time());
            if("1".equals(stu.getStatus())){
                row.createCell((short) 11).setCellValue("订单生成");
            }else if("2".equals(stu.getStatus())){
                row.createCell((short) 11).setCellValue("订单支付");
            }else if("3".equals(stu.getStatus())){
                row.createCell((short) 11).setCellValue("订单取消");
            }else if("4".equals(stu.getStatus())){
                row.createCell((short) 11).setCellValue("订单作废");
            } else if("5".equals(stu.getStatus())){
                row.createCell((short) 11).setCellValue("订单完成");
            }

            if("0".equals(stu.getPay_status())){
                row.createCell((short) 12).setCellValue("未支付");
            }else if("1".equals(stu.getPay_status())){
                row.createCell((short) 12).setCellValue("已支付");
            }else if("2".equals(stu.getPay_status())){
                row.createCell((short) 12).setCellValue("退款");
            }

            if("0".equals(stu.getDistribution_status())){
                row.createCell((short) 13).setCellValue("未发货");
            }else if("1".equals(stu.getDistribution_status())){
                row.createCell((short) 13).setCellValue("已发货");
            }else if("2".equals(stu.getDistribution_status())){
                row.createCell((short) 13).setCellValue("已签收");
            }

            row.createCell((short) 14).setCellValue(stu.getSellerName()==null?"":stu.getSellerName());
            if("0".equals(stu.getIs_dxk_order())){
                row.createCell((short) 15).setCellValue("否");
            }else{
                row.createCell((short) 15).setCellValue("是");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/orders_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/orders_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static String toExcelDianpu(List<ManagerInfoVo> empVOs, HttpServletRequest request) throws Exception
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("店铺表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("店铺名称");
        cell.setCellStyle(style);
//        cell = row.createCell((short) 1);
//        cell.setCellValue("营业执照");
//        cell.setCellStyle(style);
//        cell = row.createCell((short) 2);
//        cell.setCellValue("身份证");
//        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("会员");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("会员电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("店铺分类");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("定向卡商家");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("是否审核");
        cell.setCellStyle(style);


        for (int i = 0; i < empVOs.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            ManagerInfoVo stu = (ManagerInfoVo) empVOs.get(i);
            row.createCell((short) 0).setCellValue(stu.getCompany_name());
            row.createCell((short) 1).setCellValue(stu.getEmp_name());
            row.createCell((short) 2).setCellValue(stu.getEmp_mobile());
            row.createCell((short) 3).setCellValue(stu.getLx_class_name());
            row.createCell((short) 4).setCellValue(stu.getCompany_person());
            row.createCell((short) 5).setCellValue(stu.getCompany_tel());
            if("0".equals(stu.getIs_card_emp())){
                row.createCell((short) 6).setCellValue("否");
            }else {
                row.createCell((short) 6).setCellValue("是");
            }
            if("0".equals(stu.getIs_check())){
                row.createCell((short) 7).setCellValue("否");
            }else if("1".equals(stu.getIs_check())){
                row.createCell((short) 7).setCellValue("是");
            }else if("2".equals(stu.getIs_check())){
                row.createCell((short) 7).setCellValue("不通过");
            }
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String fileName = String.valueOf(df.format(new Date()));
            String path = request.getSession().getServletContext().getRealPath("upload");
            FileOutputStream fout = new FileOutputStream(path+"/dianpu_"+fileName+".xls");
            wb.write(fout);
            fout.close();
            return "/dianpu_"+fileName+".xls";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
