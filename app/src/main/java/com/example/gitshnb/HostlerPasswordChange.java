package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HostlerPasswordChange extends AppCompatActivity {


    EditText currentPasswordEdittext ;
    EditText newPasswordEdittext;
    EditText confirmPasswordEdittext;
    Button butnChangePassword;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String sessionId;
    String realPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostler_password_change);
        sessionId = getIntent().getStringExtra("key");

        currentPasswordEdittext = (EditText)findViewById(R.id.hostelChangeOldPassword);
        newPasswordEdittext = (EditText)findViewById(R.id.hostelChangeNewPassword);
        confirmPasswordEdittext = (EditText)findViewById(R.id.hostelChangeConfirmPassword);


    }

    public void HostlerPasswordSubmit(View view)
    {

        final String currentPassword = currentPasswordEdittext.getText().toString();
        final String newPassword = newPasswordEdittext.getText().toString();
        final String confirmPassword = confirmPasswordEdittext.getText().toString();

        final DatabaseReference reff2 = database.getReference().child("USERS").child(sessionId).child("PASSWORD");

        reff2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               realPassword = dataSnapshot.getValue().toString();

               if(currentPassword.equals(realPassword))
               {
                   if(newPassword.equals(confirmPassword))
                   {
                       reff2.setValue(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(getApplicationContext(),"Password changed successfully !",Toast.LENGTH_SHORT).show();

                               new CountDownTimer(1500,1000)
                               {
                                   @Override
                                   public void onFinish() {
                                       Intent i = new Intent(HostlerPasswordChange.this, HostlerActivity.class);
                                       i.putExtra("key",sessionId);
                                       startActivity(i);

                                   }

                                   @Override
                                   public void onTick(long millisUntilFinished) {

                                   }
                               }.start();
                           }
                       });
                   }else {
                       Toast.makeText(getApplicationContext(),"ne password and confirm password must be same",Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(getApplicationContext(),"current password is incorrect",Toast.LENGTH_SHORT).show();
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
