package ye.guo.huang.jwt.service;

import ye.guo.huang.jwt.pojo.UUser;

import java.util.List;

public interface UUserService {
    List<UUser> findAll();

    String regedit(UUser user);

    String  findByNicknameAndPswd(String nickname,String pswd);

}
