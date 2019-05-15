package com.dicoding.iqbalfirmansyah.mysubmission3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class GridMovieAdapter extends RecyclerView.Adapter<GridMovieAdapter.GridViewHolder> {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private Context context;

    public GridMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movie, parent, false);
        return new GridViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int position) {
        Glide.with(context)
                .load(mData.get(position).getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(gridViewHolder.imgPoster);

        gridViewHolder.tvTitle.setText(mData.get(position).getTitle());

        gridViewHolder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent mIntent = new Intent(context, DetailActivity.class);
                mIntent.putExtra("EXTRA_POSITION", position);
                context.startActivity(mIntent);
                Toast.makeText(context, "Film : " + mData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        gridViewHolder.btnBookmark.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Bookmark " + mData.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle;
        Button btnBookmark;

        private GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_detail);
            tvTitle = itemView.findViewById(R.id.tv_title_detail);
            btnBookmark = itemView.findViewById(R.id.btn_set_bookmark);
        }
    }
}
