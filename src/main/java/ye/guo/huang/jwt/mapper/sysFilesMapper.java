package ye.guo.huang.jwt.mapper;

import ye.guo.huang.jwt.pojo.sysFiles;

public interface sysFilesMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(sysFiles record);

    int insertSelective(sysFiles record);

    sysFiles selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(sysFiles record);

    int updateByPrimaryKey(sysFiles record);
}