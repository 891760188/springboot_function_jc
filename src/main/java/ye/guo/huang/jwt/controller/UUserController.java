package ye.guo.huang.jwt.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.pojo.UUser;
import ye.guo.huang.jwt.service.UUserService;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

@RestController
@RequestMapping("/api/user")
public class UUserController {

    private static final Logger LOGGER = LogManager.getLogger(UUserController.class);

    @Autowired
    private UUserService uUserService ;

    @GetMapping("/findAll.json")
    public ResponseBean findAll() {

        return new ResponseBean(uUserService.findAll());
    }

    @PostMapping("/regedit.do")
    public  ResponseBean regedit(UUser uUser){
        String jwt = uUserService.regedit(uUser);
        return new ResponseBean(jwt);
    }

    @PostMapping("/login.do")
    public  ResponseBean login(UUser uUser){
        String jwt = uUserService.findByNicknameAndPswd(uUser.getNickname(),uUser.getPswd());
        return new ResponseBean(jwt);
    }

}
