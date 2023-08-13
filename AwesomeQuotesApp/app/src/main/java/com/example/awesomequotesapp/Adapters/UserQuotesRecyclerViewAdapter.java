package com.example.awesomequotesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awesomequotesapp.Activity.EditQuoteActivity;
import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserQuotesRecyclerViewAdapter extends RecyclerView.Adapter<UserQuotesRecyclerViewAdapter.MyViewHolder> {
    public interface IMyInterface{

        public void refresh(int id);
    }
    Context context;
    List<Quote> quoteList;
    IMyInterface listener;

    public UserQuotesRecyclerViewAdapter(Context context, List<Quote> quoteList, IMyInterface listener) {
        this.context = context;
        this.quoteList = quoteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserQuotesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_quotes_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserQuotesRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.textQuote.setText(quoteList.get(position).getText());
        holder.textAuthor.setText("-"+quoteList.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textQuote, textAuthor;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textAuthor = itemView.findViewById(R.id.textAuthor);
            textQuote = itemView.findViewById(R.id.textQuote);
            imageView = itemView.findViewById(R.id.imgHeart);
            imageView.setVisibility(itemView.INVISIBLE);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popupMenu = new PopupMenu(context,v);
                    
                    popupMenu.getMenu().add("EDIT");
                    popupMenu.getMenu().add("DELETE");
                    popupMenu.show();
                    
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            
                            if(item.getTitle().equals("EDIT"))
                            {
                                Toast.makeText(context, "EDIT", Toast.LENGTH_SHORT).show();
                                Quote quote = quoteList.get(getAdapterPosition());
                                Intent intent = new Intent(context, EditQuoteActivity.class);
                                intent.putExtra("quote",quote);
                                context.startActivity(intent);




                            } else if (item.getTitle().equals("DELETE")) {

                                int q = quoteList.get(getAdapterPosition()).getQ_id();

                                RetroClient.getInstance().getApi().delq(q).enqueue(new Callback<JsonObject>() {
                                    @Override
                                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                        if (response.body().getAsJsonObject().get("status").getAsString().equals("success")) {

                                            listener.refresh(getAdapterPosition());
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<JsonObject> call, Throwable t) {

                                    }
                                });


                            }
                            return false;
                        }
                    });
                    
                    
                }
            });
        }
    }
}
