package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import hr.management.system.sessionManagement.UserSession;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_home);



        findViewById(R.id.crd_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession userSession = new UserSession(getApplicationContext());
                userSession.removeUser();

                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });



        findViewById(R.id.crd_js).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), AllUsersActivity.class);
                intent.putExtra("type","All Student");
                startActivity(intent);

            }
        });



        findViewById(R.id.crd_employees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), AllUsersActivity.class);
                intent.putExtra("type","All Alumni");
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