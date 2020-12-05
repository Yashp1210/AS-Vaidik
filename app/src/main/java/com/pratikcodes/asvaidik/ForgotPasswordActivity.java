package com.pratikcodes.asvaidik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    ImageButton btn_submit, btn_signUp, btn_resend;
    TextInputLayout email_entered;
    private FirebaseAuth mAuth;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_activity);
        tool = findViewById(R.id.forgotpasstool);
        setSupportActionBar(tool);

        email_entered = findViewById(R.id.email_forgot_password);
        btn_signUp = findViewById(R.id.sign_up_for_btn);
        btn_submit = findViewById(R.id.submit_btn);
        btn_resend = findViewById(R.id.resend_link_btn);

        mAuth = FirebaseAuth.getInstance();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterEmail();
            }
        });

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterEmail();
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(ForgotPasswordActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void enterEmail() {
        String userEmail = email_entered.getEditText().toString().trim();

        if (userEmail.isEmpty()) {
            email_entered.setError("Field cannot be empty");
            email_entered.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email_entered.setError("Enter a valid email");
            email_entered.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email link sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}