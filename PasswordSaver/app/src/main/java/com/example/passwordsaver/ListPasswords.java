package com.example.passwordsaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ListPasswords extends AppCompatActivity {

    RecyclerView rvContact;
    @SuppressLint("StaticFieldLeak")
    public static PasswordAdapter adapter;

    ImageView img ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_passwords);

        rvContact = findViewById(R.id.rvContacts);
        rvContact.setHasFixedSize(true);
        rvContact.setLayoutManager(new LinearLayoutManager(this));

        img = findViewById(R.id.iv_back);

        adapter = new PasswordAdapter(this, MainActivity3.contacts);

//
        rvContact.setAdapter(adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    @Override
//    public void onDeleteContact(int index) {
//        MainActivity3.deletedpassword.add(MainActivity3.contacts.get(index));
//        MainActivity3.contacts.remove(index);
//        adapter.notifyDataSetChanged();
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    @Override
//    public void onUpdateContact(int index, String[] values) {
//            MainActivity3.contacts.get(index).setURL(values[0]);
//            MainActivity3.contacts.get(index).setUsername(values[1]);
//            MainActivity3.contacts.get(index).setPassword(values[2]);
////            contacts.get(index).setPhone(values[1]);
//            adapter.notifyDataSetChanged();
//    }


}
