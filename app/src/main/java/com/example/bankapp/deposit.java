package com.example.bankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class deposit extends AppCompatActivity {
    private EditText eName;

    private EditText ePassword;
    private Button eLogin;
    private  TextView eAttemptsInfo;
    User use =new User();

    boolean isValid = false;
    private int counter =5;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit2);
        eName= findViewById(R.id.etName);
        ePassword= findViewById(R.id.etPassword);
        eLogin= findViewById(R.id.btnLogin);
        eAttemptsInfo= findViewById(R.id.tvAttemptsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();
                use.setEmail(inputName);
                use.setPas(inputPassword);

                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if(inputName.isEmpty()||inputPassword.isEmpty()){
                    Toast.makeText(deposit.this, "Enter amount to withdraw",Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(inputName,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (user == null) {
                                    // No session user
                                    return;
                                }
                                String userId = user.getUid().toString().trim();
                                Data data=new Data(userId.trim());

                                Toast.makeText(deposit.this, "Successful Log in",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(deposit.this, deposittwo.class);
                                startActivity(intent);



                            }
                            else{
                                counter--;
                                Toast.makeText(deposit.this, "Incorrect credentials",Toast.LENGTH_SHORT).show();
                                eAttemptsInfo.setText("Number of Attempts remaning: "+counter);
                                if(counter==0){
                                    eLogin.setEnabled(false);
                                }
                            }
                        }
                    });


                }


            }
        });
    }

}
