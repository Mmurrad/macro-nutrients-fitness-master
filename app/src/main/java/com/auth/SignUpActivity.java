package com.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easyfitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText email_text,password_text,username_text,phone_text,age_text;
    Button signup_button;
    TextView login_link;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email_text=findViewById(R.id.signup_email_id);
        password_text=findViewById(R.id.sign_password_id);
        phone_text=findViewById(R.id.phone_id);
        age_text=findViewById(R.id.age_id);
        username_text=findViewById(R.id.username_id);
        signup_button=findViewById(R.id.signup_id);
        login_link=findViewById(R.id.login_link_id);
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserData");


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=username_text.getText().toString();
                String password=password_text.getText().toString();
                String email=email_text.getText().toString();
                String age=age_text.getText().toString();
                String phone=phone_text.getText().toString();

                int a=Integer.parseInt(age);

                if(username.isEmpty())
                {
                    username_text.setError("Enter UserName");
                    email_text.requestFocus();
                    return;
                }
                if(phone.isEmpty())
                {
                    phone_text.setError("Enter Phone");
                    phone_text.requestFocus();
                    return;
                }
                if(age.isEmpty())
                {
                    age_text.setError("Enter age");
                    age_text.requestFocus();
                    return;
                }
                if(a<14)
                {
                    Toast.makeText(SignUpActivity.this,"Your are unable to use this apps",Toast.LENGTH_SHORT).show();
                }
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
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    email_text.setError("Enter a valid Email");
                    email_text.requestFocus();
                    return;
                }
                else {
                    try{
                        UserInfo userInfo=new UserInfo(username,email,password,phone,age);
                        databaseReference.child(phone).setValue(userInfo);//ok?
                        //Toast.makeText(getApplicationContext(),""+ userInfo.getEmail(),Toast.LENGTH_LONG).show();
                    }catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                    }


                }
                try {
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                                    startActivity(intent);
                                } else {

                                }

                            }
                        });
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }
        });


        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent=new Intent(SignUpActivity.this,CalorieMeasureActivity.class);
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
