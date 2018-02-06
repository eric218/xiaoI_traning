package eastrobot.robotdev.ticketsystem.model;

public class Attachment {
    private Integer id;

    private String fileName;

    private String fileAddr;

    private Integer seq;

    private String notifyFormId;

    private String modeCode;

    private Integer modeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileAddr() {
        return fileAddr;
    }

    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr == null ? null : fileAddr.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getNotifyFormId() {
        return notifyFormId;
    }

    public void setNotifyFormId(String notifyFormId) {
        this.notifyFormId = notifyFormId == null ? null : notifyFormId.trim();
    }

    public String getModeCode() {
        return modeCode;
    }

    public void setModeCode(String modeCode) {
        this.modeCode = modeCode == null ? null : modeCode.trim();
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }
}