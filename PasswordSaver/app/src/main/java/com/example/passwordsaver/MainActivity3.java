package com.example.passwordsaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {


    FloatingActionButton fabAdd;
    public static ArrayList<PasswordData> contacts = new ArrayList<>();
    public static ArrayList<PasswordData> deletedpassword = new ArrayList<>();
//    RecyclerView rvContact;
//    PasswordAdapter adapter;

    LinearLayout Loginlayout ;
    TextView Logincount ;
    LinearLayout Binlayout ;
    TextView Bincount ;

    public static String id ;

    @Override
    protected void onResume() {
        super.onResume();
        // Your code here
        Logincount.setText(contacts.size()+"");
        Bincount.setText(deletedpassword.size()+"");


    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        fabAdd = findViewById(R.id.fabAdd);
        Loginlayout = findViewById(R.id.LoginLayout);
        Logincount = findViewById(R.id.Logincount);
        Binlayout = findViewById(R.id.binLayout);
        Bincount = findViewById(R.id.bincount);

        Intent intent = getIntent();

        String name ;
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getStringExtra("name");
            id = name ;
            DatabaseHelper database = new DatabaseHelper(this);
            contacts = database.getPasswords(name);
            deletedpassword = database.getDeletedPasswords(name);
            database.close();
        }
        Logincount.setText(contacts.size()+"");
        Loginlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action to perform when LinearLayout is clicked
                // For example, navigate to a new activity or display a message
                startActivity(new Intent(MainActivity3.this, ListPasswords.class));
            }
        });
        Bincount.setText(deletedpassword.size()+"");
        Binlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action to perform when LinearLayout is clicked
                // For example, navigate to a new activity or display a message
                startActivity(new Intent(MainActivity3.this, ListDeletedPasswords.class));
            }
        });


//        rvContact = findViewById(R.id.rvContacts);
//        rvContact.setHasFixedSize(true);
//        rvContact.setLayoutManager(new LinearLayoutManager(this));
//

//            adapter = new PasswordAdapter(this, contacts);
//
//            rvContact.setAdapter(adapter);
//
            fabAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(MainActivity3.this, AddNewPassDetails.class);
//                    i.putExtra("name",);
                    startActivity(i);
//                    finish();
                }
            });
//            fabdel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent i = new Intent(MainActivity3.this, DeletedPasswordsActivity.class);
////                    i.putExtra("name",);
//                    startActivity(i);
////                    finish();
//                }
//            });
        }
}
