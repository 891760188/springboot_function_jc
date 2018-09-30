package ye.guo.huang.jwt.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.common.util.DateUtils;
import ye.guo.huang.jwt.common.util.ExcelUtil;
import ye.guo.huang.jwt.pojo.CustInfo;
import ye.guo.huang.jwt.service.CustInfoService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExeclController {

    @Autowired
    private CustInfoService custInfoService ;

    @RequestMapping(value="/importExcel.excel",method=RequestMethod.POST)
    public ResponseBean importExcel(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {

        if(file == null || file.isEmpty()){
            return ResponseBean.responseErr("文件为空");
        }
        Workbook wb = null;
        List<CustInfo> HeroList = new ArrayList();
        String oriName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        if (ExcelUtil.isExcel2007(oriName)) {
            wb = new XSSFWorkbook(inputStream);
        } else {
            wb = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        List<CustInfo> custInfoList = new ArrayList<CustInfo>();

        DecimalFormat df = new DecimalFormat("#");
        for (int i = 2; i < sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            //序号
            Cell cell0 = row.getCell(0) ;
            CellType CellType =cell0.getCellTypeEnum();
            cell0.toString();
//            String value= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据

            CustInfo custInfo = new CustInfo();

            //姓名
            Cell custNameCell = row.getCell(1) ;
            custInfo.setCustName(custNameCell.toString());
            //生日
            Cell birtdayCell = row.getCell(2) ;
            Date birthday = birtdayCell.getDateCellValue();
            custInfo.setBirthday(birthday);
            //手机
            Cell phoneCell = row.getCell(3) ;
            String mobile = phoneCell.toString();
            // mobile = memberCell.toString();

            switch (phoneCell.getCellType()) {
//                case HSSFCell.CELL_TYPE_NUMERIC:// 数字
//                    mobile = df.format(phoneCell.getNumericCellValue());
//                    break;
//                case HSSFCell.CELL_TYPE_STRING:// 字符串
//                    mobile = df.format(Double.parseDouble(phoneCell.toString()));
//                    break;
                default:
                    mobile = phoneCell.toString();
                    break;
            }
                custInfo.setPhone(mobile);
            //性别
            Cell sexCell = row.getCell(4) ;
            if("女".endsWith(sexCell.toString())){
                custInfo.setSex(0);
            }else if("男".endsWith(sexCell.toString())){
                custInfo.setSex(1);
            }else{
                custInfo.setSex(2);
            }
            //地址
            Cell addressCell = row.getCell(5) ;
            custInfo.setAddress(addressCell.toString());
            //email
            Cell emailCell = row.getCell(6) ;
            custInfo.setEmail(emailCell.toString());
            //工作
            Cell workCell = row.getCell(7) ;
            custInfo.setWork(workCell.toString());
            //remark
            Cell remarkCell = row.getCell(8) ;
            custInfo.setRemark(remarkCell.toString());
            custInfoList.add(custInfo);
        }
        custInfoService.insertBatch(custInfoList);
        return ResponseBean.response(custInfoList);
    }

}
