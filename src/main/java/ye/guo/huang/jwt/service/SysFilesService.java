package ye.guo.huang.jwt.service;


import ye.guo.huang.jwt.pojo.SysFiles;

import java.util.List;

public interface SysFilesService {

    List<SysFiles> selectAllFiles(int pageNum, int pageSize);

}
