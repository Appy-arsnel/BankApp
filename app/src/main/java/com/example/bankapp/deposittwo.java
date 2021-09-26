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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class deposittwo extends AppCompatActivity {

    private EditText Dep;
    private Button Deposit;
    private Button Back;
    DatabaseReference reff;
    private String Bal;
    private Double Depp;
    private Double Bald;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposittwo);
        Dep = findViewById(R.id.dep);
        Deposit=findViewById(R.id.bdep);
        Back=findViewById(R.id.bdep2);
        Data data=new Data();
        String uid=data.getPuid();

        Toast.makeText(deposittwo.this, "Enter Deposit Amount:",Toast.LENGTH_SHORT).show();

        Deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idep = Dep.getText().toString().trim();
                Depp=Double.valueOf(idep);
                reff= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bal=snapshot.child("bal").getValue().toString().trim();
                        Bald=Double.valueOf(Bal);
                        Bald = Bald +Depp;
                        Bal = String.valueOf(Bald);
                        Toast.makeText(deposittwo.this,"Amount Deposited", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(deposittwo.this,"Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map= new HashMap();
                map.put("bal",Bal);
                   reff.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {

                           startActivity(new Intent(deposittwo.this,Sow.class));
                       }
                   });


            }
        });

    }
}