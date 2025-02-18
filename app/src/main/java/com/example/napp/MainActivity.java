package com.example.napp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.napp.broadcastreceiver.WifiBroadcastReceiver;
import com.example.napp.data.sql.DatabaseManager;
import com.example.napp.view.ActivityTask;
import com.example.napp.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView txt1 , txt2, txt3 , txt_gomain2;
    private WifiBroadcastReceiver wifiBroadcastReceiver;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiBroadcastReceiver = new WifiBroadcastReceiver();

        txt1 = findViewById(R.id.main_txt1);
        txt2 = findViewById(R.id.main_txt2);
        txt3 = findViewById(R.id.main_txt3);
        txt_gomain2 = findViewById(R.id.main_go2);
        databaseManager = DatabaseManager.getInstance(MainActivity.this);




        txt_gomain2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });


        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = databaseManager.dropTable();
                if (result) {
                    Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
                }
            }
        });

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityTask.class);
                startActivity(intent);
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroadcastReceiver);
    }
}