package com.example.gitshnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Add_new_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
    }

    public void newStudentMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), Add_new_student.class);
        startActivity(i);
    }

    public void newFacultyMethod(View view)
    {
        Intent i = new Intent(getApplicationContext(), Add_new_faculty.class);
        startActivity(i);
    }


}
