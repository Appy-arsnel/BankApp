package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;

public class create extends AppCompatActivity {
    private EditText uname;
    private EditText Accountn;
    private EditText Bal;
    private EditText Pas;
    private Button Submit;
    private EditText Email;
    private String uid;

    private DatabaseReference reff;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toast.makeText(create.this, "Enter Details",Toast.LENGTH_SHORT).show();
        uname = findViewById(R.id.cname);
        Accountn = findViewById(R.id.cano);
        Bal = findViewById(R.id.cbal);
        Pas = findViewById(R.id.cPass);
        Submit= findViewById(R.id.submit);
        Email= findViewById(R.id.email);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rname = uname.getText().toString();
                String raccno = Accountn.getText().toString();
                String rbal = Bal.getText().toString();
                String rpas = Pas.getText().toString();
                String remail= Email.getText().toString();

                User users= new User(rname,raccno,rbal,rpas,remail);
                reff=FirebaseDatabase.getInstance().getReference("Users");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }
            });
                FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
                if (validate(remail,rpas))
                {
                    firebaseAuth.createUserWithEmailAndPassword(remail,rpas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (user == null) {
                                    // No session user
                                    return;
                                }
                                String userId = user.getUid();
                                    users.setAccountno(raccno);
                                users.setBal(rbal);
                                users.setName(rname);
                                users.setPas(rpas);
                                users.setEmail(remail);

                                reff.child(userId).setValue(users);


                                startActivity(new Intent(create.this,MainActivity.class));
                                Toast.makeText(create.this,"Account created", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });



                }
            }
        });



    }
    private boolean validate(String email,String passw)
    {
        if(email.isEmpty()||passw.length()<8)
        {
            Toast.makeText(this,"Please enter all details and Password should be of 8 characters or more",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}