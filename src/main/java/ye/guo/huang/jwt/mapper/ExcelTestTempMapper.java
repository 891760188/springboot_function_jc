package ye.guo.huang.jwt.mapper;

import org.springframework.stereotype.Repository;
import ye.guo.huang.jwt.pojo.ExcelTestTemp;

import java.util.List;

@Repository
public interface ExcelTestTempMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAll();

    int updateErrMsg();

    List<ExcelTestTemp> selectErrList();

    int insertBatch(List<ExcelTestTemp> recordList);

    int insert(ExcelTestTemp record);

    int insertSelective(ExcelTestTemp record);

    ExcelTestTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExcelTestTemp record);

    int updateByPrimaryKey(ExcelTestTemp record);
}