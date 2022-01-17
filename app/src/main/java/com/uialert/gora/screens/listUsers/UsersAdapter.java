package com.uialert.gora.screens.listUsers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uialert.gora.R;
import com.uialert.gora.server.pojo.user.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> userList;
    private CreateFragmentPhotos createFragmentPhotos;

    public UsersAdapter(List<User> users, CreateFragmentPhotos createFragmentPhotos) {
        this.userList = users;
        this.createFragmentPhotos = createFragmentPhotos;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameUser.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameUser;

        public ViewHolder(View view) {
            super(view);
            nameUser = view.findViewById(R.id.item_user_nameUser);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createFragmentPhotos.CreateFragment(userList.get(getAbsoluteAdapterPosition()));
                }
            });
        }

    }
}