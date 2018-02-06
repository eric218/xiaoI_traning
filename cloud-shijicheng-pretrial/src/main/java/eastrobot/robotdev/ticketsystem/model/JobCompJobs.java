package eastrobot.robotdev.ticketsystem.model;

public class JobCompJobs {
    private Integer id;

    private Integer companyId;

    private String position;

    private String education;

    private Integer number;

    private Integer sex;

    private String age;

    private String salary;

    private String reqiurement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getReqiurement() {
        return reqiurement;
    }

    public void setReqiurement(String reqiurement) {
        this.reqiurement = reqiurement == null ? null : reqiurement.trim();
    }
}