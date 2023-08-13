package com.example.awesomequotesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.Fragments.MyQuotesFragment;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends AppCompatActivity {

    EditText editAuthor,editText;
    Button btnAddQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        editAuthor = findViewById(R.id.editAuthor);
        editText = findViewById(R.id.editText);
        btnAddQ = findViewById(R.id.btnAddQ);


        btnAddQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().equals("") && editAuthor.getText().toString().equals("")) {
                    Toast.makeText(AddQuoteActivity.this, " FIELDS CANNOT BE BLANK", Toast.LENGTH_SHORT).show();
                } else {

                    Quote quote = new Quote();
                    quote.setText(editText.getText().toString());
                    quote.setAuthor(editAuthor.getText().toString());

                    int id = getSharedPreferences("quotes_app", MODE_PRIVATE).getInt("uid", 0);

                    RetroClient.getInstance().getApi().addQuotes(id, quote).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                            if (response.body().getAsJsonObject().get("status").getAsString().equals("success")) {
                                Toast.makeText(AddQuoteActivity.this, "ADDED QUOTE", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddQuoteActivity.this, MainActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });


                }
            }
        });

    }
}