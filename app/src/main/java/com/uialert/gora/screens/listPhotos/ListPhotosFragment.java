package com.uialert.gora.screens.listPhotos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uialert.gora.R;
import com.uialert.gora.server.ServiceNetwork;
import com.uialert.gora.server.pojo.photo.Photo;
import com.uialert.gora.server.pojo.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListPhotosFragment extends Fragment {

    private final User user;
    public PhotosAdapter adapter;

    public ListPhotosFragment(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceNetwork
                .getInstance()
                .getAPI()
                .getPhotosToUserID(user.getId())
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        if (response.body() != null) {
                            adapter.setPhotoList(response.body());
                        } else {
                            Toast.makeText(getContext(), "Проблема с подключением", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {
                        Toast.makeText(getContext(), "Проблема с подключением", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_photos, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new PhotosAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }
}