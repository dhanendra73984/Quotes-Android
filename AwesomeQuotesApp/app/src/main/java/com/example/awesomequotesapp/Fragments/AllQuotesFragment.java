package com.example.awesomequotesapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.awesomequotesapp.Adapters.AllQuotesRecyclerViewAdapter;
import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllQuotesFragment extends Fragment {

    RecyclerView recyclerView;
    List<Quote> quoteList;
    AllQuotesRecyclerViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_quotes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quoteList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewAll);
         adapter = new AllQuotesRecyclerViewAdapter(getContext(),quoteList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));


        getAllQuotes();

    }

    private void getAllQuotes()
    {
        quoteList.clear();
        RetroClient.getInstance().getApi().getAllQuote().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                {
                    JsonArray array = response.body().getAsJsonObject().get("data").getAsJsonArray();

                    for(JsonElement element: array)
                    {

                        Quote quote = new Quote();
                        quote.setQ_id(element.getAsJsonObject().get("q_id").getAsInt());
                        quote.setAuthor(element.getAsJsonObject().get("author").getAsString());
                        quote.setUser_id(element.getAsJsonObject().get("user_id").getAsInt());
                        quote.setText(element.getAsJsonObject().get("text").getAsString());

                        quoteList.add(quote);



                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}