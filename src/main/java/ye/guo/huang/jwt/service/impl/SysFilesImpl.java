package ye.guo.huang.jwt.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ye.guo.huang.jwt.mapper.SysFilesMapper;
import ye.guo.huang.jwt.pojo.SysFiles;
import ye.guo.huang.jwt.service.SysFilesService;

import java.util.List;

@Service
public class SysFilesImpl implements SysFilesService {

    @Autowired
    private SysFilesMapper sysFilesMapper ;

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
}
