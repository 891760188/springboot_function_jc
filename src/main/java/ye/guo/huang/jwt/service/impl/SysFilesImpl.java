package ye.guo.huang.jwt.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ye.guo.huang.jwt.common.util.FileUtil;
import ye.guo.huang.jwt.controller.SysFilesController;
import ye.guo.huang.jwt.mapper.SysFilesMapper;
import ye.guo.huang.jwt.pojo.SysFiles;
import ye.guo.huang.jwt.service.SysFilesService;

import java.io.IOException;
import java.util.List;

@Service
public class SysFilesImpl implements SysFilesService {

    private static final Logger LOGGER = LogManager.getLogger(SysFilesImpl.class);

    @Autowired
    private SysFilesMapper sysFilesMapper ;

    @Value("${constant.filepath}")
    private String filePath;

    /**
     * * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     *     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     *     * pageNum 开始页数
     *     * pageSize 每页显示的数据条数
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<SysFiles> selectAllFiles(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return sysFilesMapper.selectAllFiles();
    }

    @Override
    @Transactional
    public List<SysFiles> multifileUpload(List<MultipartFile> files) throws IOException {
        List<SysFiles> sysFiles = FileUtil.multifileUpload( "file", filePath , files);
        if( null == sysFiles){
            return null;
        }
        int size = sysFilesMapper.multifileUpload(sysFiles);
        LOGGER.info("文件上传个数："+sysFiles.size()+"======="+size);
        return sysFiles;
    }

    @Override
    public int downloadTimePlus(String filename) {
        return sysFilesMapper.downloadTimePlus(filename);
    }


}
