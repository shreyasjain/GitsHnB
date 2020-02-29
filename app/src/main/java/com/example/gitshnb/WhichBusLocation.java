package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WhichBusLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_bus_location);
    }

    public void bus1Method(View view){
        Intent i = new Intent(WhichBusLocation.this, BusGeolocation.class);
        i.putExtra("key","BusNumber1");
        startActivity(i);
    }

    public void bus2Method(View view){
        Intent i = new Intent(WhichBusLocation.this, BusGeolocation.class);
        i.putExtra("key","BusNumber2");
        startActivity(i);
    }

    public void bus3Method(View view){
        Intent i = new Intent(WhichBusLocation.this, BusGeolocation.class);
        i.putExtra("key","BusNumber3");
        startActivity(i);

    }

    public void bus4Method(View view){
        Intent i = new Intent(WhichBusLocation.this, BusGeolocation.class);
        i.putExtra("key","BusNumber4");
        startActivity(i);

    }
}
