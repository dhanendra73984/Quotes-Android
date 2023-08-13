package com.example.awesomequotesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.awesomequotesapp.Entity.User;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {


    Toolbar toolbar;
    Button btnEdit;
    TextView textFname,textLname,textEmail,textMobile;

    User user;
    User user1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnEdit = findViewById(R.id.btnEditPro);
        toolbar = findViewById(R.id.toolbarP);
        setSupportActionBar(toolbar);

        textFname = findViewById(R.id.textFname);
        textLname = findViewById(R.id.textLname);
        textEmail = findViewById(R.id.textEmail);
        textMobile = findViewById(R.id.textMobile);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity2.class);
                intent.putExtra("user1",user1);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();


        int id = getSharedPreferences("quotes_app",MODE_PRIVATE).getInt("uid",0);

        RetroClient.getInstance().getApi().getuserByuserId(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                    {


                        JsonArray array = response.body().getAsJsonObject().get("data").getAsJsonArray();


                        for(JsonElement element : array)
                        {

                            user = new User();

                            user.setUser_id(element.getAsJsonObject().get("user_id").getAsInt());
                            user.setFirst_name(element.getAsJsonObject().get("first_name").getAsString());
                            user.setLast_name(element.getAsJsonObject().get("last_name").getAsString());
                            user.setEmail(element.getAsJsonObject().get("email").getAsString());
                            user.setPassword(element.getAsJsonObject().get("password").getAsString());
                            user.setMobile(element.getAsJsonObject().get("mobile").getAsString());

                            user1 = user;
                            textFname.setText("FIRST NAME : "+user.getFirst_name());
                            textLname.setText("LAST NAME : "+user.getLast_name());
                            textEmail.setText("EMAIL : "+user.getEmail());
                            textMobile.setText("MOBILE NO: "+user.getMobile());



                        }


                    }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}