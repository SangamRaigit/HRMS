package hr.management.system.sessionManagement;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    Context context;
    SharedPreferences sharedPreferences;

    private String name;
    private String email;
    private String password;
    private String mobile;
    private String role;
    private String predictedJob;
    private int userid;

    // Contructor
    public UserSession(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }

    // Log Out function
    public void removeUser() {
        sharedPreferences.edit().clear().commit();
    }


    // Getter Setter

    public String getEmail() {
        email = sharedPreferences.getString("email", "");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("email", email).commit();
    }

    public String getPassword() {
        email = sharedPreferences.getString("password", "");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        sharedPreferences.edit().putString("password", password).commit();
    }

    public int getUserId() {
        userid = sharedPreferences.getInt("id", 0);
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
        sharedPreferences.edit().putInt("id", userid).commit();
    }

    public String getName() {
        name = sharedPreferences.getString("name", "");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("name", name).commit();
    }

    public String getRole() {
        role = sharedPreferences.getString("type", "");
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        sharedPreferences.edit().putString("type", role).commit();
    }


    public String getMobile() {
        mobile = sharedPreferences.getString("mobile", "");
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        sharedPreferences.edit().putString("mobile", mobile).commit();
    }


    public String getPredictedJob() {
        predictedJob = sharedPreferences.getString("predictedJob", "");
        return predictedJob;
    }

    public void setPredictedJob(String predictedJob) {
        this.predictedJob = predictedJob;
        sharedPreferences.edit().putString("predictedJob", predictedJob).commit();
    }
}// End of class
