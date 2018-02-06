package eastrobot.robotdev.ticketsystem.model;

import java.util.Date;

public class MigrantMarriedInfo {
    private Integer id;

    private String femaleName;

    private String femaleId;

    private Integer femaleMaritalStatus;

    private String maleName;

    private String maleId;

    private Integer maleMaritalStatus;

    private String marriageCertificate;

    private String childrenNumber;

    private String sonNumber;

    private String daughterNumber;

    private Boolean departure;

    private Date departureTime;

    private Integer registeredProvince;

    private Integer registeredCity;

    private Integer registeredArea;

    private String registeredAddress;

    private Integer domicileProvince;

    private Integer domicileCity;

    private Integer domicileArea;

    private String domicileAddress;

    private Boolean birthControl;

    private Integer contraceptiveMode;

    private Boolean gynecologicalExamination;

    private String notifyFormId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFemaleName() {
        return femaleName;
    }

    public void setFemaleName(String femaleName) {
        this.femaleName = femaleName == null ? null : femaleName.trim();
    }

    public String getFemaleId() {
        return femaleId;
    }

    public void setFemaleId(String femaleId) {
        this.femaleId = femaleId == null ? null : femaleId.trim();
    }

    public Integer getFemaleMaritalStatus() {
        return femaleMaritalStatus;
    }

    public void setFemaleMaritalStatus(Integer femaleMaritalStatus) {
        this.femaleMaritalStatus = femaleMaritalStatus;
    }

    public String getMaleName() {
        return maleName;
    }

    public void setMaleName(String maleName) {
        this.maleName = maleName == null ? null : maleName.trim();
    }

    public String getMaleId() {
        return maleId;
    }

    public void setMaleId(String maleId) {
        this.maleId = maleId == null ? null : maleId.trim();
    }

    public Integer getMaleMaritalStatus() {
        return maleMaritalStatus;
    }

    public void setMaleMaritalStatus(Integer maleMaritalStatus) {
        this.maleMaritalStatus = maleMaritalStatus;
    }

    public String getMarriageCertificate() {
        return marriageCertificate;
    }

    public void setMarriageCertificate(String marriageCertificate) {
        this.marriageCertificate = marriageCertificate == null ? null : marriageCertificate.trim();
    }

    public String getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(String childrenNumber) {
        this.childrenNumber = childrenNumber == null ? null : childrenNumber.trim();
    }

    public String getSonNumber() {
        return sonNumber;
    }

    public void setSonNumber(String sonNumber) {
        this.sonNumber = sonNumber == null ? null : sonNumber.trim();
    }

    public String getDaughterNumber() {
        return daughterNumber;
    }

    public void setDaughterNumber(String daughterNumber) {
        this.daughterNumber = daughterNumber == null ? null : daughterNumber.trim();
    }

    public Boolean getDeparture() {
        return departure;
    }

    public void setDeparture(Boolean departure) {
        this.departure = departure;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getRegisteredProvince() {
        return registeredProvince;
    }

    public void setRegisteredProvince(Integer registeredProvince) {
        this.registeredProvince = registeredProvince;
    }

    public Integer getRegisteredCity() {
        return registeredCity;
    }

    public void setRegisteredCity(Integer registeredCity) {
        this.registeredCity = registeredCity;
    }

    public Integer getRegisteredArea() {
        return registeredArea;
    }

    public void setRegisteredArea(Integer registeredArea) {
        this.registeredArea = registeredArea;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress == null ? null : registeredAddress.trim();
    }

    public Integer getDomicileProvince() {
        return domicileProvince;
    }

    public void setDomicileProvince(Integer domicileProvince) {
        this.domicileProvince = domicileProvince;
    }

    public Integer getDomicileCity() {
        return domicileCity;
    }

    public void setDomicileCity(Integer domicileCity) {
        this.domicileCity = domicileCity;
    }

    public Integer getDomicileArea() {
        return domicileArea;
    }

    public void setDomicileArea(Integer domicileArea) {
        this.domicileArea = domicileArea;
    }

    public String getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress == null ? null : domicileAddress.trim();
    }

    public Boolean getBirthControl() {
        return birthControl;
    }

    public void setBirthControl(Boolean birthControl) {
        this.birthControl = birthControl;
    }

    public Integer getContraceptiveMode() {
        return contraceptiveMode;
    }

    public void setContraceptiveMode(Integer contraceptiveMode) {
        this.contraceptiveMode = contraceptiveMode;
    }

    public Boolean getGynecologicalExamination() {
        return gynecologicalExamination;
    }

    public void setGynecologicalExamination(Boolean gynecologicalExamination) {
        this.gynecologicalExamination = gynecologicalExamination;
    }

    public String getNotifyFormId() {
        return notifyFormId;
    }

    public void setNotifyFormId(String notifyFormId) {
        this.notifyFormId = notifyFormId == null ? null : notifyFormId.trim();
    }
}