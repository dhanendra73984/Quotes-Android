package com.example.awesomequotesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuoteActivity extends AppCompatActivity {


    EditText editAuthor,editText;
    Button btnAddQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quote);

        editAuthor = findViewById(R.id.editAuthorE);
        editText = findViewById(R.id.editTextE);
        btnAddQ = findViewById(R.id.btnAddQE);

        Quote quote =(Quote) getIntent().getSerializableExtra("quote");

        editAuthor.setText(quote.getAuthor());
        editText.setText(quote.getText());

        btnAddQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quote quote1 = new Quote();
                quote1.setText(editText.getText().toString());
                quote1.setAuthor(editAuthor.getText().toString());
                quote1.setQ_id(quote.getQ_id());
                RetroClient.getInstance().getApi().editQuote(quote1).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                        {
                            Toast.makeText(EditQuoteActivity.this, "EDIT DONE", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditQuoteActivity.this,MainActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });


            }
        });
    }
}