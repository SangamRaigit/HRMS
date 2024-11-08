package hr.management.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.management.system.model.Applications;


public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ItemViewHolder> {

    private List<Applications> mItemList;
    private Context mcontext;

    public ApplicationsAdapter(@NonNull List<Applications> ItemList, Context context) {
        this.mItemList = ItemList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ApplicationsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_item, parent, false);
        return new ApplicationsAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ApplicationsAdapter.ItemViewHolder holder, final int position) {

        // Retrieve reach product from cart one by one
        final Applications item = mItemList.get(position);

        holder.txt_job_tittle.setText(item.getTitle());
        holder.txt_company.setText(item.getCompany());
        holder.txt_salary.setText("Salary  "+item.getSalary()+" LPA");
        holder.txt_desc.setText(item.getDescription());
        holder.txt_user_name.setText(item.getName());
        holder.txt_email.setText(item.getEmail());
        holder.txt_mobile.setText(item.getMobile());

        holder.itemView.findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + item.getMobile()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
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
        private TextView txt_user_name, txt_email, txt_mobile;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_job_tittle = itemView.findViewById(R.id.txt_job_tittle);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_salary = itemView.findViewById(R.id.txt_salary);
            txt_desc = itemView.findViewById(R.id.txt_desc);
            txt_user_name = itemView.findViewById(R.id.txt_name);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_mobile = itemView.findViewById(R.id.txt_mobile);
        }

    }
}
