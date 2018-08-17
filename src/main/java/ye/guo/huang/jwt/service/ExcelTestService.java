package ye.guo.huang.jwt.service;

public interface ExcelTestService {
    //导入数据
    long importDatas();
    //导入并且更新数据
    long importUpdateDatas();
    //导入并且更新数据 - 在map里比较
    String importUpdateDatasMap();
}
