package com.example.passwordsaver;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    View loginView, signupView;
    Button LbtnLogin, SbtnLogin, LbtnSignup, SbtnSignup;
    TextInputEditText SetName,Setemail, SetPassword, SetCPassword, LetPassword , Letemail;

//    CheckBox cb1 , cb2 ;

    Fragment LoginFragment, SignUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        init();

        manager.beginTransaction()
                .hide(SignUpFragment)
                .show(LoginFragment)
                .commit();

//        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // If cb1 is checked, uncheck cb2
//                    cb2.setChecked(false);
//                }
//            }
//        });
//
//        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // If cb2 is checked, uncheck cb1
//                    cb1.setChecked(false);
//                }
//            }
//        });


//        Toast.makeText(this, "vdsfvsdvsd", Toast.LENGTH_SHORT).show();

//        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sPref.edit();
//        SharedPreferences.Editor editor1 = sPref.edit();
//
//        boolean flag = sPref.getBoolean("isLogin", false);
//        if(flag)
//        {
//            Intent intent = new Intent(MainActivity.this,
//                    Home.class);
//            startActivity(intent);
//            finish();
//        }
//        Toast.makeText(this, "dsdgfsgs", Toast.LENGTH_SHORT).show();

        LbtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .hide(LoginFragment)
                        .show(SignUpFragment)
                        .commit();
            }
        });

        SbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .show(LoginFragment)
                        .hide(SignUpFragment)
                        .commit();
            }
        });

        SbtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

                String name , email , password ;
                name = String.valueOf(SetName.getText());
                email = String.valueOf(Setemail.getText());
                password = String.valueOf(SetPassword.getText());

                helper.insertData(name,email,password);
                helper.close();


                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

                manager.beginTransaction()
                        .show(LoginFragment)
                        .hide(SignUpFragment)
                        .commit();


//                Intent intent = new Intent(MainActivity.this,
//                        MainActivity2.class);
//                startActivity(intent);
//                finish();
            }
        });

        LbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(MainActivity.this);

                String  email , password ;


//                Toast.makeText(MainActivity.this,"dcs", Toast.LENGTH_SHORT).show();


                email = String.valueOf(Letemail.getText());
                password = String.valueOf(LetPassword.getText());

                String a = helper.userExists(email,password);
                helper.close();

                Toast.makeText(MainActivity.this, ""+a, Toast.LENGTH_SHORT).show();


//                Toast.makeText(MainActivity.this, a + "", Toast.LENGTH_SHORT).show();

                if(a != "-1") {
                    Intent intent = new Intent(MainActivity.this,
                            MainActivity3.class);
                    intent.putExtra("name",a);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void init()
    {
        manager = getSupportFragmentManager();

        loginView = Objects.requireNonNull(manager.findFragmentById(R.id.fragLogin)).requireView();
        signupView = Objects.requireNonNull(manager.findFragmentById(R.id.fragSignup)).requireView();
        LbtnLogin = loginView.findViewById(R.id.btnLLogin);
        LbtnSignup = loginView.findViewById(R.id.btnLsignup);
        SbtnLogin = signupView.findViewById(R.id.btnSlogin);
        SbtnSignup = signupView.findViewById(R.id.btnSsignup);

        SetName = signupView.findViewById(R.id.etSname);
        Setemail = signupView.findViewById(R.id.etSemail);
        SetPassword = signupView.findViewById(R.id.etSPassword);
        SetCPassword = signupView.findViewById(R.id.etSCPassword);
        Letemail = loginView.findViewById(R.id.LetUsername);
        LetPassword = loginView.findViewById(R.id.LetPassword);
//        cb1 = signupView.findViewById(R.id.cbS1);  // Reference to your first CheckBox
//        cb2 = signupView.findViewById(R.id.cbS2);

        LoginFragment = manager.findFragmentById(R.id.fragLogin);
        SignUpFragment = manager.findFragmentById(R.id.fragSignup);

    }
}