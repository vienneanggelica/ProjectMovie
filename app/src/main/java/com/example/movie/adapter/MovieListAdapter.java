package com.example.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.MovieData;
import com.example.movie.network.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{
    private List<MovieData> movieData;
    private Context context;

    private OnMovieItemSelectedListener onMovieItemSelectedListener;

    public MovieListAdapter (Context context){
        this.context = context;
        movieData = new ArrayList<>();
    }
    private void add(MovieData item){
        movieData.add(item);
        notifyItemInserted(movieData.size());
    }

    public void addAll(List<MovieData> movieDatas){
        for (MovieData movieData : movieDatas) {
            add(movieData);
        }
    }

    public void remove(MovieData item){
        int position = movieData.indexOf(item);
        if(position>-1){
            movieData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    private MovieData getItem(int i) {
        return movieData.get(i);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movie, viewGroup, false);
        final  MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = movieViewHolder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    if(onMovieItemSelectedListener != null) {
                        onMovieItemSelectedListener.onSelected(movieViewHolder.itemView, adapterPos);
                    }
                }
            }
        });
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieData movieData1 = movieData.get(position);
        holder.bind(movieData1);
    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.img_data);
        }

        public void bind(MovieData movieData1) {
            Picasso.get().load(Constant.IMG_URL + movieData1.getPosterPath()).into(img);
        }
    }

    private interface OnMovieItemSelectedListener {
        void onSelected(View itemView, int adapterPos);
    }
}
