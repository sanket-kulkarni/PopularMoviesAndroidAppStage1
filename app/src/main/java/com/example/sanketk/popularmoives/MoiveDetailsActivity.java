package com.example.sanketk.popularmoives;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
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
        this.tvOverview.setMovementMethod(new ScrollingMovementMethod());
        this.tvVoteAverage = (TextView) findViewById(R.id.tvVoteAverage);
        this.tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        if(getIntent().getParcelableExtra("resultsItem")!=null) {
            ResultsItem resultsItem=getIntent().getParcelableExtra("resultsItem");
            //Bundle bundle=getIntent().getExtras();
            Log.d(TAG,"onBindViewHolder resultsItem "+resultsItem);
            String strOriginalTitle=resultsItem.getOriginalTitle();
            String strOverview=resultsItem.getOverview();
            String strReleaseDate=resultsItem.getReleaseDate();
            String strVoterAvg=resultsItem.getVoteAverage()+ "/10";
            String strPosterPath=resultsItem.getPosterPath();
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
