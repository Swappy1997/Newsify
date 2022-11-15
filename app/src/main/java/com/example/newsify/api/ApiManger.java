package com.example.newsify.api;

import android.content.Context;
import android.widget.Toast;

import com.example.newsify.models.NewsResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManger {

    public ApiManger(Context mcontext) {
        this.mcontext = mcontext;
    }

    Context mcontext;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface NewsApi {
        @GET("everything")
        Call<NewsResponce> newsresponce(
                @Query("q") String query,
                @Query("apikey") String api_key
        );
    }

    public void getNewsSource(DataListner listner,String query) {
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<NewsResponce> call = newsApi.newsresponce(query, "af1d86fffb1b48f1b35c1621ccca8108");
        try {
            call.enqueue(new Callback<NewsResponce>() {
                @Override
                public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(mcontext, "Error", Toast.LENGTH_SHORT).show();
                    }
                    listner.onDataFetch(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsResponce> call, Throwable t) {
                    listner.onError("failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
