package com.example.newsify.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.newsify.R;
import com.example.newsify.databinding.ActivityMainBinding;
import com.example.newsify.databinding.ActivityNewsDetailBinding;
import com.example.newsify.models.NewsSource;
import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    NewsSource newsSource;
    ActivityNewsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newsSource = (NewsSource) getIntent().getSerializableExtra("newsdata");
        Log.d("TAG", "onCreate: "+newsSource.getTitle().toString());

        binding.detailTittle.setText(newsSource.getTitle());
        binding.detailAuthor.setText(newsSource.getAuthor());
        binding.detailDetail.setText(newsSource.getDescription());
        binding.detailDetailContent.setText(newsSource.getContent());
        binding.detailTime.setText(newsSource.getPublishedAt());
        Picasso.get().load(newsSource.getUrlToImage()).into(binding.detailIv);


    }
}