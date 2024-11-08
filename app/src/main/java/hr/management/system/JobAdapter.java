package hr.management.system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.management.system.api.ApiClient;
import hr.management.system.api.ApiInterface;
import hr.management.system.model.Applications;
import hr.management.system.model.Job;
import hr.management.system.model.ServerResponse;
import hr.management.system.sessionManagement.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ItemViewHolder> {

    private List<Job> mItemList;
    private Context mcontext;

    public JobAdapter(@NonNull List<Job> ItemList, Context context) {
        this.mItemList = ItemList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public JobAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        return new JobAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final JobAdapter.ItemViewHolder holder, final int position) {

        // Retrieve reach product from cart one by one
        final Job item = mItemList.get(position);

        holder.txt_job_tittle.setText(item.getTitle());
        holder.txt_company.setText(item.getCompany());
        holder.txt_salary.setText("Salary "+item.getSalary()+" LPA");
        holder.txt_desc.setText(item.getDescription());

        holder.itemView.findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Please wait applying for job");

                UserSession userSession = new UserSession(mcontext);
                Applications applications = new Applications();
                applications.setJobId(String.valueOf(item.getId()));
                applications.setAlumniId(item.getAlumniId());
                applications.setStudentId(String.valueOf(new UserSession(mcontext).getUserId()));
                applications.setCompany(item.getCompany());
                applications.setDescription(item.getDescription());
                applications.setEmail(userSession.getEmail());
                applications.setMobile(userSession.getMobile());
                applications.setName(userSession.getName());
                applications.setSalary(item.getSalary());
                applications.setTitle(item.getTitle());

                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<ServerResponse> serverResponseCall = apiInterface.applyJob(applications);
                serverResponseCall.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (call.isExecuted()) {
                            showToast("Your Application Has Submitted Successfully");
                        } else {
                            showToast("Error Occur pls try again");
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        showToast("Error Occur pls try again " + t.toString());
                    }
                });
            }
        });


    }// End of binder function


    public void showToast(String test) {
        Toast.makeText(mcontext, test, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_job_tittle, txt_company, txt_salary, txt_desc;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_job_tittle = itemView.findViewById(R.id.txt_job_tittle);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_salary = itemView.findViewById(R.id.txt_salary);
            txt_desc = itemView.findViewById(R.id.txt_desc);
        }

    }
}
