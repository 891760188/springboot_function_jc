package ye.guo.huang.jwt.mapper;

import ye.guo.huang.jwt.pojo.SysDataType;

public interface SysDataTypeMapper {
    int deleteByPrimaryKey(Integer cdId);

    int insert(SysDataType record);

    int insertSelective(SysDataType record);

    SysDataType selectByPrimaryKey(Integer cdId);

    int updateByPrimaryKeySelective(SysDataType record);

    int updateByPrimaryKey(SysDataType record);
}