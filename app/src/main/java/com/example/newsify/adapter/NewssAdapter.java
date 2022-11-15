package com.example.newsify.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsify.R;
import com.example.newsify.api.CardListner;
import com.example.newsify.api.DataListner;
import com.example.newsify.models.NewsSource;
import com.squareup.picasso.Picasso;

import java.util.List;


public class NewssAdapter extends RecyclerView.Adapter<NewssAdapter.NewsViewHolder> {

    public NewssAdapter(Context context, List<NewsSource> responce, CardListner dataListner) {
        this.context = context;
        this.responce = responce;
        this.dataListner = dataListner;
    }

    private Context context;
    List<NewsSource> responce;
    private CardListner dataListner;

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newscardview, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tittle.setText(responce.get(position).getTitle());
        holder.source.setText(responce.get(position).getSource().getName());
        Picasso.get().load(responce.get(position).getUrlToImage()).into(holder.headlineiv);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataListner.onCardClick(responce.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {

        return responce.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView headlineiv;
        TextView tittle, source;
        CardView cardView;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.tittle);
            source = itemView.findViewById(R.id.source);
            headlineiv = itemView.findViewById(R.id.imgheadline);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }


}
