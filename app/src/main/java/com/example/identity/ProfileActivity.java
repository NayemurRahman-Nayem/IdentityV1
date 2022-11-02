package com.example.identity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileActivity extends AppCompatActivity {

    TextView name , email , profession ;
    Button singout ;
    FirebaseAuth firebaseAuth ;
    FirebaseFirestore firebaseFirestore ;
    String userID ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = findViewById(R.id.name) ;
        email = findViewById(R.id.email) ;
        profession = findViewById(R.id.about) ;
        singout = findViewById(R.id.logout);
        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseFirestore = FirebaseFirestore.getInstance() ;
        userID = firebaseAuth.getCurrentUser().getUid() ;
        DocumentReference documentReference = firebaseFirestore.collection("User").document(userID) ;
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("name"));
                email.setText(value.getString("email"));
                profession.setText(value.getString("profession"));
            }
        });

        singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
