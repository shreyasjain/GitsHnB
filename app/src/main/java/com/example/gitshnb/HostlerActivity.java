package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.time.OffsetDateTime.now;

public class HostlerActivity extends AppCompatActivity {

    Button butnHostlerAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostler);
        butnHostlerAttendance = (Button)findViewById(R.id.Butn_Hosteler_attendance);
    }

    public void hostelerAttendance(View view)
    {
        Calendar calendar;
        SimpleDateFormat simpledateformat;
        calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String ate = simpledateformat.format(calendar.getTime());


        Toast.makeText(this, ate, Toast.LENGTH_SHORT).show();
    }
}
