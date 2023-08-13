package com.example.awesomequotesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.awesomequotesapp.Entity.User;
import com.example.awesomequotesapp.R;
import com.example.awesomequotesapp.Utils.RetroClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editEmail,editPassword;
    Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnLogin)
        {

            User user = new User();
            user.setEmail(editEmail.getText().toString());
            user.setPassword(editPassword.getText().toString());

            RetroClient.getInstance().getApi().userLogin(user).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                    {
                        int id = response.body().getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject().get("user_id").getAsInt();
                        Toast.makeText(LoginActivity.this, ""+id, Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("quotes_app",MODE_PRIVATE);
                        preferences.edit().putInt("uid",id).apply();
                        Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Something went wrong with DB", Toast.LENGTH_SHORT).show();

                }
            });







        } else if (v.getId() == R.id.btnRegister) {


            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
            
        }

    }
}