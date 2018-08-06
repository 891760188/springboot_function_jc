package ye.guo.huang.jwt.service;


import org.springframework.web.multipart.MultipartFile;
import ye.guo.huang.jwt.pojo.SysFiles;

import java.io.IOException;
import java.util.List;

public interface SysFilesService {

    List<SysFiles> selectAllFiles(int pageNum, int pageSize);

    List<SysFiles> multifileUpload(List<MultipartFile> files) throws IOException;

    int downloadTimePlus(String filename);

}
