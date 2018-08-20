package ye.guo.huang.jwt.mapper;

import ye.guo.huang.jwt.pojo.KillItem;

public interface KillItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(KillItem record);

    int insertSelective(KillItem record);

    KillItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KillItem record);

    int updateByPrimaryKey(KillItem record);
}