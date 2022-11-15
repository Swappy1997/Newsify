package com.example.newsify.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.newsify.adapter.NewssAdapter;
import com.example.newsify.api.ApiManger;
import com.example.newsify.api.CardListner;
import com.example.newsify.api.DataListner;
import com.example.newsify.databinding.ActivityMainBinding;
import com.example.newsify.models.NewsResponce;
import com.example.newsify.models.NewsSource;

import java.util.List;


public class MainActivity extends AppCompatActivity implements CardListner {
    NewssAdapter adapter;
    ProgressDialog dialog;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        binding.seachbiew.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Getting news article of " + query);
                dialog.show();
                ApiManger apiManger = new ApiManger(MainActivity.this);
                apiManger.getNewsSource(listner, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        dialog.setTitle("Loading..");
        dialog.show();
        ApiManger apiManger = new ApiManger(this);
        apiManger.getNewsSource(listner, "global");
        binding.dotsicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDilogbox();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private final DataListner<NewsResponce> listner = new DataListner<NewsResponce>() {
        @Override
        public void onDataFetch(List<NewsSource> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            } else {
                showList(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
        }
    };

    private void showList(List<NewsSource> list) {
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewssAdapter(this, list, this);
        binding.list.setAdapter(adapter);
    }

    private void setDilogbox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to logout? ")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginSet(false);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loginSet(Boolean b) {
        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("flag", b);
        editor.apply();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }


    @Override
    public void onCardClick(NewsSource newsSource) {
        startActivity(new Intent(MainActivity.this, NewsDetail.class)
                .putExtra("newsdata", newsSource));

    }
}