package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_all_users);

        String type = getIntent().getExtras().get("type").toString();
        ((TextView) findViewById(R.id.user_type_head)).setText(type);


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<User>> call = null;

        if (type.equals("All Jobseekers")) {
            call = apiInterface.allStudent();
        } else {
            call = apiInterface.allAlumni();
        }

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (call.isExecuted()) {
                    if (response.body().size() == 0) {
                        Toast.makeText(getApplicationContext(), "No Job Found", Toast.LENGTH_LONG).show();
                    } else {
                        UserAdapter itemAdapter = new UserAdapter(response.body(), getApplicationContext());
                        GridLayoutManager gridLayout = new GridLayoutManager(getApplicationContext(), 1);
                        RecyclerView recyclerView = findViewById(R.id.user_recycle_view);
                        recyclerView.setAdapter(itemAdapter);
                        recyclerView.setLayoutManager(gridLayout);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error occur try again " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
    }
}