package com.example.sanketk.popularmoives;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;

public class MoviesAdapter extends Adapter<MoviesAdapter.ListHolder> {
    private static final String TAG = MoviesAdapter.class.getSimpleName();
    Context context;
    List<ResultsItem> resultsItemList;
    private final OnItemClickListener listener;

    public class ListHolder extends ViewHolder {
        ImageView ivMoivePoster;

        public ListHolder(View itemView) {
            super(itemView);
            this.ivMoivePoster = (ImageView) itemView.findViewById(R.id.movie_image);
        }
        public void bind(final ResultsItem item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public MoviesAdapter(Context context, List<ResultsItem> resultsItemList, OnItemClickListener listener) {
        this.context = context;
        this.resultsItemList = resultsItemList;
        this.listener = listener;
        notifyDataSetChanged();
    }

    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
    }

    public void onBindViewHolder(ListHolder holder, int position) {
        final ResultsItem resultsItem = (ResultsItem) this.resultsItemList.get(position);
        holder.bind(resultsItem, listener);
        Log.d(TAG,"onBindViewHolder position "+position);
        Log.d(TAG,"onBindViewHolder resultsItem "+resultsItem);

        if (!TextUtils.isEmpty(resultsItem.getPosterPath())) {
            Glide.with(this.context).load("http://image.tmdb.org/t/p/w185/" + resultsItem.getPosterPath()).into(holder.ivMoivePoster);
        }

    }

    public int getItemCount() {
        if (this.resultsItemList != null) {
            return this.resultsItemList.size();
        }
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(ResultsItem item);
    }
}
