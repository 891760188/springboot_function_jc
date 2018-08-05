package ye.guo.huang.jwt.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ye.guo.huang.jwt.common.Page;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.common.util.FileUtil;
import ye.guo.huang.jwt.common.util.StringUtils;
import ye.guo.huang.jwt.pojo.SysFiles;
import ye.guo.huang.jwt.service.SysFilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class SysFilesController {

    private static final Logger LOGGER = LogManager.getLogger(SysFilesController.class);

    @Autowired
    private SysFilesService sysFilesService;

    @Value("${constant.filepath}")
    private String filePath;


    @RequestMapping(value = "/all.json", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(Page<SysFiles> page) {
        System.out.print(111111);
        System.out.print(111111);
        System.out.print(111111);
        System.out.print(111111);
        List<SysFiles> filesList = sysFilesService.selectAllFiles(page.getPageNo(), page.getPageSize());
        page.setResultList(filesList);
        return ResponseBean.response(page);
    }

    /**
     * 实现多文件上传 public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files)
     * @param request
     * @return
     */
    @RequestMapping(value="multifileUpload.file",method=RequestMethod.POST)
    public ResponseBean multifileUpload(HttpServletRequest request) throws IOException {

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return ResponseBean.response(null);
        }
        List<SysFiles> sysFiles = FileUtil.multifileUpload( "file", filePath , files);

        return ResponseBean.response(sysFiles);
    }

    @RequestMapping("/download.file")
    public String downLoad(HttpServletResponse response, String filename){
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            LOGGER.info("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

}
