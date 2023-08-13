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

public class EditProfileActivity2 extends AppCompatActivity {

    EditText editfirstName,editlastName,editPassword,editEmail,editMobile;

    Button btnSave,btnCancel;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile2);

        editfirstName = findViewById(R.id.editfirstName);
        editlastName = findViewById(R.id.editlastName);
        editPassword = findViewById(R.id.editPassword);

        editEmail = findViewById(R.id.editEmail);
        editMobile = findViewById(R.id.editMobile);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);


         user = (User) getIntent().getSerializableExtra("user1");

        editEmail.setText(user.getEmail());
        editMobile.setText(user.getMobile());
        editPassword.setText(user.getPassword());
        editfirstName.setText(user.getFirst_name());
        editlastName.setText(user.getLast_name());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(EditProfileActivity2.this, MainActivity.class));
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    
                User user1 = new User();
                user1.setMobile(editMobile.getText().toString());
                user1.setEmail(editEmail.getText().toString());
                user1.setPassword(editPassword.getText().toString());
                user1.setLast_name(editlastName.getText().toString());
                user1.setFirst_name(editfirstName.getText().toString());

                RetroClient.getInstance().getApi().editUser(user.getUser_id(),user1).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                        {
                            Toast.makeText(EditProfileActivity2.this, "EDITED", Toast.LENGTH_SHORT).show();
                            finish();
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