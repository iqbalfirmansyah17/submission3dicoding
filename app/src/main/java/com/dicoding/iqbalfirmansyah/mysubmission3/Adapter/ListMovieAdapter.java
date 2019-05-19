package com.dicoding.iqbalfirmansyah.mysubmission3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.iqbalfirmansyah.mysubmission3.Activity.DetailActivity;
import com.dicoding.iqbalfirmansyah.mysubmission3.Listener.CustomOnItemClickListener;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder> {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private Context context;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movies, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.tvTitle.setText(mData.get(position).getTitle());
        movieViewHolder.tvOverview.setText(mData.get(position).getOverview());
        Glide.with(context)
                .load(mData.get(position).getPoster())
                .apply(new RequestOptions().override(200, 200))
                .into(movieViewHolder.poster);

        movieViewHolder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent mIntent = new Intent(context, DetailActivity.class);
                mIntent.putExtra("EXTRA_POSITION", position);
                context.startActivity(mIntent);
                Toast.makeText(context, "Film : " + mData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView poster;

        private MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            poster = itemView.findViewById(R.id.img_poster);
        }
    }
}
