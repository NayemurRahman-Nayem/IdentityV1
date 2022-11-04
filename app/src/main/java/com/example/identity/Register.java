package com.example.identity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.identity.databinding.ActivityMainBinding;
import com.example.identity.databinding.RegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class Register extends AppCompatActivity
{
    FirebaseAuth firebaseAuth ;
    RegisterBinding binding ;
    TextView login_again;
    ProgressDialog progressDialog ;
    FirebaseFirestore firebaseFirestore ;
    protected void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState) ;
            setContentView(R.layout.register) ;
            firebaseFirestore = FirebaseFirestore.getInstance() ;
            firebaseAuth = FirebaseAuth.getInstance() ;
            binding = RegisterBinding.inflate(getLayoutInflater()) ;
            setContentView(binding.getRoot()) ;
            login_again = findViewById(R.id.haveAcc);
            progressDialog = new ProgressDialog(this) ;
            binding.btnReg.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v)
                {
                    String email = binding.emailReg.getText().toString().trim() ;
                    String name = binding.nameReg.getText().toString() ;
                    String profession = binding.profession.getText().toString() ;
                    String password = binding.passReg.getText().toString() ;
                    String age = binding.age.getText().toString() ;
                    String country = binding.country.getText().toString() ;
                    String confirmPass = binding.passConf.getText().toString() ;
                    if(password==confirmPass)
                    {
                        progressDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>()
                            {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel() ;
                                    Intent intent ;
                                    intent = new Intent(Register.this,MainActivity.class) ;
                                    startActivity(intent) ;
                                    progressDialog.cancel() ;
                                    firebaseFirestore.collection("User")
                                            .document(FirebaseAuth.getInstance().getUid())
                                            .set(new UserModel(name,profession,email,age,country)) ;
                                }
                            })
                            .addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    progressDialog.cancel();
                                }
                            });
                    }
                    else
                    {
                        Toast.makeText(Register.this,"Passwords Doesn't Match! ",Toast.LENGTH_SHORT).show() ;
                    }
                }
            });
            login_again.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(Register.this,MainActivity.class));
                }
            });
    }
}







    
    


