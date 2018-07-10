package com.example.sanketk.popularmoives;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityFragment extends Fragment implements MoviesAdapter.OnItemClickListener {
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private int listType = R.string.most_popular;
    private Dialog mDialog;
    private MoviesAdapter moviesArrayAdapter;
    private List<ResultsItem> resultsItemList;
    RecyclerView rvMoivesList;
    int numColumns=2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        this.rvMoivesList = (RecyclerView) rootView.findViewById(R.id.rvMoivesList);
        initialiseRecyclerView();
        webserviceGetInfo();
        return rootView;
    }

    private void webserviceGetInfo() {
        if (isNetworkAvailble(getActivity())) {
            this.mDialog = showProgressDialog(getActivity());
            API api = (API) RetrofitClient.getClient().create(API.class);
            Call<ApiResponse> call = null;
            if (this.listType == R.string.most_popular) {
                call = api.getPopularMovies(getResources().getString(R.string.api_key));
            } else if (this.listType == R.string.top_rated) {
                call = api.getTopRatedMovies(getResources().getString(R.string.api_key));
            }
            call.enqueue(new Callback<ApiResponse>() {
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "response.isSuccessful() " + response.isSuccessful());
                        if (response.body() == null) {
                            MainActivityFragment.this.mDialog.dismiss();
                            return;
                        } else if (((ApiResponse) response.body()).getResults() == null) {
                            MainActivityFragment.this.mDialog.dismiss();
                            return;
                        } else {
                            MainActivityFragment.this.mDialog.dismiss();
                            Log.d(TAG, " response.body().getResults().size()   " + ((ApiResponse) response.body()).getResults().size());
                            MainActivityFragment.this.resultsItemList = ((ApiResponse) response.body()).getResults();
                            MainActivityFragment.this.moviesArrayAdapter.resultsItemList = MainActivityFragment.this.resultsItemList;
                            MainActivityFragment.this.moviesArrayAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                    MainActivityFragment.this.mDialog.dismiss();
                    Log.d(TAG, " response.isSuccessful()2  " + response.isSuccessful());
                }

                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    MainActivityFragment.this.mDialog.dismiss();
                    MainActivityFragment.showOkDialog(MainActivityFragment.this.getActivity(), t.getMessage());
                }
            });
        }
        else
        {
            MainActivityFragment.showOkDialog(MainActivityFragment.this.getActivity(), "No Internet Connection");
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "Fragment.onCreateOptionsMenu");
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected id " + id);
        Log.d(TAG, "onOptionsItemSelected R.id.action_most_popular "+R.id.action_most_popular);
        Log.d(TAG, "onOptionsItemSelected R.id.action_top_rated "+R.id.action_top_rated);
        if (id == R.id.action_most_popular) {
            this.listType = R.string.most_popular;
            this.resultsItemList.clear();
            webserviceGetInfo();
            return true;
        } else if (id != R.id.action_top_rated) {
            return super.onOptionsItemSelected(item);
        } else {
            this.listType = R.string.top_rated;
            this.resultsItemList.clear();
            webserviceGetInfo();
            return true;
        }
    }

    public static boolean isNetworkAvailble(Context ctx) {
        NetworkInfo activeNetwork = ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static Dialog showProgressDialog(Context context) {
        if (context == null) {
            return null;
        }
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.vw_custom_progress_bar);
        Glide.with(context).load(Integer.valueOf(R.drawable.loader)).asGif().into((ImageView) dialog.findViewById(R.id.wvLoad));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

    public static void showOkDialog(Context context, String message) {
        if (context != null) {
            Builder build = new Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok, null);
            build.setView(view);
            final AlertDialog okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("OK");
            btnYes.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                }
            });
            tvTitle.setText(R.string.app_name);
            if (TextUtils.isEmpty(message)) {
                tvMessage.setText(BuildConfig.FLAVOR);
            } else {
                tvMessage.setText(message);
            }
            okCancelDialog.show();
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }

    private void initialiseRecyclerView() {
        if (this.resultsItemList == null) {
            this.resultsItemList = new ArrayList();
        }
        this.rvMoivesList.setLayoutManager(new GridLayoutManager(getActivity(), numColumns));
        this.moviesArrayAdapter = new MoviesAdapter(getActivity(), this.resultsItemList, this);
        this.rvMoivesList.setAdapter(this.moviesArrayAdapter);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        numColumns=(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2);
        Log.d(TAG,"onConfigurationChanged numColumns "+numColumns);
        super.onConfigurationChanged(newConfig);
        initialiseRecyclerView();
    }

    @Override
    public void onItemClick(ResultsItem resultsItem) {
        Intent intent = new Intent(getActivity(), MoiveDetailsActivity.class);
        intent.putExtra("resultsItem", resultsItem);
//        Bundle bundle= new Bundle();
//        bundle.putInt("id", resultsItem.getId());
//        bundle.putString("OriginalTitle", resultsItem.getOriginalTitle());
//        bundle.putString("Overview", resultsItem.getOverview());
//        bundle.putString("VoteAverage", BuildConfig.FLAVOR + resultsItem.getVoteAverage());
//        bundle.putString("ReleaseDate", resultsItem.getReleaseDate());
//        bundle.putString("PosterPath", resultsItem.getPosterPath());
//        intent.putExtras(bundle);
        startActivity(intent);
    }
}
