package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_faculty extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    String FirstName;
    String LastName;
    String hostelNo;
    String contactNo;
    String password;
    String gender;
    String UserId;
    String name;

    EditText FacultyFisrtNameEdittext;
    EditText FacultyLastNameEdittext;
    EditText FacultyHostelEdittext;
    EditText FacultyContactEdittext;
    EditText FacultyPasswordEdittext;
    EditText FacultyGenderEdittext;
    Button butnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_faculty);

        FacultyFisrtNameEdittext = (EditText)findViewById(R.id.add_faculty_dialog_firstname);
        FacultyLastNameEdittext = (EditText)findViewById(R.id.add_faculty_dialog_lastname);
        FacultyContactEdittext = (EditText)findViewById(R.id.add_faculty_dialog_Contact);
        FacultyHostelEdittext = (EditText)findViewById(R.id.add_faculty_dialog_HostelNo);
        FacultyPasswordEdittext = (EditText)findViewById(R.id.add_faculty_dialog_Password);
        FacultyGenderEdittext = (EditText)findViewById(R.id.add_faculty_dialog_Gender);
        butnSignUp = (Button)findViewById(R.id.butnSignF);

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
        hostelNo = FacultyHostelEdittext.getText().toString();
        password = FacultyPasswordEdittext.getText().toString();
        gender = FacultyGenderEdittext.getText().toString();

      //  name = FirstName+" "+LastName;

    }

    public void writeData()
    {
        DatabaseReference reff2 = database.getReference().child("USERS").child(contactNo);
        reff2.child("PASSWORD").setValue(password);
        reff2.child("TYPE").setValue("faculty");

        DatabaseReference reff = database.getReference().child("DETAILS").child("FACULTY").child(contactNo);
        reff.child("FIRSTNAME").setValue(FirstName);
        reff.child("LASTNAME").setValue(LastName);
        reff.child("CONTACT").setValue(contactNo);
        reff.child("HOSTEL").setValue(hostelNo);
        reff.child("GENDER").setValue(gender);
    }
}
