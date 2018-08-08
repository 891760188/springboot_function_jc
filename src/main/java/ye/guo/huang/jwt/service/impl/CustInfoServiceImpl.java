package ye.guo.huang.jwt.service.impl;

import org.springframework.stereotype.Service;
import ye.guo.huang.jwt.mapper.CustInfoMapper;
import ye.guo.huang.jwt.pojo.CustInfo;
import ye.guo.huang.jwt.service.CustInfoService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustInfoServiceImpl implements CustInfoService {

    @Resource
    private CustInfoMapper custInfoMapper ;

    @Override
    public int insertBatch(List<CustInfo> custInfoList) {
        return custInfoMapper.insertBatch(custInfoList);
    }
}
