package ye.guo.huang.jwt.mapper;

import org.springframework.stereotype.Repository;
import ye.guo.huang.jwt.pojo.SysDataItem;

import java.util.List;

@Repository
public interface SysDataItemMapper {

    List<SysDataItem> findAll();

    int deleteByPrimaryKey(Integer cdValId);

    int insert(SysDataItem record);

    int insertSelective(SysDataItem record);

    SysDataItem selectByPrimaryKey(Integer cdValId);

    int updateByPrimaryKeySelective(SysDataItem record);

    int updateByPrimaryKey(SysDataItem record);
}