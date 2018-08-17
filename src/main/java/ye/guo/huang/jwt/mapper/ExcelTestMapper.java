package ye.guo.huang.jwt.mapper;

import org.springframework.stereotype.Repository;
import ye.guo.huang.jwt.pojo.ExcelTest;

import java.util.List;

@Repository
public interface ExcelTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExcelTest record);

    int insertBatch(List<ExcelTest> recordList);

    int insertCheck();

    int insertSelective(ExcelTest record);

    ExcelTest selectByPrimaryKey(Integer id);

    List<ExcelTest> selectByExcelTest(ExcelTest excelTest);

    int updateByPrimaryKeySelective(ExcelTest record);

    int updateByPrimaryKey(ExcelTest record);
}