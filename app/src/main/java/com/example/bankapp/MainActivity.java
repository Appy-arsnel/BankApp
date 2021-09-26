package com.example.bankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button create;
    private Button deposit;
    private  Button withdraw;
    private Button print;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create = (Button) findViewById(R.id.create);
        deposit= (Button) findViewById(R.id.deposit);
        withdraw= (Button) findViewById(R.id.withdraw);
        print= (Button) findViewById(R.id.print);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencreate();
            }
        });
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendeposit();
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwithdraw();
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprint();
            }
        });

    }
    public void opencreate(){
        Intent intent = new Intent(this, create.class);
        startActivity(intent);
    }
    public void opendeposit(){
        Intent intent = new Intent(this, deposit.class);
        startActivity(intent);
    }
    public void openwithdraw(){
        Intent intent = new Intent(this, withdraw.class);
        startActivity(intent);
    }
    public void openprint(){
        Intent intent = new Intent(this, print.class);
        startActivity(intent);
    }



}