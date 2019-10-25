package com.example.nyt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to ArticleViewHolder (our custom ViewHolder)
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    // class variable that holds the data that we want to adapt
    private ArrayList<Article> articlesToAdapt;


    public void setData(ArrayList<Article> articlesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.articlesToAdapt = articlesToAdapt;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.



        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.article, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        // Compare what we have in this method, to what we used to have in MainActivity

        // 'position' is the index of the ViewHolder currently being bound (note VIEWHOLDER). So
        // if position is 0, then we are binding the first ViewHolder in the list. This means the
        // corresponding data object will be at index 0 of our data ArrayList.
        final Article articleAtPosition = articlesToAdapt.get(position);


        holder.headlineTextView.setText(articleAtPosition.getHeadline());
        holder.summaryTextView.setText(articleAtPosition.getSummary());
        holder.authorTextView.setText(articleAtPosition.getAuthor());



        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra("ArticleID", articleAtPosition.getArticleID());
                context.startActivity(intent);
            }
        });

        holder.shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_TEXT, articleAtPosition.getHeadline());
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        });

        holder.itemImageView.setImageResource(articleAtPosition.getImageDrawableId());
    }

    @Override
    public int getItemCount() {
        return articlesToAdapt.size();
    }

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView headlineTextView;
        public TextView summaryTextView;
        public TextView authorTextView;
        public ImageView shareImageView;
        public ImageView itemImageView;

        // This constructor is used in onCreateViewHolder
        public ArticleViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            headlineTextView = v.findViewById(R.id.newsHeadline);
            summaryTextView = v.findViewById(R.id.newsDetails);
            authorTextView = v.findViewById(R.id.newsAuthor);
            shareImageView = v.findViewById(R.id.newsShareButton);
            itemImageView = v.findViewById(R.id.newsPhoto);
        }
    }
}
