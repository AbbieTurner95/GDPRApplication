package com.example.abbieturner.gdprapplication.UI.Activity.Admin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<User> userList;
    private final Context context;
    private final UserClickListener listener;

    public EmployeeAdapter(Context context, UserClickListener listener) {
        userList = new ArrayList<>();
        this.context = context;
        this.listener = listener;
    }

    public void setUserList(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        User user = userList.get(position);

        String name = user.getName();
        holder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public EmployeeViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.user_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onEmployeeItemClick(userList.get(getAdapterPosition()));
        }
    }

    public interface UserClickListener {
        void onEmployeeItemClick(User user);
    }
}
