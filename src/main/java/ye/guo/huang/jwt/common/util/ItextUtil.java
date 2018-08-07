package ye.guo.huang.jwt.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *  pdf工具
 * @author 448249687@qq.com
 * @Date 2018/8/7
 */
public class ItextUtil{
    /**
     *  将html文件打印成pdf文件
     * @param response
     * @param filePath html路径
     * @param pdfName
     * @throws Exception
     */
    public static void html2Pdf(HttpServletResponse response, String filePath, String pdfName) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+pdfName);
        Document document = new Document();

        StyleSheet st = new StyleSheet();
        st.loadTagStyle("body", "leading", "16,0");
        PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
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

        ArrayList p = (ArrayList) HTMLWorker.parseToList(new FileReader(filePath), st);
        for(int k = 0; k < p.size(); ++k) {
            document.add((Element)p.get(k));
        }
        InputStream htmlFileStream = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(htmlFileStream, "UTF-8");
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, isr);

        document.close();
        writer.close();

    }

    /**
     * 将html字符串打印成pdf文件
     * @param htmlCode
     * @param pdfName
     */
    public static void htmlCodeComeString(HttpServletResponse response, String htmlCode, String pdfName) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+pdfName);
        /*1、创建document对象*/
        Document doc = new Document(PageSize.A4);
        /*2、创建PdfWriter实例*/
        PdfWriter.getInstance(doc, response.getOutputStream());
        /*3、打开文档*/
        doc.open();
        /*4、解决中文问题*/
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        Paragraph t = new Paragraph(htmlCode, FontChinese);
        doc.add(t);
        doc.close();

    }

    /**
     *  将html字符串打印成pdf文件--终极版
     * @param response
     * @param html
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void html2pdfPublic(HttpServletResponse response, String html, String filePath,String fileName) throws Exception {

        File file = new File(filePath + "\\" + fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if (System.getProperty("os.name").contains("Window")) {
            fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } else {
            fontResolver.addFont("/usr/share/fonts/win/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        renderer.layout();
        renderer.createPDF(outputStream);
        boolean downFlag = FileUtil.downLoad( response, fileName ,  filePath);
    }

    /**
     * 生成 PDF 文件
     * @param out 输出流
     * @param html HTML字符串
     * @throws IOException IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if (System.getProperty("os.name").contains("Window")) {
            fontResolver.addFont("C:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } else {
            fontResolver.addFont("/usr/share/fonts/win/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        renderer.layout();
        renderer.createPDF(out);
    }

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\09_software\\50_intellj idea\\Iprodect\\spring-boot-chapter\\jwt\\src\\main\\resources\\test.pdf");
        FileOutputStream outputStream = new FileOutputStream(file);
        String html =
                "<html>\n" +
                        "<head>\n" +
                        "<style type=\"text/css\">\n" +
                        "body {\n" +
                        "\tfont-family: SimSun;\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<img src=\"http://upload-images.jianshu.io/upload_images/3424642-455a7e76316807e6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700\"></img>\n" +
                        "\n" +
                        "<table border=\"1\">\n" +
                        "<tr>\n" +
                        "<td>row 1, cell 1</td>\n" +
                        "<td>row 1, cell 2</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td>哈哈哈</td>\n" +
                        "<td>row 2, cell 2</td>\n" +
                        "</tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>\n";


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


        createPDF(outputStream,html2);

    }


}
