package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class SubmitActivity extends AppCompatActivity {

    TextView name, email, phone, location, type, category, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

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


    }
}