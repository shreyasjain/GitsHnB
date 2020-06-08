package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostelStudentComplaint extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String sessionId;

    EditText complaintEditText ;
    int count;
    String complaint;
    DatabaseReference postRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_student_complaint);

        complaintEditText = (EditText) findViewById(R.id.hostelComplaintEdittext);

        sessionId = getIntent().getStringExtra("key");
    }

    public void complaintSubmit(View view)
    {
        complaint = complaintEditText.getText().toString();

        final DatabaseReference postRef1 = database.getReference().child("COMPLAINTS").child("HOSTEL");
        postRef1.child(sessionId).child("complaint").setValue(complaint).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"success !",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Complain failed !",Toast.LENGTH_SHORT).show();
            }
        });

        postRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = (int)dataSnapshot.getChildrenCount();
                Log.i("count",String.valueOf(count));

                new CountDownTimer(1500,1000)
                {
                    @Override
                    public void onFinish() {
                        Intent i = new Intent(HostelStudentComplaint.this, HostlerActivity.class);
                        i.putExtra("key",sessionId);
                        startActivity(i);
                        finish();

                    }

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                }.start();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
