package com.example.awesomequotesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.R;

import java.util.List;


public class MyFavQuotesRecyclerViewAdapter extends RecyclerView.Adapter<MyFavQuotesRecyclerViewAdapter.MyViewHolder>
{
    Context context;
    List<Quote> quoteList;


    public MyFavQuotesRecyclerViewAdapter(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public MyFavQuotesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.list_quotes_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavQuotesRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.textQuote.setText(quoteList.get(position).getText());
        holder.textAuthor.setText("-"+quoteList.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textQuote,textAuthor;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textAuthor = itemView.findViewById(R.id.textAuthor);
            textQuote = itemView.findViewById(R.id.textQuote);
            imageView = itemView.findViewById(R.id.imgHeart);
            imageView.setImageResource(R.drawable.heartfill);

        }
    }
}
