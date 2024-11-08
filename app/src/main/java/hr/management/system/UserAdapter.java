package hr.management.system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.management.system.model.Job;
import hr.management.system.model.User;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ItemViewHolder> {

    private List<User> mItemList;
    private Context mcontext;

    public UserAdapter(@NonNull List<User> ItemList, Context context) {
        this.mItemList = ItemList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public UserAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.ItemViewHolder holder, final int position) {

        // Retrieve reach product from cart one by one
        final User item = mItemList.get(position);

        holder.txt_user_name.setText(item.getName());
        holder.txt_email.setText(item.getEmail());
        holder.txt_mobile.setText(item.getMobile());

    }// End of binder function

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_user_name, txt_email, txt_mobile;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_user_name = itemView.findViewById(R.id.txt_name);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_mobile = itemView.findViewById(R.id.txt_mobile);
        }

    }
}
