package sample;

public class Job {
    private Long salary;//зарплата в рублях
    private String name;//название работы
    private String education;//образование
    private String workSchedule;//график работы
    private String workExperience;//опыт работы
    private String city;//город
    private String requirements;//требования
    private String contactInformation;//контактная информация

    public Job(Long salary, String education, String workSchedule,
               String workExperience, String city, String requirements, String name, String contactInformation) {
        this.salary = salary;
        this.education = education;
        this.workSchedule = workSchedule;
        this.workExperience = workExperience;
        this.city = city;
        this.requirements = requirements;
        this.name = name;
        this.contactInformation = contactInformation;
    }

    /*ГЕТТЕРЫ*/
    public Long getSalary() {
        return salary;
    }

    public String getEducation() {
        return education;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getCity() {
        return city;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getName() {
        return name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    /*СЕТТЕРЫ*/
    public void setEducation(String education) {
        this.education = education;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

}
