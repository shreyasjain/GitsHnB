package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String username;
    String password;

    EditText LoginUsernameEdittext;
    EditText LoginPasswordEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginPasswordEdittext = (EditText)findViewById(R.id.Login_Password);
        LoginUsernameEdittext = (EditText)findViewById(R.id.Login_UserId);


    }

    public void signIn(View view)
    {
        getData();
        signInChecker();

    }

    public void getData()
    {
        username = LoginUsernameEdittext.getText().toString();
        password = LoginPasswordEdittext.getText().toString();
        //  name = FirstName+" "+LastName;
    }
    String wrongPass = "Wrong Password";
    public void signInChecker()
    {
        DatabaseReference postRef = database.getReference().child("USERS");
        postRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(username).exists()){
                    String RetPassword = (String)dataSnapshot.child(username).child("PASSWORD").getValue();
                    String RetType = (String)dataSnapshot.child(username).child("TYPE").getValue();
                    if(RetPassword.equals(password)){
                        if(RetType.equals("studentBus")){
                            Intent i = new Intent(LoginActivity.this, WhichBusLocation.class);
                            i.putExtra("key",username);
                            startActivity(i);

                        } else if(RetType.equals("facultyBus")){
                            Intent i = new Intent(LoginActivity.this, AddNewBusUser.class);
                            i.putExtra("key",username);
                            startActivity(i);

                        } else if(RetType.equals("studentHostel")){
                            Intent i = new Intent(LoginActivity.this, HostlerActivity.class);
                            i.putExtra("key",username);
                            startActivity(i);


                        } else if(RetType.equals("facultyHostel")){
                            Intent i = new Intent(LoginActivity.this, Add_new_user.class);
                            i.putExtra("key",username);
                            startActivity(i);

                        } else if(RetType.equals("driver")){
                            Intent i = new Intent(LoginActivity.this, SharingLocation.class);
                            i.putExtra("key",username);
                            startActivity(i);

                        }
                        Log.i("Logged","In");
                    }
                    else{
                        Log.i(RetPassword,password);
                        Toast.makeText(getApplicationContext(), "Incorrect Password !",
                                Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Incorrect UserId",
                            Toast.LENGTH_LONG).show();
                    Log.i("wrong","UserId "+username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
