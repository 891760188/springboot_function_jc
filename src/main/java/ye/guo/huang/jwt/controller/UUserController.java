package ye.guo.huang.jwt.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.pojo.UUser;
import ye.guo.huang.jwt.service.UUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

@RestController
@RequestMapping("/api/user")
public class UUserController {

    private static final Logger LOGGER = LogManager.getLogger(UUserController.class);

    @Autowired
    private UUserService uUserService ;

    @GetMapping("/findAll.json")
    public ResponseBean findAll() {

        return new ResponseBean(uUserService.findAll());
    }

    @PostMapping("/regedit.do")
    public  ResponseBean regedit(UUser uUser){
        String jwt = uUserService.regedit(uUser);
        return new ResponseBean(jwt);
    }

    @PostMapping("/login.do")
    public  ResponseBean login(UUser uUser){
        LOGGER.info(1);
        String jwt = uUserService.findByNicknameAndPswd(uUser.getNickname(),uUser.getPswd());
        return new ResponseBean(jwt);
    }

    // 下载pdf文档
//    @RequestMapping("/downloadPdf.file")
//    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 告诉浏览器用什么软件可以打开此文件
//        response.setHeader("content-Type", "application/pdf");
//        // 下载文件的默认名称
//        response.setHeader("Content-Disposition", "attachment;filename=user.pdf");
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, response.getOutputStream());
//        document.open();
//        for (int i = 0 ; i < 4 ; i++) {
//            PdfPTable table = new PdfPTable(3);
//            PdfPCell cell = new PdfPCell();
//            cell.setPhrase(new Paragraph("id"));
//            table.addCell(cell);
//            document.add(table);
//
//            cell = new PdfPCell();
//            cell.setPhrase(new Paragraph("名字555"));
//            table.addCell(cell);
//            document.add(table);
//
//            cell = new PdfPCell();
//            cell.setPhrase(new Paragraph("age"));
//            table.addCell(cell);
//            document.add(table);
//        }
//        document.close();
//    }

}
