package eastrobot.robotdev.ticketsystem.model.vo;
import java.math.BigInteger;

/**
 * Project:
 * Author: HONGMIN GAO
 * Date: 27/9/17
 * Descripthion: ...
 */
public class DbReturnTopic {
    private static final long serialVersionUID = -293628144753493710L;

    private Integer id;
    private String title;
    private Integer sqence;
    private BigInteger topicSum;
    private Integer topicType;

    public DbReturnTopic() {
        super();
    }

    public DbReturnTopic(Integer id, String title, Integer sqence, BigInteger topicSum, Integer topicType) {
        super();
        this.id = id;
        this.title = title;
        this.sqence = sqence;
        this.topicSum = topicSum;
        this.topicType = topicType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSqence() {
        return sqence;
    }

    public void setSqence(Integer sqence) {
        this.sqence = sqence;
    }

    public BigInteger getTopicSum() {
        return topicSum;
    }

    public void setTopicSum(BigInteger topicSum) {
        this.topicSum = topicSum;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }
}
