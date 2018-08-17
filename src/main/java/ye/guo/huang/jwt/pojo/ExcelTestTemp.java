package ye.guo.huang.jwt.pojo;

public class ExcelTestTemp {
    private Integer id;

    private String name;

    private String card;

    private String remark;


    public ExcelTestTemp(String name,String card){
        this.name = name;
        this.card = card;
    }

    public ExcelTestTemp(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}