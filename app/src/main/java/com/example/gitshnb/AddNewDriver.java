package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewDriver extends AppCompatActivity {

    String BusNo;
    String password;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    EditText DriverBusNoEdittext;
    EditText DriverPasswordEdittext;
    Button butnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_driver);

        DriverBusNoEdittext = (EditText)findViewById(R.id.add_Driver_BusNo);
        DriverPasswordEdittext = (EditText)findViewById(R.id.add_Driver_password);
        butnSignUp = (Button)findViewById(R.id.butnSignF);




    }

    public void driverSignUp(View view)
    {
        getData();
        writeData();
    }

    public void getData()
    {
        BusNo = DriverBusNoEdittext.getText().toString();
        password = DriverPasswordEdittext.getText().toString();
                //  name = FirstName+" "+LastName;

    }

    public void writeData() {
        DatabaseReference reff2 = database.getReference().child("USERS").child(BusNo);
        reff2.child("PASSWORD").setValue(password);
        reff2.child("TYPE").setValue("driver");
    }
}
