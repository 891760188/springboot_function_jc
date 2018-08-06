package ye.guo.huang.jwt.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import ye.guo.huang.jwt.pojo.SysFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *  文件上传下载工具类
 * @author 448249687@qq.com
 * @Date 2018/8/6
 *
 */

public class FileUtil {

    private static final Logger LOGGER = LogManager.getLogger(FileUtil.class);

    public static List<SysFiles> multifileUpload(String modelType,String filePath ,  List<MultipartFile> files) throws IOException {

        List<SysFiles> sysFiles = new ArrayList<SysFiles>();
        for(MultipartFile file : files){

            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
            String phyName  = StringUtils.getUUid() + suffix;
            int size = (int) file.getSize();
            LOGGER.info(fileName + "-->" + size);

            if(file.isEmpty()){
//                return null;
            }else{
                File dest = new File(filePath + "/" + phyName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                file.transferTo(dest);
                SysFiles sysFile = new SysFiles();
                sysFile.setOrinName(fileName);
                sysFile.setPhyName(phyName);
                sysFile.setSize(size);
                sysFile.setStatus(1);
                sysFile.setDownTime(0);
                sysFile.setType(modelType);
                sysFile.setSuffix(suffix.substring(1,suffix.length()));
                sysFiles.add(sysFile);
            }
        }
        return sysFiles ;
    }


    public static boolean downLoad(HttpServletResponse response,String filename , String filePath) throws IOException {
        File file = new File(filePath + "/" + filename);
        if(null == file || file.exists()){ //判断文件父目录是否存在
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
            bis.close();
            fis.close();
        }else{
            return false ;
        }
        return true ;
    }

}
