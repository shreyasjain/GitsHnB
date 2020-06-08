package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WardenNotice extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String sessionId;

    EditText NoticeEditText ;
    int count;
    String notice;
    DatabaseReference postRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_notice);

        NoticeEditText = (EditText) findViewById(R.id.wardenNoticeEdittext);

        sessionId = getIntent().getStringExtra("key");

    }

    public void noticeSubmit(View view)
    {
        notice = NoticeEditText.getText().toString();

        final DatabaseReference postRef1 = database.getReference().child("NOTICE").child("HOSTEL");

        postRef1.child(sessionId).child("notice").setValue(notice).addOnSuccessListener(new OnSuccessListener<Void>() {
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

                        Intent i = new Intent(WardenNotice.this, Add_new_user.class);
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
