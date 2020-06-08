package com.example.gitshnb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class studentComplaintList extends AppCompatActivity {

    FirebaseDatabase database=FirebaseDatabase.getInstance();


    private ListView complaintListView;

    CustomAdapter customAdapter = new CustomAdapter();
    ArrayList<String> arrayListUserId = new ArrayList<>();
    ArrayList<String> arrayListUsername = new ArrayList<>();
    ArrayList<String> arrayListHostel = new ArrayList<>();
    ArrayList<String> arrayListRoom = new ArrayList<>();
    ArrayList<String> arrayListComplaintText = new ArrayList<>();
    ArrayAdapter<String> adapter;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complaint_list);



        complaintListView = (ListView)findViewById(R.id.complaintListview);
        dataRet();
















    }

    int i=0;
    public void dataRet()
    {
        arrayListComplaintText.clear();
        arrayListRoom.clear();
        arrayListHostel.clear();
        arrayListUserId.clear();
        DatabaseReference refComplaint = database.getReference().child("COMPLAINTS").child("HOSTEL");
        refComplaint.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                count = (int)dataSnapshot.getChildrenCount();
                arrayListUserId.add(dataSnapshot.getKey());

                arrayListComplaintText.add(dataSnapshot.getValue().toString());


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refComplaint.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count =(int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        new CountDownTimer(8000,1000)
        {
            @Override
            public void onFinish() {
                Log.i("count",String.valueOf(count));
                CustomAdapter customAdapter = new CustomAdapter();

                complaintListView.setAdapter(customAdapter);

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //return arrayListComplaintText.size();
            return count;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.my_list_complaints,null);

            TextView textviewUsername = (TextView)convertView.findViewById(R.id.Username);
            //TextView textviewHostelNo = (TextView)convertView.findViewById(R.id.HostelNo);
           // TextView textviewRoomlNo = (TextView)convertView.findViewById(R.id.RoomNo);
            TextView textviewComplaintText = (TextView)convertView.findViewById(R.id.complaintText);
            Button butnDeleteComplaint = (Button)convertView.findViewById(R.id.btn_delete_complaint);

            textviewUsername.setText(arrayListUserId.get(position));
            //textviewHostelNo.setText(arrayListHostel.get(position));
            //textviewRoomlNo.setText(arrayListRoom.get(position));
            textviewComplaintText.setText(arrayListComplaintText.get(position));

            return convertView;
        }
    }
}

