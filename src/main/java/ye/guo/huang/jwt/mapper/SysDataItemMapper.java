package ye.guo.huang.jwt.mapper;

import ye.guo.huang.jwt.pojo.SysDataItem;

public interface SysDataItemMapper {
    int deleteByPrimaryKey(Integer cdValId);

    int insert(SysDataItem record);

    int insertSelective(SysDataItem record);

    SysDataItem selectByPrimaryKey(Integer cdValId);

    int updateByPrimaryKeySelective(SysDataItem record);

    int updateByPrimaryKey(SysDataItem record);
}