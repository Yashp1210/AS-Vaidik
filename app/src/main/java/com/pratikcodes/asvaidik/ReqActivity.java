package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ReqActivity extends AppCompatActivity {

    EditText name,email,phone,location,description;
    CheckBox android,web,pooja,poojaAccesories,havanSamgri,pandits,astrologer;
    Button next;
    Toolbar tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);
        tool = findViewById(R.id.reqtool);
        setSupportActionBar(tool);

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

        String type = "";

        next.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),SubmitActivity.class);
            intent.putExtra("cname",name.getText());
            intent.putExtra("cemail",email.getText());
            intent.putExtra("cphone",phone.getText());
            intent.putExtra("clocation",location.getText());
            intent.putExtra("cdesc",description.getText());


            if(android.isChecked())
            {
                type.concat("Android App\n");
            }

            if(web.isChecked())
            {
                type.concat("Website\n");
            }

            if(pooja.isChecked())
            {
                intent.putExtra("cpooja","Pooja");
            }

            if(poojaAccesories.isChecked())
            {
                intent.putExtra("cpoojaaccesories","Pooja Accessories");
            }

            if(havanSamgri.isChecked())
            {
                intent.putExtra("chavan","Havan Samagri");
            }

            if(pandits.isChecked())
            {
                intent.putExtra("cpandit","Pandits");
            }

            if(astrologer.isChecked())
            {
                intent.putExtra("castrologer","astrologer");
            }

            intent.putExtra("ctype",type);
            startActivity(intent);

        });
    }
}