package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.time.OffsetDateTime.now;

public class HostlerActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //String inputFormat = "h:mm a";
    boolean attend;
    Date date0;
    Date currentDate ;
    Date lowerRange;
    Date upperRange;
    SimpleDateFormat parseFormat = new SimpleDateFormat("h:mm a");
    SimpleDateFormat keyFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
    Button butnHostlerAttendance;
    TextView UserIdTextView;
    double latitude;
    double longitude;
    String sessionId;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostler);




        /*-------------------------------------location*/

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                longitude = location.getLongitude();
                latitude = location.getLatitude();


                Log.i("Location", location.toString());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };

        if (Build.VERSION.SDK_INT < 23) {

            //   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        } else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // ask for permission

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {

                // we have permission!

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }

        }


        /* ------------------------------------------*/







      //  sendBroadcast(intent);
        butnHostlerAttendance = (Button)findViewById(R.id.Butn_Hosteler_attendance);
        UserIdTextView = (TextView)findViewById(R.id.userId_Text);
        sessionId = getIntent().getStringExtra("key");
        UserIdTextView.setText(sessionId);
    }

    public void hostelerComplaint(View view)
    {
        Intent i = new Intent(this, HostelStudentComplaint.class);
        i.putExtra("key",sessionId);
        startActivity(i);

    }

    public void hostelerChangePassword(View view)
    {
        Intent i = new Intent(this, HostlerPasswordChange.class);
        i.putExtra("key",sessionId);
        startActivity(i);
    }





    public void hostelerAttendance (View view)throws Exception
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo1)
                .setContentTitle("GitsHnB")
                .setContentText("Hellooooooooo")
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        date0 = Calendar.getInstance().getTime();
        Date date1 = parseFormat.parse("9:00 PM");
        Date date2 = parseFormat.parse("9:30 PM");

        String keyDateString = keyFormat.format(date0);
        String Stringdate0 = parseFormat.format(date0);
        String Stringdate1 = parseFormat.format(date1);
        String Stringdate2 = parseFormat.format(date2);

        currentDate = parseFormat.parse(Stringdate0);
        lowerRange = parseFormat.parse(Stringdate1);
        upperRange = parseFormat.parse(Stringdate2);
        compareDates();



        if(attend == false)
        {
            Toast.makeText(this,"Attendance is not allowed at this time !",Toast.LENGTH_SHORT).show();
        }
        else if(longitude==0.0 || latitude==0.0)
        {
            Toast.makeText(this,"wait for few moments till GPS fetches your location !",Toast.LENGTH_SHORT).show();
        }
        else if((latitude>24.620470 || latitude<24.618805) || longitude>73.857146 || longitude<73.855224)
        {
            Toast.makeText(this,"Please be in Hostel to mark attendance !",Toast.LENGTH_SHORT).show();
        }
        else if(attend == true && latitude<=24.620470 && latitude>=24.618805 && longitude<=73.857146 && longitude>=73.855224)
        {
            Toast.makeText(this,"Attendance marked successfully !",Toast.LENGTH_SHORT).show();
            DatabaseReference postRef = database.getReference().child("ATTENDANCE").child(keyDateString);
            postRef.child(sessionId).setValue("PRESENT");
        }

       //Toast.makeText(this,String.valueOf(Stringdate1)+" "+String.valueOf(Stringdate0)+" "+String.valueOf(Stringdate2),Toast.LENGTH_SHORT).show();

       // Toast.makeText(this,String.valueOf(attend),Toast.LENGTH_LONG).show();

       //Toast.makeText(this,String.valueOf(latitude)+" "+String.valueOf(longitude),Toast.LENGTH_LONG).show();
       //Toast.makeText(this,String.valueOf(longitude),Toast.LENGTH_SHORT).show();


    }


   private void compareDates(){



        if ( lowerRange.before( currentDate ) && upperRange.after(currentDate)) {
            //yada yada
            attend = true;

        }
        else{
            attend = false;
        }
    }








}
