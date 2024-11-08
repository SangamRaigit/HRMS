package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.Job;
import hr.management.system.model.ServerResponse;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_post_job);

        Spinner jobTittle = findViewById(R.id.job_role);

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Job job = new Job();
                job.setTitle(jobTittle.getSelectedItem().toString());
                job.setAlumniId(String.valueOf(new UserSession(PostJobActivity.this).getUserId()));
                job.setDescription(((EditText) findViewById(R.id.edt_desc)).getText().toString());
                job.setSalary(((EditText) findViewById(R.id.edt_salary)).getText().toString());
                job.setCompany(((EditText) findViewById(R.id.edt_company_name)).getText().toString());


                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<ServerResponse> call = apiInterface.addJobPost(job);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (call.isExecuted()) {
                            Toast.makeText(getApplicationContext(), "Job Posted Successfully", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error occur pls try again", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        findViewById(R.id.img_go_back).setOnClickListener(new
                                                              View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View v) {
                                                                      finish();
                                                                      startActivity(new Intent(getApplicationContext(), EmployeesHomeActivity.class));

                                                                  }
                                                              });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), EmployeesHomeActivity.class));
    }
}