package com.example.awesomequotesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awesomequotesapp.Entity.FavQuote;
import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllQuotesRecyclerViewAdapter extends RecyclerView.Adapter<AllQuotesRecyclerViewAdapter.MyViewHolder>
{
    Context context;
    List<Quote> quoteList;

    public AllQuotesRecyclerViewAdapter(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public AllQuotesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =  LayoutInflater.from(context).inflate(R.layout.list_quotes_layout,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllQuotesRecyclerViewAdapter.MyViewHolder holder, int position) {
        int id = context.getSharedPreferences("quotes_app",Context.MODE_PRIVATE).getInt("uid",0);
        int check = quoteList.get(position).getUser_id();
        holder.textQuote.setText(quoteList.get(position).getText());
        holder.textAuthor.setText("-"+quoteList.get(position).getAuthor());

        if(id == check)
        {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            TextView textQuote,textAuthor;
            ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            textAuthor = itemView.findViewById(R.id.textAuthor);
            textQuote = itemView.findViewById(R.id.textQuote);
            imageView = itemView.findViewById(R.id.imgHeart);

            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int id = context.getSharedPreferences("quotes_app",Context.MODE_PRIVATE).getInt("uid",0);
            int q_id = quoteList.get(getAdapterPosition()).getQ_id();
            int u_check = quoteList.get(getAdapterPosition()).getUser_id();
            FavQuote favQuote = new FavQuote();
            favQuote.setUser_id(id);
            favQuote.setQ_id(q_id);




            if(v.getId() == R.id.imgHeart)
            {
             if(imageView.isSelected())
             {
                 imageView.setImageResource(R.drawable.baseline_heart_broken_24);
                 Toast.makeText(context, "unliked", Toast.LENGTH_SHORT).show();
                 imageView.setSelected(false);

                RetroClient.getInstance().getApi().delquotes(q_id).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                        {
                            Toast.makeText(context, "unliked", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

             }
             else{
                 imageView.setSelected(true);


                    if(id == u_check)
                    {
                        Toast.makeText(context, "CANNOT LIKE YOUR OWN QUOTES", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        imageView.setImageResource(R.drawable.heartfill);
                        RetroClient.getInstance().getApi().addFavQuotes(favQuote).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                                {
                                    Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });


                    }


             }

            }

        }
    }
}
