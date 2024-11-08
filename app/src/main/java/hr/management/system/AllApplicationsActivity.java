package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.List;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.Applications;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllApplicationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();


        setContentView(R.layout.activity_all_applications);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Applications>> call = apiInterface.allApplication(String.valueOf(new UserSession(getApplicationContext()).getUserId()));

        call.enqueue(new Callback<List<Applications>>() {
            @Override
            public void onResponse(Call<List<Applications>> call, Response<List<Applications>> response) {
                if (call.isExecuted()) {
                    if (response.body().size() == 0) {
                        Toast.makeText(getApplicationContext(), "No Application Found", Toast.LENGTH_LONG).show();
                    } else {
                        ApplicationsAdapter itemAdapter = new ApplicationsAdapter(response.body(), getApplicationContext());
                        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 1);
                        RecyclerView recyclerView = findViewById(R.id.applications_recycle_view);
                        recyclerView.setAdapter(itemAdapter);
                        recyclerView.setLayoutManager(gridLayout);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Applications>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error occur try again " + t.toString(), Toast.LENGTH_LONG).show();

            }
        });


        findViewById(R.id.go_back).setOnClickListener(new
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
        finish();
        startActivity(new Intent(getApplicationContext(), EmployeesHomeActivity.class));
    }


}