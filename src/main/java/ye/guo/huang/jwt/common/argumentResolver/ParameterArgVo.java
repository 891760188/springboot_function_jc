package ye.guo.huang.jwt.common.argumentResolver;

import java.util.Date;

public class ParameterArgVo {

    private int id ;

    private String name ;

    private Date crtDt ;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
}
