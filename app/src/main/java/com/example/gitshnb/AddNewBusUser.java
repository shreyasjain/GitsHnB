package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNewBusUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bus_user);
    }

    public void newBusFacultyMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), AddNewBusFaculty.class);
        startActivity(i);
    }

    public void newBusStudentMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), AddNewBusStudent.class);
        startActivity(i);
    }

    public void newDriverMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), AddNewDriver.class);
        startActivity(i);
    }
}
