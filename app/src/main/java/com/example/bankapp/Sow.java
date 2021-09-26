package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sow extends AppCompatActivity {

    private TextView pname;
    private TextView pAccountn;
    private TextView pBal;
    private TextView pEmail;
    private String Name ;
    private String Accountno;
    private String Bal ;
    private String Email;
    private Button btn1;
    private Button btn2;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sow);
        Toast.makeText(Sow.this, "Details are:",Toast.LENGTH_SHORT).show();
        pname=findViewById(R.id.tname);
        pAccountn=findViewById(R.id.taccount);
        pBal=findViewById(R.id.tbalance);
        pEmail=findViewById(R.id.tEmail);
        btn1=findViewById(R.id.bback);
        btn2=findViewById(R.id.bback2);
        Data data=new Data();
        String uid=data.getPuid();

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Name=snapshot.child("name").getValue().toString();
                        Accountno=snapshot.child("accountno").getValue().toString();
                        Bal=snapshot.child("bal").getValue().toString();
                        Email=snapshot.child("email").getValue().toString();
                        pname.setText(Name);
                        pBal.setText(Bal);
                        pAccountn.setText(Accountno);
                        pEmail.setText(Email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sow.this,MainActivity.class));
                Toast.makeText(Sow.this,"Main Menu", Toast.LENGTH_SHORT).show();
            }
        });

    }
}