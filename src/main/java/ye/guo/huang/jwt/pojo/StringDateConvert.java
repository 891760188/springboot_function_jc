package ye.guo.huang.jwt.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;


import java.sql.Timestamp;
import java.util.Date;

public class StringDateConvert {
    private static final Logger LOGGER = LogManager.getLogger(StringDateConvert.class);

    private Long id ;

    private String dateStr ;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date ;

    private Timestamp timestamp ;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestampStr(String timestampStr) {
        this.timestampStr = timestampStr;
    }

    public Long getId() {

        return id;
    }

    public String getDateStr() {
        return dateStr;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getTimestampStr() {
        return timestampStr;
    }

    private String timestampStr ;


}
