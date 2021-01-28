package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends  RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    Context context;
    List<Tweet> tweets;

    // Pass in context and list of tweets

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout for tweet
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the data at position
        Tweet tweet = tweets.get(position);
        // bind the tweet with view holder
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#212121"));
        }
        else {
            holder.itemView.setBackgroundColor(Color.parseColor("#121212"));
        }
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        this.tweets.clear();
    }


    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(Tweet tweet) {
           tvBody.setText(tweet.body);
           tvScreenName.setText(tweet.user.screenName);
           Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
           tvTimestamp.setText(Tweet.getFormattedTimestamp(tweet.createdAt));
        }
    }
}
