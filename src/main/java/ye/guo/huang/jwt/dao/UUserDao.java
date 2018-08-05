package ye.guo.huang.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ye.guo.huang.jwt.pojo.UUser;

public interface UUserDao extends JpaRepository<UUser,Integer> {

    UUser  findByNicknameAndPswd(String nickname,String pswd);

}
