package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.ServerResponse;
import hr.management.system.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText edt_email, edt_password, edt_mobile, edt_name;
    private TextView txt_log_in;
    private ApiInterface apiInterface;
    private String type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_mobile = findViewById(R.id.edt_mobile);
        edt_name = findViewById(R.id.edt_name);
        txt_log_in = findViewById(R.id.txt_log_in);


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate())
                    signUp();
            }
        });

        findViewById(R.id.txt_log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


        TextView jobseeker = findViewById(R.id.txt_jobseeker);
        TextView employee = findViewById(R.id.txt_employee);


        jobseeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorRed(jobseeker);
                changeColorGrey(employee);
                type = "jobseeker";
            }
        });


        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColorRed(employee);
                changeColorGrey(jobseeker);
                type = "employee";
            }
        });
    }


    public void changeColorRed(TextView button) {
        Drawable buttonDrawable1 = button.getBackground();
        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
        DrawableCompat.setTint(buttonDrawable1, Color.GREEN);
    }

    public void changeColorGrey(TextView button) {
        Drawable buttonDrawable1 = button.getBackground();
        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
        DrawableCompat.setTint(buttonDrawable1, Color.GRAY);
    }


    private void signUp() {

        // Show Loading Dialog
        final ProgressDialog progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Pls wait ....... ");
        progressDialog.setCancelable(false);
        progressDialog.show();


        User user = new User();
        user.setEmail(edt_email.getText().toString());
        user.setMobile(edt_mobile.getText().toString());
        user.setPassword(edt_password.getText().toString());
        user.setName(edt_name.getText().toString());
        user.setType(type);

        Call<ServerResponse> call = apiInterface.signUpToServer(user);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (call.isExecuted()) {
                    progressDialog.dismiss();
                    if (response.body().getUserId() == 0) {
                        showToast("Error occur pls try again");
                    } else showToast("Registered Successfully");
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                showToast(t.toString());
                progressDialog.dismiss();
            }
        });

    }

    private boolean isValidate() {

        boolean result = true;
        if (edt_email.getText().toString().isEmpty() || (!edt_email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            edt_email.requestFocusFromTouch();
            edt_email.setError("Pls provide valid email");
            result = false;
        }

        if (edt_password.getText().toString().isEmpty()) {
            edt_password.requestFocusFromTouch();
            edt_password.setError("Pls enter password");
            result = false;
        }

        if (edt_mobile.getText().toString().isEmpty() || (!edt_mobile.getText().toString().matches("(0/91)?[7-9][0-9]{9}"))) {
            edt_mobile.requestFocusFromTouch();
            edt_mobile.setError("Pls provide valid number");
            result = false;
        }

        if (edt_name.getText().toString().length() == 0 || (!edt_name.getText().toString().matches("[a-zA-Z ]+"))) {
            edt_name.requestFocusFromTouch();
            edt_name.setError("Pls provide valid name");
            result = false;
        }

        if (type == null) {
            showToast("Please select type of category");
            result = false;
        }


        return result;
    }

    public void showToast(String test) {
        Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
    }

}