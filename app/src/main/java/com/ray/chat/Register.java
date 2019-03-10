package com.ray.chat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText username, password, rpassword, phone, email;
    Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Access a Cloud Firestore instance from your Activity
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        username=findViewById(R.id.name);
        password=findViewById(R.id.pass);
        rpassword=findViewById(R.id.repass);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        regis=findViewById(R.id.registra);
        // Create a new user with a first and last name
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().equals(rpassword.getText().toString())
                        && !username.getText().toString().isEmpty()
                        && !password.getText().toString().isEmpty()
                        && !rpassword.getText().toString().isEmpty()
                        && !phone.getText().toString().isEmpty()
                        && !email.getText().toString().isEmpty()){
                    Map<String, Object> user;
                    user = new HashMap<>();
                    user.put("name", username.getText().toString());
                    user.put("pass", password.getText().toString());
                    user.put("phone", phone.getText().toString());
                    user.put("email", email.getText().toString());
                    // Add a new document with a generated ID
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Fail", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(Register.this, "Password gak sama", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
