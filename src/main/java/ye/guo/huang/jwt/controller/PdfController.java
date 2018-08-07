package ye.guo.huang.jwt.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ye.guo.huang.jwt.common.util.ItextUtil;
import ye.guo.huang.jwt.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * https://www.cnblogs.com/chenpi/p/5534595.html
 * @author 448249687@qq.com
 * @Date 2018/8/6
 */
@Controller
@RequestMapping("/api/pdf")
public class PdfController {

    @Value("${constant.filepath}")
    private String filePath;

    @RequestMapping("/html2pdfPublic.pdf")
    public void html2pdfPublic( HttpServletResponse response) throws Exception {

        String html2 =
                "<!DOCTYPE html>                                      "+
                        "<html>                                               "+
                        "	<head>                                             "+
                        "		<meta charset=\"UTF-8\"></meta>                    "+
                        "		<style type=\"text/css\">                          "+
                        "body {\n" +
                        "\tfont-family: SimSun;\n" +
                        "}\n" +
                        "			div{                                        "+
                        "				text-align: center;                          "+
                        "			}                                              "+

                        "		</style>                                         "+
                        "	</head>                                            "+
                        "	<body>                                             "+
                        "		<div>pdf测试</div>                         "+
                        "		<div>                                        "+
                        "			<table style=\" width:300px;margin-left: 100px; \">                                        "+
                        "				<tr>                                         "+
                        "					<th>第一行</th>                            "+
                        "					<th>第一行</th>                            "+
                        "					<th>第一行</th>                            "+
                        "					<th>第一行</th>                            "+
                        "					<th>第一行</th>                            "+
                        "				</tr>                                        "+
                        "				<tr>                                         "+
                        "					<td>第一行</td>                            "+
                        "					<td>第一行</td>                            "+
                        "					<td>第一行</td>                            "+
                        "					<td>第一行</td>                            "+
                        "					<td>第一行</td>                            "+
                        "				</tr>                                        "+
                        "			</table>                                       "+
                        "		</div>                                       "+
                        "		<div >             "+
                        "			我是脚222                                      "+
                        "		</div>                                        "+
                        "	</body>                                            "+
                        "</html>                                              ";

        ItextUtil.html2pdfPublic( response,  html2,  filePath, StringUtils.getUUid()+".pdf");
    }

    @RequestMapping("/download.fdf")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=HelloWorld.pdf");
        //创建一个实例
        Document document = new Document();
        //把所有添加到document的内容添加到输出流
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        //打开
        document.open();
        //添加
        document.add(new Paragraph("A Hello World PDF document.啊啊"));//添加一个段落


        //方法一：使用Windows系统字体(TrueType)
//        BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        //方法二：使用iTextAsian.jar中的字体
//        BaseFont baseFont = BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);

        //方法三：使用资源字体(ClassPath)
        ////BaseFont baseFont = BaseFont.createFont("/SIMYOU.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        Font font = new Font(baseFont);
        document.add(new Paragraph("解决中文问题了！",font));



        //Add Image
//        Image image1 = Image.getInstance("C:\\temp.jpg");
//        //Fixed Positioning
//        image1.setAbsolutePosition(100f, 550f);
//        //Scale to new height and new width of image
//        image1.scaleAbsolute(200, 200);
//        //Add to document
//        document.add(image1);
        //添加图片
//        String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
//        Image image = Image.getInstance(new URL(imageUrl));
//        image.setAbsolutePosition(100f, 550f);
//        image.scaleAbsolute(200, 200);
//        document.add(image);


        //添加属性 作者
        document.addAuthor("Lokesh Gupta");
        //创建日期
        document.addCreationDate();
        //创建者
        document.addCreator("HowToDoInJava.com");
        //标题
        document.addTitle("Set Attribute Example");
        //主要内容
        document.addSubject("An example to show how attributes can be added to pdf files.");
        //关闭
        document.close();
        writer.close();
    }

    @RequestMapping("/download2.fdf")
    public void download2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=HelloWorld.pdf");
        //创建一个实例
        Document document = new Document();
        //把所有添加到document的内容添加到输出流
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        //打开
        document.open();
        //新建一个3列的表格对象
        PdfPTable table = new PdfPTable(3);
        //100%的宽度
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f); // Space before table
        table.setSpacingAfter(10f); // Space after table
        float[] columnWidths = { 1f, 1f, 1f };
        table.setWidths(columnWidths);//单元格的宽度

        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
        cell1.setBorderColor(BaseColor.BLUE);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
        cell2.setBorderColor(BaseColor.GREEN);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
        cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // To avoid having the cell border and the content overlap, if you
        // are having thick cell borders
        // cell1.setUserBorderPadding(true);
        // cell2.setUserBorderPadding(true);
        // cell3.setUserBorderPadding(true);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);

        document.add(table);

        document.close();
        writer.close();

    }

    @RequestMapping("/test.pdf")
    public void html2pdf(HttpServletResponse response) throws Exception {
        ItextUtil.html2Pdf( response, "D:\\09_software\\50_intellj idea\\Iprodect\\spring-boot-chapter\\jwt\\src\\main\\resources\\noDataUI.html", "test01.pdf");
//        ItextUtil.htmlCodeComeString( response, "D:\\09_software\\50_intellj idea\\Iprodect\\spring-boot-chapter\\jwt\\src\\main\\resources\\noDataUI.html", "test01.pdf");
    }

    public static void main(String[] args) throws IOException {
        try
        {
            //Read file using PdfReader
            PdfReader pdfReader = new PdfReader("6.pdf");

            //Modify file using PdfReader
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("HelloWorld-modified.pdf"));

            Image image = Image.getInstance("http://www.eclipse.org/xtend/images/java8_logo.png");
            image.scaleAbsolute(100, 50);
            image.setAbsolutePosition(100f, 700f);

            for(int i=1; i<= pdfReader.getNumberOfPages(); i++)
            {
                PdfContentByte content = pdfStamper.getUnderContent(i);
                content.addImage(image);
            }

            pdfStamper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


}
