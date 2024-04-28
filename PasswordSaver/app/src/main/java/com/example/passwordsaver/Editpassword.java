package com.example.passwordsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Editpassword extends AppCompatActivity {

    EditText etName, etText , etURL;
    TextView btncancel, btnsave;

    ImageView img ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpassword);
        init();
        int id0 = 0 ;
        int id = 0 ;
        String id1="",id2="",id3="",id4="" ;
        Bundle b = getIntent().getExtras();

        if (b != null) {
            id0 = b.getInt("id0",0);
             id = b.getInt("id", -1);
             id1 = b.getString("id1", "Default Value");
             id2 = b.getString("id2", "Default Value");
             id3 = b.getString("id3", "Default Value");
             id4 = b.getString("id4", "Default Value");
        }

        etURL.setText(id2);
        etName.setText(id3);
        etText.setText(id4);



        String finalId = id1;
        int fid = id ;
        int finalId7 = id0;
        String finalId8 = id2;
        String finalId9 = id3;
        String finalId10 = id4;
        btnsave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
//                saveContact();
                DatabaseHelper db = new DatabaseHelper(Editpassword.this);
                String name = etName.getText().toString().trim();
                String phone = etText.getText().toString();
                String url = etURL.getText().toString();
                db.updatepassword(fid, finalId,url,name,phone);
                MainActivity3.contacts.get(finalId7).setURL(url);
                MainActivity3.contacts.get(finalId7).setUsername(name);
                MainActivity3.contacts.get(finalId7).setPassword(phone);
                ListPasswords.adapter.notifyDataSetChanged();
//                startActivity(new Intent(Editpassword.this, ListPasswords.class));
                finish();
            }
        });

        int finalId1 = id;
        String finalId2 = id1;
        String finalId3 = id2;
        String finalId4 = id3;
        String finalId5 = id4;
        int finalId6 = id0;
        btncancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                DatabaseHelper database = new DatabaseHelper(Editpassword.this);

                database.deletePassword(finalId1, finalId2, finalId3, finalId4, finalId5);
                database.close();
                MainActivity3.contacts.remove(finalId6);
                ListPasswords.adapter.notifyDataSetChanged();
//                startActivity(new Intent(Editpassword.this, ListPasswords.class));
                finish();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        database.close();

    }
    private void init()
    {
        etName = findViewById(R.id.etName);
        etText = findViewById(R.id.etText);
        etURL = findViewById(R.id.etURL);
        btncancel = findViewById(R.id.tv_del);
        btnsave = findViewById(R.id.tv_save);
        img = findViewById(R.id.iv_back);
    }
}