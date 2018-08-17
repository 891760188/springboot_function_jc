package ye.guo.huang.jwt.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ye.guo.huang.jwt.common.Constant;
import ye.guo.huang.jwt.common.token.Token;
import ye.guo.huang.jwt.common.token.TokenCache;
import ye.guo.huang.jwt.common.util.JWTUtil;
import ye.guo.huang.jwt.common.util.MD5Utils;
import ye.guo.huang.jwt.common.util.StringUtils;
import ye.guo.huang.jwt.dao.UUserDao;
import ye.guo.huang.jwt.pojo.UUser;
import ye.guo.huang.jwt.service.UUserService;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

@Service
public class UUserServiceImpl implements UUserService {

    private static final Logger LOGGER = LogManager.getLogger(UUserServiceImpl.class);

    @Autowired
    private UUserDao uUserDao ;

    @Override
    public List<UUser> findAll() {
        return uUserDao.findAll();
    }

    @Override
    @Transactional
    public String regedit(UUser user) {
        String pswd = user.getPswd();
        pswd = MD5Utils.md5(pswd);
        user.setPswd(pswd);
        user = uUserDao.save(user);

        return  tokenJwt(user);
    }

    @Override
    public String findByNicknameAndPswd(String nickname, String pswd) {

        pswd = MD5Utils.md5(pswd);
        UUser user = uUserDao.findByNicknameAndPswd(nickname,pswd);
        if (user == null){
            return null;
        }else{
            return  tokenJwt(user);
        }
    }

    /**
     *  得到jwt，并且把和用户信息一起写入缓存
     * @param user 用户对象
     * @return
     */
    private String tokenJwt(UUser user){
        Token token = new Token();
        token.setTokenId(StringUtils.getUUid());
        token.setCreateTime(new Date());
        token.setUserId(String.valueOf(user.getId()));
        token.setUserName(user.getNickname());
        token.setDeadline(Constant.JWT_EXPIRE_TIME);
        token.setSecurity(user.getPswd());
        String jwt = JWTUtil.encryptJWT(token);
        TokenCache.setToken(jwt,token);
        return jwt;
    }
    public void aa(){
//        StringBuilder sb = null;
//        sb.append(1);
        //        sb.append(1);
    }
}
