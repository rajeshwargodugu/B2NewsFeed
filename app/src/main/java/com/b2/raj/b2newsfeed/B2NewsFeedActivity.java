package com.b2.raj.b2newsfeed;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.b2.raj.b2newsfeed.base.FeedConfig;
import com.b2.raj.b2newsfeed.data.NewsFeedModel;
import com.b2.raj.b2newsfeed.network.FeedDownloadHelper;
import com.b2.raj.b2newsfeed.util.B2FeedListAdapter;

public class B2NewsFeedActivity extends AppCompatActivity {

    private static final String TAG = B2NewsFeedActivity.class.getSimpleName();

    private ProgressBar mProgressBar;

    // Due to time constraint going with listview view holder pattern.
    // Needs to implement with Recyclerview for large data/news feed.
    private ListView mFeedListView;
    private B2FeedListAdapter mFeedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_news_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        mFeedListView = (ListView) findViewById(R.id.newsList);
        mFeedListView.setVisibility(View.GONE);

        mFeedListAdapter = new B2FeedListAdapter(getApplicationContext());
        mFeedListView.setAdapter(mFeedListAdapter);
        if (isNetworkAvailable() ) {
            downloadFeedsAndUpdateUI();
        } else {
            showWarning("Warning", "There is no network connectivity. Please close the application and " +
                    "try later for news.");
        }
    }

    /*
     * checks the network status availability
     */
    private boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) (getApplicationContext()).getSystemService(
                                    Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (null != ni && ni.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Downloads main feed data
     */
    private void downloadFeedsAndUpdateUI() {
        DownloadFeedsAndUpdateUITask downloadJsonFeedTask = new DownloadFeedsAndUpdateUITask();
        downloadJsonFeedTask.execute(FeedConfig.FEED_URL);
    }

    /*
     * Warning message dialog
     */
    private void showWarning(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        exitApp();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
     * Exiting the application as no network connectvity ideally refresh should be
     * more meaningful but as test app exiting
     */
    private void exitApp()
    {
        this.finishAffinity();
    }

    private class DownloadFeedsAndUpdateUITask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... url) {

            String jsonData = null;
            try {
                jsonData = FeedDownloadHelper.downloadUrl(url[0]);
                NewsFeedModel.getInstance().updateModel(jsonData);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            Log.d(TAG, "Result=" +jsonData);
            return jsonData;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Result=" +result);
            mProgressBar.setVisibility(View.GONE);
            mFeedListView.setVisibility(View.VISIBLE);
            mFeedListAdapter.updateAdapter();
            //Log.d(TAG, "feedlistAdapter After Update get count=" +mFeedListAdapter.getCount());

            //Log.d(TAG, "feedlistAdapter222 After Update get count=" +mFeedListAdapter.getCount());
        }
    }

}

