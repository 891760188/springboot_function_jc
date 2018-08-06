package ye.guo.huang.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.common.cache.ItemCache;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

@RestController
@RequestMapping("/api/item")
public class SysDataItemController {

    @Autowired
    private ItemCache itemCache;

    @RequestMapping("/all.json")
    public ResponseBean downLoad() {

        return ResponseBean.response(itemCache.getLookupItemCache());
    }

}
