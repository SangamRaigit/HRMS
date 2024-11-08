
package hr.management.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User implements Serializable {

    @Expose
    @SerializedName("id")
    private int userid;

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
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("company")
    private String company;

    @Expose
    @SerializedName("job")
    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
