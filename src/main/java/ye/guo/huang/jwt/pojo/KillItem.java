package ye.guo.huang.jwt.pojo;

import java.util.Date;

public class KillItem {
    private String id;

    private String killProductId;

    private String mobile;

    private Date killTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getKillProductId() {
        return killProductId;
    }

    public void setKillProductId(String killProductId) {
        this.killProductId = killProductId == null ? null : killProductId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getKillTime() {
        return killTime;
    }

    public void setKillTime(Date killTime) {
        this.killTime = killTime;
    }
}