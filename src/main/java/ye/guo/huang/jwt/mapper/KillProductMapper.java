package ye.guo.huang.jwt.mapper;

import ye.guo.huang.jwt.pojo.KillProduct;

public interface KillProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(KillProduct record);

    int insertSelective(KillProduct record);

    KillProduct selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KillProduct record);

    int updateByPrimaryKey(KillProduct record);
}