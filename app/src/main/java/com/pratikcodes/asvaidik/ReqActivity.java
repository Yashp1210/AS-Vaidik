package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ReqActivity extends AppCompatActivity {

    EditText name,email,phone,location,description;
    CheckBox android,web,pooja,poojaAccesories,havanSamgri,pandits,astrologer;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);
        getSupportActionBar().setTitle("Requirements");

        name = findViewById(R.id.cname);
        email = findViewById(R.id.cmail);
        phone = findViewById(R.id.cphone);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);

        android = findViewById(R.id.check_androidapp);
        web = findViewById(R.id.check_website);

        pooja = findViewById(R.id.pooja);
        poojaAccesories = findViewById(R.id.poojaacces);
        havanSamgri = findViewById(R.id.havan);
        pandits = findViewById(R.id.pandit);
        astrologer = findViewById(R.id.astrologer);

        next = findViewById(R.id.nextbtn);



        next.setOnClickListener(v -> {



        });
    }
}