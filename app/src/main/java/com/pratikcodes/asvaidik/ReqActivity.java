package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
            Intent intent = new Intent(getApplicationContext(),SubmitActivity.class);
            startActivity(intent);
        });
    }
}