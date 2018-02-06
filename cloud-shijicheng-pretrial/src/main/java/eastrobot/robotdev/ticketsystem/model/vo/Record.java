package eastrobot.robotdev.ticketsystem.model.vo;

import java.util.List;

/**
 * Project:
 * Author: HONGMIN GAO
 * Date: 27/9/17
 * Descripthion: ...
 */
public class Record {


    private int id;
    private String type;
    private String title;
    private List<Option> options;
    private Integer sum;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
