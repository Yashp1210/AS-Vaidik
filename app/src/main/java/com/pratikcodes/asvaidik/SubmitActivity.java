package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SubmitActivity extends AppCompatActivity {

    TextView name, email, phone, location, type, category, description;
    Button confirm,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        confirm = findViewById(R.id.btnconfirm);
        home = findViewById(R.id.btnHome);

        name = findViewById(R.id.name_order);
        email = findViewById(R.id.email_order);
        phone = findViewById(R.id.phone_order);
        location = findViewById(R.id.location_order);
        type = findViewById(R.id.type_order);
        category = findViewById(R.id.category_order);
        description = findViewById(R.id.description_order);

        Intent intent =  getIntent();
        String nameOrder = intent.getStringExtra("name");
        String emailOrder = intent.getStringExtra("email");
        String phoneOrder = intent.getStringExtra("phone");
        String locationOrder = intent.getStringExtra("location");
        String typeOrder = intent.getStringExtra("type");
        String categoryOrder = intent.getStringExtra("category");
        String descriptionOrder = intent.getStringExtra("description");

        name.setText(nameOrder);
        email.setText(emailOrder);
        phone.setText(phoneOrder);
        location.setText(locationOrder);
        type.setText(typeOrder);
        category.setText(categoryOrder);
        description.setText(descriptionOrder);


        confirm.setOnClickListener(v -> {
            Intent mail = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + "Software Request From " + nameOrder + "&body=" + "Name : "+ nameOrder +"\n\nPhone : " + phoneOrder + "\n\nLocation : " + locationOrder + "\n\nType : " + typeOrder + "\n\ncategory : " +categoryOrder+ "\nDescription : "+descriptionOrder +"&to=" + "shuklasatyam456@gmail.com");
            mail.setData(data);
            startActivity(Intent.createChooser(mail, "Send Email to us.."));
        });

        home.setOnClickListener(v -> {
            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
        });

    }
}