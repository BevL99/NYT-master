package com.example.nyt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;


public class ArticleRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;

    public ArticleRecyclerFragment() {
        // Required empty public constructor
    }

    // Here the View object representing the Fragment is "inflated" from the layout file. You can
    // think of inflating as just transforming the XML to an actual View object that can be
    // displayed on screen. XML is just text. We need to inflate it to become an actual thing.
    //
    // If you want to do your own thing to the View (e.g. setText on specific TextViews, set up
    // RecyclerView, you can do it here. Alternatively, you may override onViewCreated and do the
    // set up there.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_recycler, container, false);

        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArticleAdapter articleAdapter = new ArticleAdapter();

//        articleAdapter.setData(FakeDatabase.getAllArticles());
        Gson gson = new Gson();
        MostViewedStories mostViewedStories = gson.fromJson(FakeAPI.getMostViewedStoriesJsonString(), MostViewedStories.class);

        ArrayList<Article> articleList = new ArrayList<Article>(mostViewedStories.results);
        articleAdapter.setData(articleList);
        recyclerView.setAdapter(articleAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
//        parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");
    }
}
