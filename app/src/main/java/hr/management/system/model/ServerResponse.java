package hr.management.system.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {


    @Expose
    @SerializedName("result")
    private String result;

    @Expose
    @SerializedName("error")
    private String error;

    @Expose
    @SerializedName("id")
    private int userId;

    @Expose
    @SerializedName("role")
    private String role;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return "ServerResponse{" +
                "result='" + result + '\'' +
                ", error='" + error + '\'' +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
