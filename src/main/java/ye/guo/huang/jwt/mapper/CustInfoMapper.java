package ye.guo.huang.jwt.mapper;

import org.springframework.stereotype.Repository;
import ye.guo.huang.jwt.pojo.CustInfo;

import java.util.List;

@Repository
public interface CustInfoMapper {

    int insertBatch(List<CustInfo> custInfoList);

    int deleteByPrimaryKey(Integer custId);

    int insert(CustInfo record);

    int insertSelective(CustInfo record);

    CustInfo selectByPrimaryKey(Integer custId);

    int updateByPrimaryKeySelective(CustInfo record);

    int updateByPrimaryKey(CustInfo record);
}