package ye.guo.huang.jwt.pojo;

import java.util.Date;

public class SysFiles {
    private Integer fileId;

    private String orinName;

    private String phyName;

    private Integer status;

    private Integer size;

    private String suffix;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    private Date lastDate;

    private Date crtDate;

    private String remark;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getOrinName() {
        return orinName;
    }

    public void setOrinName(String orinName) {
        this.orinName = orinName == null ? null : orinName.trim();
    }

    public String getPhyName() {
        return phyName;
    }

    public void setPhyName(String phyName) {
        this.phyName = phyName == null ? null : phyName.trim();
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
}