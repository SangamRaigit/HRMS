package hr.management.system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.ServerResponse;
import hr.management.system.model.User;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobRolePredictionActivity extends AppCompatActivity {

    EditText no_of_mini_projects, no_of_mega_projects;
    Spinner extracurricular, coresub_skill, ds_coding, abstractthink_skill, programming_skill, aptitude_skill, problemsolving_skill, cgpa, design_skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_carrier_job_role_prediction);


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {

//                    'cgpa', 'no_of_miniprojects', 'no_of_projects', 'coresub_skill',
//                            'aptitude_skill', 'problemsolving_skill', 'programming_skill',
//                            'abstractthink_skill', 'design_skill', 'ds_coding', 'extracurricular']:

                    String inputs =
                            String.valueOf(cgpa.getSelectedItemPosition()) + "," +
                                    String.valueOf(no_of_mini_projects.getText().toString()) + "," +
                                    String.valueOf(no_of_mega_projects.getText().toString()) + "," +
                                    String.valueOf(coresub_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(aptitude_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(problemsolving_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(programming_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(abstractthink_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(design_skill.getSelectedItemPosition()) + "," +
                                    String.valueOf(ds_coding.getSelectedItemPosition()) + "," +
                                    String.valueOf(extracurricular.getSelectedItemPosition());


                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<ServerResponse> serverResponseCall = apiInterface.jobRolePredication(inputs);
                    serverResponseCall.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (call.isExecuted()) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(JobRolePredictionActivity.this);
                                builder.setTitle("Job Role Prediction");
                                builder.setMessage("System has predicted " + response.body().getRole() + " as your job role would you like to set this as your role ");
                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        // Do nothing but close the dialog
                                        new UserSession(JobRolePredictionActivity.this).setPredictedJob(response.body().getRole());

                                        Call<ServerResponse> call1 = apiInterface.updateUser(getUserSession());
                                        call1.enqueue(new Callback<ServerResponse>() {
                                            @Override
                                            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                                Log.e("Res", response.body().toString());
                                                if (call1.isExecuted()) {
                                                    dialog.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Job Updated Successfully", Toast.LENGTH_LONG).show();
                                                    finish();
                                                    startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));
                                                } else {
                                                    dialog.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Error Occur Pls Try Again", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ServerResponse> call, Throwable t) {
                                                dialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error Occur Pls Try Again", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }
                                });


                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Do nothing
                                        dialog.dismiss();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error " + t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


        cgpa = (Spinner) findViewById(R.id.cgpa);
        no_of_mini_projects = findViewById(R.id.no_of_mini_projects);
        no_of_mega_projects = findViewById(R.id.no_of_mega_projects);
        extracurricular = findViewById(R.id.extracurricular);
        coresub_skill = findViewById(R.id.coresub_skill);
        design_skill = findViewById(R.id.design_skill);
        ds_coding = findViewById(R.id.ds_coding);
        abstractthink_skill = findViewById(R.id.abstractthink_skill);
        programming_skill = findViewById(R.id.programming_skill);
        aptitude_skill = findViewById(R.id.aptitude_skill);
        problemsolving_skill = findViewById(R.id.problem_solving);

        findViewById(R.id.img_go_back).setOnClickListener(new
                                                                  View.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(View v) {
                                                                          finish();
                                                                          startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));

                                                                      }
                                                                  });

    }


    private boolean isValidate() {

        boolean result = true;
        String error = "Please Select following parameters";


        if (cgpa.getSelectedItemPosition() == 0) {
            error = error + "\n CGPA % Level";
            result = false;
        }

        if (aptitude_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Aptitude Skill Level";
            result = false;
        }

        if (coresub_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Core Sub skill Level";
            result = false;
        }

        if (ds_coding.getSelectedItemPosition() == 0) {
            error = error + "\n Data Structure Coding Level";
            result = false;
        }

        if (problemsolving_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Problem Solving Level";
            result = false;
        }

        if (extracurricular.getSelectedItemPosition() == 0) {
            error = error + "\n Extracurricular Level";
            result = false;
        }

        if (abstractthink_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Abstract Thinking Level";
            result = false;
        }

        if (programming_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Programming Skill Level";
            result = false;
        }

        if (design_skill.getSelectedItemPosition() == 0) {
            error = error + "\n Design Skill Level";
            result = false;
        }


        if (result == false) {
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
        }

        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), JobseekerHomeActivity.class));
    }


    public User getUserSession() {
        UserSession userSession = new UserSession(getApplicationContext());
        User user = new User();
        user.setType(userSession.getRole());
        user.setEmail(userSession.getEmail());
        user.setMobile(userSession.getMobile());
        user.setName(userSession.getName());
        user.setPassword(userSession.getPassword());
        user.setUserid(userSession.getUserId());
        user.setJob(userSession.getPredictedJob());
        return user;
    }


}