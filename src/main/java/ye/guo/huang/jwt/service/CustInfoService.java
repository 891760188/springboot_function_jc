package ye.guo.huang.jwt.service;

import ye.guo.huang.jwt.pojo.CustInfo;

import java.util.List;

public interface CustInfoService {

    int insertBatch(List<CustInfo> custInfoList);

}
