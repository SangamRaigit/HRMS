
package hr.management.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Job implements Serializable {


    @Expose
    @SerializedName("id")
    private int id;

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

    @Expose
    @SerializedName("employeeId")
    private String alumniId;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlumniId() {
        return alumniId;
    }

    public void setAlumniId(String alumniId) {
        this.alumniId = alumniId;
    }
}
