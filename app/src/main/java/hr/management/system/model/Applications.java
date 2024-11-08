
package hr.management.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Applications {


    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("jobId")
    private String jobId;

    @Expose
    @SerializedName("jobseekerId")
    private String studentId;

    @Expose
    @SerializedName("employeeId")
    private String alumniId;


    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("mobile")
    private String mobile;

    @Expose
    @SerializedName("email")
    private String email;


    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("salary")
    private String salary;


    @Expose
    @SerializedName("company")
    private String company;

    @Expose
    @SerializedName("description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAlumniId() {
        return alumniId;
    }

    public void setAlumniId(String alumniId) {
        this.alumniId = alumniId;
    }
}
