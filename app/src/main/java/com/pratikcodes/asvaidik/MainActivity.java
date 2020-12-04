package com.pratikcodes.asvaidik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pratikcodes.asvaidik.fragments.HomeFragment;
import com.pratikcodes.asvaidik.fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    ImageButton btn_sign_in, btn_sign_up, btn_google_sign_in;
    Button btn_forget_password;
    TextInputLayout emailEntered, passwordEntered;
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;
    GoogleSignInClient mGoogleSignInClient;


    BottomNavigationView nav;
    FloatingActionButton fab;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_in = findViewById(R.id.sign_in);
        btn_forget_password = findViewById(R.id.forgot_password);
        btn_google_sign_in = findViewById(R.id.google_sign_in);
        btn_sign_up = findViewById(R.id.sign_up_btn);
        emailEntered = findViewById(R.id.logIn_email);
        passwordEntered = findViewById(R.id.logIn_password);
        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference("users");

        btn_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmailLoginIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btn_google_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_LONG).show();
                            updateUI(user);
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        try {
            if (user != null) {
                Toast.makeText(MainActivity.this, "Logged in using google", Toast.LENGTH_SHORT).show();
            }
        } catch (Error e){
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void userEmailLoginIn() {
        final String email = emailEntered.getEditText().getText().toString().trim();
        final String password = passwordEntered.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            emailEntered.setError("Field cannot be empty");
            emailEntered.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEntered.setError("Enter a valid email");
            emailEntered.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordEntered.setError("Field cannot be empty");
            passwordEntered.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mReference.child(mAuth.getCurrentUser().getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    checkEmailVerification();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailVerification() {
        mUser = mAuth.getCurrentUser();
        Boolean emailFlag = mUser.isEmailVerified();

        if(emailFlag){
            Toast.makeText(MainActivity.this, "Email Verified already go to homescreen", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please verify your email", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }
}
=======

        nav = findViewById(R.id.navhome);
        fab = findViewById(R.id.fab_req);
        tool = findViewById(R.id.hometool);
        setSupportActionBar(tool);

        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.screen,fragment).commit();

        nav.setOnNavigationItemSelectedListener(listener);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),ReqActivity.class);
            startActivity(intent);
        });

    }

    BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {

        switch(item.getItemId())
        {
            case R.id.home:
                HomeFragment home = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.screen,home).commit();
                break;

            case R.id.add:
                Intent intent = new Intent(getApplicationContext(),ReqActivity.class);
                startActivity(intent);
                break;

            case R.id.profile:
                ProfileFragment profile = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.screen,profile).commit();
                break;
        }
        return true;
    };
}

