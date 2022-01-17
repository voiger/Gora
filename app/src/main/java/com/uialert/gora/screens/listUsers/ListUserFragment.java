package com.uialert.gora.screens.listUsers;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uialert.gora.R;
import com.uialert.gora.screens.listPhotos.ListPhotosFragment;
import com.uialert.gora.server.ServiceNetwork;
import com.uialert.gora.server.pojo.user.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserFragment extends Fragment implements CreateFragmentPhotos {

    UsersAdapter usersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<User> users = new ArrayList<>();
        usersAdapter = new UsersAdapter(users, this);
        recyclerView.setAdapter(usersAdapter);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        ServiceNetwork
                .getInstance()
                .getAPI()
                .getAllUser()
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.body() != null) {
                            List<User> users = new ArrayList<>(response.body());
                            usersAdapter.setUserList(users);
                        } else {
                            Toast.makeText(getContext(), "Проблема с подключением", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(getContext(), "Проблема с подключением", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void CreateFragment(User user) {
        Fragment fragment = new ListPhotosFragment(user);
        getParentFragmentManager()
                .beginTransaction()
                .addToBackStack("Users")
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }
}