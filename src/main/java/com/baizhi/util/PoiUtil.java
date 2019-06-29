package com.baizhi.util;

import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoiUtil {


    //导出excel
    public  static  void exportExcel(String sheetName, String title, List<User> users, OutputStream os){

        try {
            //创建一个excel的工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

            //创建一个工作空间
            HSSFSheet sheet = workbook.createSheet(sheetName);

            //统一设置单元格列的宽度
            sheet.setDefaultColumnWidth(18);

            //合并单元格
            //参数1:从第几行合并  参数2:合并到多少行  参数3:从第几列合并 参数4:合并到多少列
            CellRangeAddress region = new CellRangeAddress(0,0,0,4);
            sheet.addMergedRegion(region);
            //创建大的标题行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            //创建单元格样式对象
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中对齐
            HSSFFont font = workbook.createFont();//创建字体
            font.setFontHeightInPoints((short)18);
            cellStyle.setFont(font);//设置字体
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title);//设置文本内容


            //创建几列
            Field[] declaredFields = User.class.getDeclaredFields();//获取当前对象中所有属性数组

            //创建副标题行
            HSSFRow titleRow = sheet.createRow(1);
            for (int i = 0; i < declaredFields.length; i++) {
                String header = getKey(declaredFields[i].getName());
                HSSFCell titleCell = titleRow.createCell(i);
                titleCell.setCellStyle(cellStyle);
                titleCell.setCellValue(header);
            }


            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                //创建数据行
                HSSFRow dataRow = sheet.createRow(i + 2);
                for (int j = 0; j < declaredFields.length; j++) {
                    Field declaredField = declaredFields[j];
                    //反射调用类中get方法
                    String name = declaredField.getName();
                    String getMethod = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
                    Method getMethodObj = User.class.getDeclaredMethod(getMethod, new Class[]{});
                    //反射调用类中
                    Object invoke = getMethodObj.invoke(user, new Object[]{});
                    HSSFCell dataCell = dataRow.createCell(j);
                    if (invoke!=null) {
                        if(declaredField.getType() == Date.class){
                            dataCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(invoke));

                        }else{
                            dataCell.setCellValue(invoke.toString());
                        }
                    }
                }
            }


            //生成excel
            workbook.write(os);
            workbook.close();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义一个map 维护属性和标题对应关系
    public static String  getKey(String attr){
        Map<String,String> results  = new HashMap<String,String>();
        results.put("id","ID");
        results.put("phoneNum","手机号");
        results.put("username","用户名");
        results.put("password","密码");
        results.put("salt","盐");
        results.put("dharmName","法名");
        results.put("gender","性别");
        results.put("headPic","头像地址");
        results.put("province","所在省份");
        results.put("city","城市");
        results.put("sign","签名");
        results.put("status","状态");
        results.put("date","注册时间");
        return results.get(attr);
    }


}
