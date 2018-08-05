package ye.guo.huang.jwt.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import ye.guo.huang.jwt.pojo.SysFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                sysFile.setType(modelType);
                sysFile.setSuffix(suffix.substring(1,suffix.length()));
                sysFiles.add(sysFile);
            }
        }
        return sysFiles ;
    }

}
