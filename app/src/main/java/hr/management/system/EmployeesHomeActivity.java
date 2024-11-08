package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import hr.management.system.sessionManagement.UserSession;

public class EmployeesHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_employee_home);

        ((TextView) findViewById(R.id.txt_user_name)).setText(new UserSession(getApplicationContext()).getName());


        findViewById(R.id.crd_applications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), AllApplicationsActivity.class));
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


        findViewById(R.id.crd_my_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ProfileScreenActivity.class));
            }
        });

        findViewById(R.id.crd_post_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), PostJobActivity.class));
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}