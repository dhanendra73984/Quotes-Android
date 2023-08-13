package com.example.awesomequotesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    EditText editfirstName,editlastName,editPassword,editConfrimPassword,editEmail,editMobile;

    Button btnSave,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editfirstName = findViewById(R.id.editfirstName);
        editlastName = findViewById(R.id.editlastName);
        editPassword = findViewById(R.id.editPassword);
        editConfrimPassword = findViewById(R.id.editPassword1);
        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = validateUser();
                if(user != null)

                {
                    RetroClient.getInstance().getApi().userRegister(user).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                            {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
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

    private User validateUser()
    {
        if(editfirstName.getText().toString().equals(""))
        {
            Toast.makeText(this, "First Name Cannot be Empty", Toast.LENGTH_SHORT).show();
            return null;
        }


        if(editlastName.getText().toString().equals(""))
        {
            Toast.makeText(this, "Last Name Cannot be Empty", Toast.LENGTH_SHORT).show();
            return null;
        }


        if(editEmail.getText().toString().equals(""))
        {
            Toast.makeText(this, "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(!editPassword.getText().toString().equals(editConfrimPassword.getText().toString()))
        {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            return null;
        }
        if(editPassword.getText().toString().equals("") && editConfrimPassword.getText().toString().equals(""))
        {


            Toast.makeText(this, "Password Cannot be blank", Toast.LENGTH_SHORT).show();
            return null;

        }
        
          if(editPassword.getText().toString().equals(editConfrimPassword.getText().toString()) ){
            User user1 = new User(editfirstName.getText().toString(),
                    editlastName.getText().toString(),
                    editEmail.getText().toString(),
                    editConfrimPassword.getText().toString(),
                    editMobile.getText().toString());
            return user1;

        }
        return null;
    }
}