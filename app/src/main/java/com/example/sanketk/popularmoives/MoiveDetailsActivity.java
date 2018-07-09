package com.example.sanketk.popularmoives;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class MoiveDetailsActivity extends AppCompatActivity {
    private static final String TAG = MoiveDetailsActivity.class.getSimpleName();
    ImageView ivMoviePoster;
    TextView tvMoiveTitle;
    TextView tvOverview;
    TextView tvReleaseDate;
    TextView tvVoteAverage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moive_details);

        this.tvMoiveTitle = (TextView) findViewById(R.id.tvMoiveTitle);
        this.ivMoviePoster = (ImageView) findViewById(R.id.ivMoviePoster);
        this.tvOverview = (TextView) findViewById(R.id.tvOverview);
        this.tvVoteAverage = (TextView) findViewById(R.id.tvVoteAverage);
        this.tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        if(getIntent().getExtras()!=null) {
            Bundle bundle=getIntent().getExtras();
            Log.d(TAG,"onBindViewHolder pos "+getIntent().getIntExtra("pos",0));
            String strOriginalTitle=bundle.getString("OriginalTitle");
            String strOverview=bundle.getString("Overview");
            String strReleaseDate=bundle.getString("ReleaseDate");
            String strVoterAvg=bundle.getString("VoteAverage")+ "/10";
            String strPosterPath=bundle.getString("PosterPath");
            this.tvMoiveTitle.setText(strOriginalTitle);
            this.tvOverview.setText(strOverview);
            this.tvVoteAverage.setText(strVoterAvg);
            this.tvReleaseDate.setText(strReleaseDate);
            if (!TextUtils.isEmpty(strPosterPath)) {
                Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + strPosterPath).into(this.ivMoviePoster);
            }
        }
    }
}
