package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;

import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReqActivity extends AppCompatActivity {

    EditText ename,eemail,ephone,elocation,edescription;
    CheckBox android,web,pooja,poojaAccesories,havanSamgri,pandits,astrologer;
    Button next;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);
        tool = findViewById(R.id.reqtool);
        setSupportActionBar(tool);


        ename = findViewById(R.id.cname);
        eemail = findViewById(R.id.cmail);
        ephone = findViewById(R.id.cphone);
        elocation = findViewById(R.id.location);
        edescription = findViewById(R.id.description);

        //checkboxes for type
        android = findViewById(R.id.check_androidapp);
        web = findViewById(R.id.check_website);

        //checkboxes for category
        pooja = findViewById(R.id.pooja);
        poojaAccesories = findViewById(R.id.poojaacces);
        havanSamgri = findViewById(R.id.havan);
        pandits = findViewById(R.id.pandit);
        astrologer = findViewById(R.id.astrologer);

        //button
        next = findViewById(R.id.nextbtn);

        next.setOnClickListener(v -> {
            nextActivity();
        });
    }

    private void nextActivity() {
        String nameEntered = ename.getText().toString().trim();
        String emailEntered = eemail.getText().toString().trim();
        String phoneEntered = ephone.getText().toString().trim();
        String locationEntered = elocation.getText().toString().trim();
        boolean isAndroidChecked = android.isChecked();
        boolean isWebChecked = web.isChecked();
        boolean isPoojaChecked = pooja.isChecked();
        boolean isPoojaAccessoriesChecked = poojaAccesories.isChecked();
        boolean isHavanSamagriChecked = havanSamgri.isChecked();
        boolean isPanditChecked = pandits.isChecked();
        boolean isAstrologerChecked = astrologer.isChecked();
        String descriptionEntered = edescription.getText().toString().trim();
        String typeEntered = "";
        StringBuilder categoryEntered = new StringBuilder();

        if(nameEntered.isEmpty()){
            ename.setError("Field cannot be empty");
            ename.requestFocus();
            return;
        }

        if(emailEntered.isEmpty()){
            eemail.setError("Field cannot be empty");
            eemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailEntered).matches()){
            eemail.setError("Enter a valid email");
            eemail.requestFocus();
            return;
        }

        if(phoneEntered.isEmpty()){
            ephone.setError("Field cannot be empty");
            ephone.requestFocus();
            return;
        }

        if(locationEntered.isEmpty()){
            elocation.setError("Field cannot be empty");
            elocation.requestFocus();
            return;
        }

        if (!isAndroidChecked && !isWebChecked){
            Toast.makeText(ReqActivity.this, "Select atleast one type", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isAndroidChecked && isWebChecked){
            typeEntered = "Android, Web";
        } else if (isAndroidChecked){
            typeEntered = "Android";
        } else if (isWebChecked){
            typeEntered = "Web";
        }

        if (!isPoojaChecked && !isPoojaAccessoriesChecked && !isHavanSamagriChecked && !isPanditChecked && !isAstrologerChecked){
            Toast.makeText(ReqActivity.this, "Choose atleast one category", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isPoojaChecked){
            categoryEntered.append("Pooja\n");
        }
        if (isPoojaAccessoriesChecked){
            categoryEntered.append("Pooja Accessories\n");
        }
        if (isHavanSamagriChecked){
            categoryEntered.append("Havan Samagri\n");
        }
        if (isPanditChecked){
            categoryEntered.append("Pandits\n");
        }
        if (isAstrologerChecked){
            categoryEntered.append("Astrologer\n");
        }

        if(descriptionEntered.isEmpty()){
            edescription.setError("Field cannot be empty");
            edescription.requestFocus();
            return;
        }

        Intent intent = new Intent(getApplicationContext(),SubmitActivity.class);
        intent.putExtra("name", nameEntered);
        intent.putExtra("email", emailEntered);
        intent.putExtra("phone", phoneEntered);
        intent.putExtra("location", locationEntered);
        intent.putExtra("type", typeEntered);
        intent.putExtra("category", categoryEntered.toString());
        intent.putExtra("description", descriptionEntered);
        startActivity(intent);
    }
}