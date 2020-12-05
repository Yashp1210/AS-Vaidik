package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ContactActivity extends AppCompatActivity {

    TextInputLayout email,msg;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
       email = findViewById(R.id.user_email);
       msg = findViewById(R.id.user_msg);

       btn = findViewById(R.id.msgbtn);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String useremail = email.getEditText().getText().toString().trim();
               String usermsg = msg.getEditText().getText().toString().trim();
                if(!useremail.isEmpty() && !usermsg.isEmpty()) {
                    Intent mail = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:?subject=" + "" + "&body=" + usermsg + "&to=" + "frostpratik03@gmail.com");
                    mail.setData(data);
                    startActivity(Intent.createChooser(mail, "Send Email to us.."));
                }
                else {
                    Toast.makeText(ContactActivity.this, "Please Enter Full Information", Toast.LENGTH_SHORT).show();
                }

           }
       });
    }
}