package ye.guo.huang.jwt.mapper;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ye.guo.huang.jwt.pojo.SysFiles;

import java.util.List;

@Repository
public interface SysFilesMapper {

    List<SysFiles> selectAllFiles();

    List<SysFiles> multifileUpload(List<MultipartFile> files);

    int deleteByPrimaryKey(Integer fileId);

    int insert(SysFiles record);

    int insertSelective(SysFiles record);

    SysFiles selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(SysFiles record);

    int updateByPrimaryKey(SysFiles record);
}