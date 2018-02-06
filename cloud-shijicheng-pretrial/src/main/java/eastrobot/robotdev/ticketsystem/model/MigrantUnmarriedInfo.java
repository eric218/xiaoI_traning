package eastrobot.robotdev.ticketsystem.model;

public class MigrantUnmarriedInfo {
    private Integer id;

    private String applicationName;

    private String applicationId;

    private String expiryDate;

    private Integer registeredProvince;

    private Integer registeredCity;

    private Integer registeredArea;

    private String registeredAddress;

    private Integer domicileProvince;

    private Integer domicileCity;

    private Integer domicileArea;

    private String domicileAddress;

    private String notifyFormId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate == null ? null : expiryDate.trim();
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

    public String getNotifyFormId() {
        return notifyFormId;
    }

    public void setNotifyFormId(String notifyFormId) {
        this.notifyFormId = notifyFormId == null ? null : notifyFormId.trim();
    }
}