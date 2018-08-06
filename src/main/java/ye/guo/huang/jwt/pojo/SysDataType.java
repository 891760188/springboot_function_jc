package ye.guo.huang.jwt.pojo;

import java.util.Date;

public class SysDataType {
    private Integer cdId;

    private String cdName;

    private Integer status;

    private Date lastDate;

    private Date crtDate;

    private String remark;

    private String crtPsn;

    private String upPsn;

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName == null ? null : cdName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCrtPsn() {
        return crtPsn;
    }

    public void setCrtPsn(String crtPsn) {
        this.crtPsn = crtPsn == null ? null : crtPsn.trim();
    }

    public String getUpPsn() {
        return upPsn;
    }

    public void setUpPsn(String upPsn) {
        this.upPsn = upPsn == null ? null : upPsn.trim();
    }
}