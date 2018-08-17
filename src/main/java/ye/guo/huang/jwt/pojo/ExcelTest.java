package ye.guo.huang.jwt.pojo;

public class ExcelTest {
    private Integer id;

    private String name;

    private String card;


    public  ExcelTest(){}

    public  ExcelTest(String name,String card){
        this.name = name ;
        this.card = card ;

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
}