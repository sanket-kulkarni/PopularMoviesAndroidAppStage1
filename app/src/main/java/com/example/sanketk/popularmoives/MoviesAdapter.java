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

    public class ListHolder extends ViewHolder {
        ImageView ivMoivePoster;

        public ListHolder(View itemView) {
            super(itemView);
            this.ivMoivePoster = (ImageView) itemView.findViewById(R.id.movie_image);
        }
    }

    public MoviesAdapter(Context context, List<ResultsItem> resultsItemList) {
        this.context = context;
        this.resultsItemList = resultsItemList;
        notifyDataSetChanged();
    }

    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
    }

    public void onBindViewHolder(ListHolder holder, int position) {
        final ResultsItem resultsItem = (ResultsItem) this.resultsItemList.get(position);

        Log.d(TAG,"onBindViewHolder position "+position);
        Log.d(TAG,"onBindViewHolder resultsItem "+resultsItem);

        if (!TextUtils.isEmpty(resultsItem.getPosterPath())) {
            Glide.with(this.context).load("http://image.tmdb.org/t/p/w185/" + resultsItem.getPosterPath()).into(holder.ivMoivePoster);
        }
        final int pos=position;
        holder.ivMoivePoster.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MoviesAdapter.this.context, MoiveDetailsActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt("id", resultsItem.getId());
                bundle.putInt("pos", pos);
                bundle.putString("OriginalTitle", resultsItem.getOriginalTitle());
                bundle.putString("Overview", resultsItem.getOverview());
                bundle.putString("VoteAverage", BuildConfig.FLAVOR + resultsItem.getVoteAverage());
                bundle.putString("ReleaseDate", resultsItem.getReleaseDate());
                bundle.putString("PosterPath", resultsItem.getPosterPath());
                intent.putExtras(bundle);
                MoviesAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        if (this.resultsItemList != null) {
            return this.resultsItemList.size();
        }
        return 0;
    }
}
