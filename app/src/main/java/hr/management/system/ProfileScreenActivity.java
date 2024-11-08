package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import hr.management.system.sessionManagement.UserSession;

public class ProfileScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_profile_screen);

        UserSession userSession = new UserSession(getApplicationContext());

        findViewById(R.id.back_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if (userSession.getRole().equals("student")) {
                    startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), EmployeesHomeActivity.class));
                }
            }
        });

        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText mobile = findViewById(R.id.mobile);
        EditText jobRole = findViewById(R.id.jobRole);


        name.setText(userSession.getName());
        email.setText(userSession.getEmail());
        mobile.setText(userSession.getMobile());

        if (userSession.getRole().equals("student")) {
            jobRole.setText(userSession.getPredictedJob());
        } else {

            findViewById(R.id.city_box2).setVisibility(View.GONE);
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        if (new UserSession(getApplicationContext()).getRole().equals("student")) {
            startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), EmployeesHomeActivity.class));
        }
    }
}
