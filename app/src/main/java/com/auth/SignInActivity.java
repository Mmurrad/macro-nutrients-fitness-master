package com.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easyfitness.MainActivity;
import com.easyfitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText email_text,password_text;
    Button login_button;
    TextView regiser_link;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email_text=findViewById(R.id.email_id);
        password_text=findViewById(R.id.password_id);
        login_button=findViewById(R.id.login_id);

        regiser_link=findViewById(R.id.register_link_id);
        mAuth = FirebaseAuth.getInstance();


        regiser_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });



          login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_text.getText().toString();
                String password=password_text.getText().toString();

                if(email.isEmpty())
                {
                    email_text.setError("Enter Email");
                    email_text.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    password_text.setError("Enter Password");
                    password_text.requestFocus();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent=new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignInActivity.this,"Email or Password is not correct",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }
            }

        });
    }
}
