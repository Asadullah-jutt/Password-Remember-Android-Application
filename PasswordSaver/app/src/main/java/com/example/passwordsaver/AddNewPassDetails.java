package com.example.passwordsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddNewPassDetails extends AppCompatActivity {


    EditText etName, etText , etURL;
    TextView btndel, btnsave;

    ImageView imgg ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pass_details);

        init();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            saveContact();
            startActivity(new Intent(AddNewPassDetails.this, MainActivity3.class));
            finish();
        }
    });

        imgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewPassDetails.this, MainActivity3.class));
                finish();
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewPassDetails.this, MainActivity3.class));
                finish();
            }
        });


    }



    private void saveContact()
    {
        String name = etName.getText().toString().trim();
        String phone = etText.getText().toString();
        String url = etURL.getText().toString();


        DatabaseHelper database = new DatabaseHelper(this);

        database.insertPassword(MainActivity3.id,name,phone,url);
        MainActivity3.contacts.add(new PasswordData(0,MainActivity3.id,name,phone,url));

        database.close();

    }
    private void init()
    {
        etName = findViewById(R.id.etName);
        etText = findViewById(R.id.etText);
        etURL = findViewById(R.id.etURL);
        btndel= findViewById(R.id.tv_del);
        btnsave = findViewById(R.id.tv_save);
        imgg = findViewById(R.id.iv_back);
    }

}