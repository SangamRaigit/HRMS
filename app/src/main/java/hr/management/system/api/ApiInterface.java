package hr.management.system.api;

import java.util.List;

import hr.management.system.model.Applications;
import hr.management.system.model.Job;
import hr.management.system.model.ServerResponse;
import hr.management.system.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<List<User>> loginToServer(@Query("email") String email, @Query("password") String password);

    @POST("register")
    Call<ServerResponse> signUpToServer(@Body User user);

    @POST("jobRolePredication")
    Call<ServerResponse> jobRolePredication(@Query("inputs") String inputs);

    @POST("addJobPost")
    Call<ServerResponse> addJobPost(@Body Job job);

    @POST("allJobs")
    Call<List<Job>> allJobs();

    @POST("recommendedJobs")
    Call<List<Job>> recommendedJobs(@Query("role") String role);

    @POST("allJobseekers")
    Call<List<User>> allStudent();

    @POST("allEmployees")
    Call<List<User>> allAlumni();

    @POST("applyJob")
    Call<ServerResponse> applyJob(@Body Applications applications);

    @POST("allApplication")
    Call<List<Applications>> allApplication(@Query("employeeId") String alumniId);

    @POST("updateUser")
    Call<ServerResponse> updateUser(@Body User user);

}

