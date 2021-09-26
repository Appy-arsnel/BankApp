package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class withdrawtwo extends AppCompatActivity {
    private EditText With;
    private Button Withdraw;
    private Button BWithdraw;
    DatabaseReference reff;
    private String Bal;
    private Double withd;
    private Double Bald;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawtwo);
        With = findViewById(R.id.with);
        Withdraw=findViewById(R.id.bwith);
        BWithdraw=findViewById(R.id.bwith2);
        Data data=new Data();
        String uid=data.getPuid();


        Withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iwith = With.getText().toString();
                withd=Double.parseDouble(iwith);
                reff= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bal=snapshot.child("bal").getValue().toString().trim();
                        Bald=Double.parseDouble(Bal);
                        if(withd<Bald) {
                            Bald = Bald - withd;
                            Bal = String.valueOf(Bald);
                            Toast.makeText(withdrawtwo.this,"Amount Withdrawn", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(withdrawtwo.this,"Not Enough money to withdraw",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        BWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map= new HashMap();
                map.put("bal",Bal);
                reff.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                         startActivity(new Intent(withdrawtwo.this,Sow.class));
                    }
                });


            }
        });

    }
}