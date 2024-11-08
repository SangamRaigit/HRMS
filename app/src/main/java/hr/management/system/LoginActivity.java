package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.User;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText edt_email, edt_password;
    private TextView txt_create_account;
    private Button btn_submit;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (!new UserSession(getApplicationContext()).getEmail().isEmpty()) {
            checkRole(getUserSession());
        }

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {
                    loginAuthentication();
                }
            }
        });


        findViewById(R.id.txt_create_account).setOnClickListener(new View.OnClickListener() {
                                                                     @Override
                                                                     public void onClick(View v) {
                                                                         finish();
                                                                         startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                                                                     }
                                                                 }
        );
    }


    private void loginAuthentication() {

        // Show Loading Dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Pls wait ....... ");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<List<User>> call = apiInterface.loginToServer(edt_email.getText().toString(), edt_password.getText().toString());
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (call.isExecuted()) {
                    progressDialog.dismiss();

                    Log.d("Resp", response.body().toString());
                    if (response.body().isEmpty()) {
                        showToast("You haven't register with us pls do register");
                    } else {
                        Log.e("User", response.body().get(0).toString());
                        setUserSession(response.body().get(0));
                        checkRole(response.body().get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                showToast(t.toString());
                progressDialog.dismiss();
            }
        });
    }

    private boolean isValidate() {

        if (edt_email.getText().toString().isEmpty() || (!edt_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            edt_email.requestFocusFromTouch();
            edt_email.setError("Pls provide valid email");
            return false;
        }

        if (edt_password.getText().toString().isEmpty()) {
            edt_password.requestFocusFromTouch();
            edt_password.setError("Pls enter password");
            return false;
        }

        return true;
    }

    public void showToast(String test) {
        Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
    }


    public void setUserSession(User user) {
        UserSession userSession = new UserSession(getApplicationContext());
        userSession.setRole(user.getType());
        userSession.setEmail(user.getEmail());
        userSession.setMobile(String.valueOf(user.getMobile()));
        userSession.setName(user.getName());
        userSession.setPassword(user.getPassword());
        userSession.setUserId(user.getUserid());
        userSession.setPredictedJob(user.getJob());
    }


    public User getUserSession() {
        UserSession userSession = new UserSession(getApplicationContext());
        User user = new User();

        user.setType(userSession.getRole());
        user.setEmail(userSession.getEmail());
        if (!userSession.getMobile().equals("")) {
            user.setMobile(userSession.getMobile());
        }
        user.setName(userSession.getName());
        user.setPassword(userSession.getPassword());
        user.setUserid(userSession.getUserId());
        return user;
    }


    public void checkRole(User user) {

        if (user.getType().equals("student")) {
            finish();
            Intent intent = new Intent(getApplicationContext(), JobseekerHomeActivity.class);
            intent.putExtra("User", (Serializable) user);
            startActivity(intent);
        } else if (user.getType().equals("admin")) {
            finish();
            Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
            intent.putExtra("User", (Serializable) user);
            startActivity(intent);
        } else {
            finish();
            Intent intent = new Intent(getApplicationContext(), EmployeesHomeActivity.class);
            intent.putExtra("User", (Serializable) user);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAndRemoveTask();
        finish();
        System.exit(0);
    }
}