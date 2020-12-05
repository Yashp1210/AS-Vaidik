package com.pratikcodes.asvaidik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pratikcodes.asvaidik.fragments.HomeFragment;
import com.pratikcodes.asvaidik.fragments.ProfileFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    FloatingActionButton fab;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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








