package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movie.adapter.MovieListAdapter;
import com.example.movie.model.Movie;
import com.example.movie.network.ApiService;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovie extends AppCompatActivity {
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;
    private int page = 1;
    private GridLayoutManager gridLayoutManager;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie);
        movieListAdapter = new MovieListAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_movies);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieListAdapter);

        loadData();
    }

    private void loadData() {
        apiService = new ApiService();
        apiService.getPopularMovies(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("Message", "Response"+response.body().toString());
                Movie movie = (Movie) response.body();
                if(movie != null){
                    if(movieListAdapter != null){
                        movieListAdapter.addAll(movie.getResult());
                    }
                }else {
                    Toast.makeText(getBaseContext(),"No Data!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException){
                    Toast.makeText(getBaseContext(),"Request Timeout.", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(),"Connection Error!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
