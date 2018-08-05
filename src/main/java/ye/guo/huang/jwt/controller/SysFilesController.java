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
import ye.guo.huang.jwt.common.util.StringUtils;
import ye.guo.huang.jwt.pojo.SysFiles;
import ye.guo.huang.jwt.service.SysFilesService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    public @ResponseBody String multifileUpload(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return "false";
        }

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            String phyName  = StringUtils.getUUid();
            int size = (int) file.getSize();
            LOGGER.info(fileName + "-->" + size);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(filePath + "/" + phyName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }

}
