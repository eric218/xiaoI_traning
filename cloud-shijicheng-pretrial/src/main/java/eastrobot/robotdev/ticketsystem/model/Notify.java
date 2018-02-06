package eastrobot.robotdev.ticketsystem.model;

import java.util.Date;

public class Notify {
    private Integer formNo;

    private String manageObject;

    private String undertakingDepart;

    private String address;

    private String saleCall;

    private String processingTime;

    private String completionTime;

    private Integer formType;

    private Date createTime;

    private Integer approvalStatus;

    private String approvalPerson;

    private String approvalResult;

    private String proposerName;

    private String proposePhone;

    private Integer proposeMaritalStatus;

    public Integer getFormNo() {
        return formNo;
    }

    public void setFormNo(Integer formNo) {
        this.formNo = formNo;
    }

    public String getManageObject() {
        return manageObject;
    }

    public void setManageObject(String manageObject) {
        this.manageObject = manageObject == null ? null : manageObject.trim();
    }

    public String getUndertakingDepart() {
        return undertakingDepart;
    }

    public void setUndertakingDepart(String undertakingDepart) {
        this.undertakingDepart = undertakingDepart == null ? null : undertakingDepart.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSaleCall() {
        return saleCall;
    }

    public void setSaleCall(String saleCall) {
        this.saleCall = saleCall == null ? null : saleCall.trim();
    }

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime == null ? null : processingTime.trim();
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime == null ? null : completionTime.trim();
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalPerson() {
        return approvalPerson;
    }

    public void setApprovalPerson(String approvalPerson) {
        this.approvalPerson = approvalPerson == null ? null : approvalPerson.trim();
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult == null ? null : approvalResult.trim();
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName == null ? null : proposerName.trim();
    }

    public String getProposePhone() {
        return proposePhone;
    }

    public void setProposePhone(String proposePhone) {
        this.proposePhone = proposePhone == null ? null : proposePhone.trim();
    }

    public Integer getProposeMaritalStatus() {
        return proposeMaritalStatus;
    }

    public void setProposeMaritalStatus(Integer proposeMaritalStatus) {
        this.proposeMaritalStatus = proposeMaritalStatus;
    }
}