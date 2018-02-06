package eastrobot.robotdev.ticketsystem.model.vo;

import java.math.BigInteger;

/**
 * Project:
 * Author: HONGMIN GAO
 * Date: 27/9/17
 * Descripthion: ...
 */
public class DbReturnOption {
    private static final long serialVersionUID = -293628144753493710L;
    private int id;
    private int optionId;
    private String optionContent;
    private BigInteger countOption;
    private String content;
    private int sequence;
    private int topicType;
    public DbReturnOption() {
        super();
    }


    public DbReturnOption(int id, int optionId, String optionContent, BigInteger countOption, String content, int sequence, int topicType) {
        super();
        this.id = id;
        this.optionId = optionId;
        this.optionContent = optionContent;
        this.countOption = countOption;
        this.content = content;
        this.sequence = sequence;
        this.topicType = topicType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public BigInteger getCountOption() {
        return countOption;
    }

    public void setCountOption(BigInteger countOption) {
        this.countOption = countOption;
    }
}
