package com.b2.raj.b2newsfeed.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.b2.raj.b2newsfeed.R;
import com.b2.raj.b2newsfeed.data.FeedItems;
import com.b2.raj.b2newsfeed.data.NewsFeedModel;
import com.b2.raj.b2newsfeed.network.LazyImageDownloadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by raj on 3/1/2017.
 */

public class B2FeedListAdapter extends BaseAdapter {

    private ArrayList<FeedItems> feedRows;
    private LayoutInflater inflater;

    //private DisplayImageOptions options;

    //private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public B2FeedListAdapter(Context context) {
        feedRows = new ArrayList<FeedItems>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      /*  options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();*/
    }

    @Override
    public int getCount() {
        return feedRows.size();
    }

    @Override
    public int getItemViewType(int position) {

        return (position == 0) ? 0: 1;
    }

    @Override
    public int getViewTypeCount () {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return feedRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = this.getItemViewType(position);
        Log.d("TEST FEED", "type="+type +" position=" +position );
        if (type == 0) {
            Log.d("FEED", "Position<=0=" +position);
            View feedRowView = convertView;
            ViewHolderTop holder1;
            if (convertView == null) {
                feedRowView = inflater.inflate(R.layout.top_item_row, null);
                holder1 = new ViewHolderTop();
                holder1.title = (TextView) feedRowView.findViewById(R.id.view_feed_list_row_title);
                holder1.pubDate = (TextView) feedRowView.findViewById(R.id.view_feed_list_row_date);
                holder1.image = (ImageView) feedRowView.findViewById(R.id.view_feed_list_row_image);
                feedRowView.setTag(holder1);
            } else {
                holder1 = (ViewHolderTop) feedRowView.getTag();
            }
            FeedItems feedRow = (FeedItems) getItem(position);
            holder1.title.setText(feedRow.getTitle());
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
                Date inputDate = inputFormat.parse(feedRow.getPubDate());
                Log.d("Date=" + position, "date=" + outputFormat.format(inputDate));
                holder1.pubDate.setText(outputFormat.format(inputDate));
            } catch (Exception e) {
                Log.d("DateFormatException", e.toString());
            }
            if(holder1.image != null) {

                new LazyImageDownloadTask(holder1.image).execute(feedRow.getEnclosure().getLink());
            }
            //ImageLoader.getInstance().displayImage(feedRow.getThumbnail(), holder.image, options, animateFirstListener);
            return feedRowView;
        } else {
            View feedRowView = convertView;
            ViewHolderNormal holder2;
            Log.d("FEED", "Position>0=" +position);
            if (convertView == null) {
                Log.d("FEED Null", "Position>0=" +position);
                feedRowView = inflater.inflate(R.layout.news_list_row, null);
                holder2 = new ViewHolderNormal();
                holder2.title = (TextView) feedRowView.findViewById(R.id.view_feed_list_row_title);
                holder2.pubDate = (TextView) feedRowView.findViewById(R.id.view_feed_list_row_date);
                holder2.image = (ImageView) feedRowView.findViewById(R.id.view_feed_list_row_image);
                feedRowView.setTag(holder2);
            } else {
                holder2 = (ViewHolderNormal) feedRowView.getTag();
            }
            FeedItems feedRow = (FeedItems) getItem(position);
            holder2.title.setText(feedRow.getTitle());
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
                Date inputDate = inputFormat.parse(feedRow.getPubDate());
                Log.d("Date=" + position, "date=" + outputFormat.format(inputDate));
                holder2.pubDate.setText(outputFormat.format(inputDate));
            } catch (Exception e) {
                Log.d("DateFormatException", e.toString());
            }
            if(holder2.image != null) {
                new LazyImageDownloadTask(holder2.image).execute(feedRow.getThumbnail());
            }

            //ImageLoader.getInstance().displayImage(feedRow.getThumbnail(), holder.image, options, animateFirstListener);

            return feedRowView;

        }
    }

    public static class ViewHolderTop {
        public TextView title;
        public TextView pubDate;
        public ImageView image;
    }

    public static class ViewHolderNormal {
        public TextView title;
        public TextView pubDate;
        public ImageView image;
    }

   /* static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }*/

    public void updateAdapter() {
        Log.d("B2FeedListAdapter", "FeedRows");
        feedRows = NewsFeedModel.getInstance().getItems();
        Log.d("B2FeedListAdapter", " "+feedRows.size());
        //notifyDataSetChanged();
    }
}

