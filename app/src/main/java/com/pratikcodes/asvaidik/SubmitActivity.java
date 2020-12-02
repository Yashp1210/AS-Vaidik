package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    TextView name,mail,phone,location,type,catagory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        name = findViewById(R.id.display_name);
        mail = findViewById(R.id.display_mail);
        phone = findViewById(R.id.display_phone);
        location = findViewById(R.id.display_location);
        type = findViewById(R.id.display_type);
        catagory = findViewById(R.id.display_catagory);

        Intent intent = getIntent();
        String displayname = intent.getStringExtra("cname");
        name.setText(displayname);

//        mail.setText(intent.getStringExtra("cemail"));
//        phone.setText(intent.getStringExtra("cphone"));
//        location.setText(intent.getStringExtra("clocation"));
//        type.setText(intent.getStringExtra("ctype"));
        //catagory.setText(intent.getStringExtra("ccatagory"));
    }
}