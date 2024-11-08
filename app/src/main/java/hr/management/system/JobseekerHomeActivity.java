package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import hr.management.system.sessionManagement.UserSession;

public class JobseekerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_js_home);


        ((TextView) findViewById(R.id.txt_user_name)).setText(new UserSession(getApplicationContext()).getName());

        findViewById(R.id.crd_my_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ProfileScreenActivity.class));
            }
        });

        findViewById(R.id.crd_job_prediction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), JobRolePredictionActivity.class));
            }
        });


        findViewById(R.id.crd_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession userSession = new UserSession(getApplicationContext());
                userSession.removeUser();

                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        findViewById(R.id.crd_recommended_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new UserSession(getApplicationContext()).getPredictedJob().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Predict you carrier first", Toast.LENGTH_SHORT).show();
                }else {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), JobsActivity.class);
                    intent.putExtra("type","Recommended Job's");
                    startActivity(intent);
                }
            }
        });


        findViewById(R.id.crd_all_jobs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), JobsActivity.class);
                intent.putExtra("type","All Available Job's");
                startActivity(intent);            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}

