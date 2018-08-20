package ye.guo.huang.jwt.pojo;

import java.util.Date;

public class KillProduct {
    private String id;

    private String productId;

    private String killDescription;

    private Integer number;

    private Date startTime;

    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getKillDescription() {
        return killDescription;
    }

    public void setKillDescription(String killDescription) {
        this.killDescription = killDescription == null ? null : killDescription.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}