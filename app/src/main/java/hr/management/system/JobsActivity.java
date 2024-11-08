package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.Job;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_jobs);

        String type = getIntent().getExtras().get("type").toString();
        ((TextView) findViewById(R.id.job_type_head)).setText(type);


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Job>> call = null;

        if (type.equals("All Available Job's")) {
            call = apiInterface.allJobs();
        } else {
            call = apiInterface.recommendedJobs(new UserSession(getApplicationContext()).getPredictedJob());
        }

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (call.isExecuted()) {
                    if (response.body().size() == 0) {
                        Toast.makeText(getApplicationContext(), "No Job Found", Toast.LENGTH_LONG).show();
                    } else {
                        JobAdapter itemAdapter = new JobAdapter(response.body(), getApplicationContext());
                        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 1);
                        RecyclerView recyclerView = findViewById(R.id.job_recycle_view);
                        recyclerView.setAdapter(itemAdapter);
                        recyclerView.setLayoutManager(gridLayout);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error occur try again " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });


        findViewById(R.id.go_back).setOnClickListener(new
                                                                  View.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(View v) {
                                                                          finish();
                                                                          startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));

                                                                      }
                                                                  });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));
    }
}