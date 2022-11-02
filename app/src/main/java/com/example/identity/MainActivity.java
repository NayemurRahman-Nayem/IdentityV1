package com.example.identity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.identity.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity{

    Button button;
    View reg;
    View forget;
    ActivityMainBinding binding ;
    FirebaseAuth firebaseAuth ;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance() ;
        button = findViewById(R.id.btnSignin);
        reg= findViewById(R.id.txtReg);
        forget = findViewById(R.id.txtFor);
        progressDialog = new ProgressDialog(this) ;
        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = binding.emailSin.getText().toString().trim() ;
               String password = binding.passSin.getText().toString().trim() ;
               progressDialog.show();
               firebaseAuth.signInWithEmailAndPassword(email,password)
                       .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               progressDialog.cancel();
                               Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                               Intent intent ;
                               intent = new Intent(MainActivity.this,LandingPage.class) ;
                               startActivity(intent);
                           }
                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               progressDialog.cancel();
                               Toast.makeText(MainActivity.this,e.getMessage()+"Something Wrong ! Try Again ",Toast.LENGTH_SHORT).show();
                           }
                       });
           }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPass.class) ;
                startActivity(intent) ;
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}




