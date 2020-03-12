package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewBusFaculty extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    String FirstName;
    String LastName;
    String contactNo;
    String password;
    String gender;


    EditText FacultyFisrtNameEdittext;
    EditText FacultyLastNameEdittext;
    EditText FacultyContactEdittext;
    EditText FacultyPasswordEdittext;
    EditText FacultyGenderEdittext;
    Button butnSignUp;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bus_faculty);

        sessionId = getIntent().getStringExtra("key");

        FacultyFisrtNameEdittext = (EditText)findViewById(R.id.add_faculty_Bus_firstname);
        FacultyLastNameEdittext = (EditText)findViewById(R.id.add_faculty_Bus_lastname);
        FacultyContactEdittext = (EditText)findViewById(R.id.add_faculty_Bus_Contact);
        FacultyPasswordEdittext = (EditText)findViewById(R.id.add_faculty_Bus_Password);
        FacultyGenderEdittext = (EditText)findViewById(R.id.add_faculty_Bus_Gender);
        butnSignUp = (Button)findViewById(R.id.butnSignBusF);
    }

    public void facultySignUp(View view)
    {
        getData();
        writeData();
    }

    public void getData()
    {
        FirstName = FacultyFisrtNameEdittext.getText().toString();
        LastName = FacultyLastNameEdittext.getText().toString();
        contactNo = FacultyContactEdittext.getText().toString();
        password = FacultyPasswordEdittext.getText().toString();
        gender = FacultyGenderEdittext.getText().toString();

        //  name = FirstName+" "+LastName;

    }

    public void writeData()
    {
        DatabaseReference reff2 = database.getReference().child("USERS").child(contactNo);
        reff2.child("PASSWORD").setValue(password);
        reff2.child("TYPE").setValue("facultyBus");

        DatabaseReference reff = database.getReference().child("DETAILS").child("FACULTY").child(contactNo);
        reff.child("FIRSTNAME").setValue(FirstName);
        reff.child("LASTNAME").setValue(LastName);
        reff.child("CONTACT").setValue(contactNo);
        reff.child("GENDER").setValue(gender).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Faculty Registered successfully",Toast.LENGTH_SHORT).show();


                new CountDownTimer(1500,1000)
                {
                    @Override
                    public void onFinish() {
                        Intent i = new Intent(AddNewBusFaculty.this, AddNewBusUser.class);
                        i.putExtra("key",sessionId);
                        startActivity(i);

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();
            }
        });
    }
}
