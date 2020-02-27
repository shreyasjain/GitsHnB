package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_student extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    String name;
    String rollNo;
    String year;
    String fathersName;
    String emailId;
    String address;
    String password;
    String contactNo;
    String roomNo;
    String userId;
    String branch;
    String hostelNo;
    String gender;

    EditText studentNameEdittext;
    EditText studentRoomEdittext;
    EditText studentRollEdittext;
    EditText studentFatherEdittext;
    EditText studentEmailEdittext;
    EditText studentAddressEdittext;
    EditText studentPasswordEdittext;
    EditText studentContactEdittext;
    EditText studentYearEdittext;
    EditText studentBranchEdittext;
    EditText studentHostelNoEdittext;
    EditText studentGenderEdittext;
    Button butnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        studentNameEdittext = (EditText)findViewById(R.id.add_student_dialog_name);
        studentRollEdittext = (EditText)findViewById((R.id.add_student_dialog_Rollno));
        studentFatherEdittext = (EditText)findViewById(R.id.add_student_dialog_FathersName);
        studentEmailEdittext = (EditText)findViewById(R.id.add_student_dialog_email);
        studentAddressEdittext = (EditText)findViewById(R.id.add_student_dialog_Address);
        studentPasswordEdittext = (EditText)findViewById(R.id.add_student_dialog_Password);
        studentContactEdittext = (EditText)findViewById(R.id.add_student_dialog_Contact);
        studentRoomEdittext = (EditText)findViewById(R.id.add_student_dialog_Room);
        studentYearEdittext = (EditText)findViewById(R.id.add_student_dialog_Year);
        studentBranchEdittext = (EditText)findViewById(R.id.add_student_dialog_Branch);
        studentHostelNoEdittext = (EditText)findViewById(R.id.add_student_dialog_HostelNo);
        studentGenderEdittext = (EditText)findViewById(R.id.add_student_dialog_Gender);
        butnSignUp = (Button)findViewById(R.id.butnSign);



    }

    public  void studentSignUp(View view)
    {
        getData();
        writeData();

    }

    public void getData()
    {
        name = studentNameEdittext.getText().toString();
        rollNo = studentRollEdittext.getText().toString();
        year = studentYearEdittext.getText().toString();
        fathersName = studentFatherEdittext.getText().toString();
        emailId = studentEmailEdittext.getText().toString();
        address = studentAddressEdittext.getText().toString();
        password = studentPasswordEdittext.getText().toString();
        contactNo = studentContactEdittext.getText().toString();
        roomNo = studentRoomEdittext.getText().toString();
        userId = studentRollEdittext.getText().toString();
        branch = studentBranchEdittext.getText().toString();
        hostelNo = studentHostelNoEdittext.getText().toString();
        gender = studentGenderEdittext.getText().toString();
    }

    public void writeData()
    {

        DatabaseReference reff2 = database.getReference().child("USERS").child(userId);
        reff2.child("PASSWORD").setValue(password);
        reff2.child("TYPE").setValue("student");
        DatabaseReference reff = database.getReference().child("DETAILS").child("STUDENT").child(userId);
        reff.child("Name").setValue(name);
        reff.child("YEAR").setValue(year);
        reff.child("FATHERSNAME").setValue(fathersName);
        reff.child("EMAIL").setValue(emailId);
        reff.child("ADDRESS").setValue(address);
        reff.child("CONTACT").setValue(contactNo);
        reff.child("ROOM").setValue(roomNo);
        reff.child("BRANCH").setValue(branch);
        reff.child("HOSTEL").setValue(hostelNo);
        reff.child("GENDER").setValue(gender);
    }
}
