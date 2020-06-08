package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_new_user extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button butnStudentComplaints;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        sessionId = getIntent().getStringExtra("key");

        butnStudentComplaints = (Button)findViewById(R.id.Student_Complaints_button);

        DatabaseReference complaintRef = database.getReference().child("COMPLAINTS").child("HOSTEL");
        complaintRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((int)dataSnapshot.getChildrenCount()!=0)
                {
                    butnStudentComplaints.setBackgroundResource(R.drawable.complaint_button);
                    butnStudentComplaints.setText("Complaints "+"("+dataSnapshot.getChildrenCount()+")");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void newStudentMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), Add_new_student.class);
        i.putExtra("key",sessionId);
        startActivity(i);
    }

    public void newFacultyMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), Add_new_faculty.class);
        i.putExtra("key",sessionId);
        startActivity(i);
    }

    public void StudentComplaints(View view)
    {
        Intent i = new Intent(getApplicationContext(), studentComplaintList.class);
        i.putExtra("key",sessionId);
        startActivity(i);
    }

    public void viewStudentDetail(View view)
    {
        Intent i = new Intent(getApplicationContext(), StudentDetail.class);
        i.putExtra("key",sessionId);
        startActivity(i);
    }

    public void AddStudentNotice(View view)
    {
        Intent i = new Intent(this, WardenNotice.class);
        i.putExtra("key",sessionId);
        startActivity(i);

    }




}
