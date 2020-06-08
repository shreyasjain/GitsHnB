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
import java.util.List;

public class StudentDetail extends AppCompatActivity implements UsernameDialog.UsernameDialogListner {

    FirebaseDatabase database=FirebaseDatabase.getInstance();

    ArrayList<String> arrayListUserId = new ArrayList<>();
    ArrayList<String> arrayListUsername = new ArrayList<>();
    ArrayList<String> arrayListHostel = new ArrayList<>();
    ArrayList<String> arrayListRoom = new ArrayList<>();
    ArrayList<String> arrayListName = new ArrayList<>();
    ArrayList<String> arrayListContact = new ArrayList<>();

    ArrayAdapter<String> adapter;

    int count = 0;


    String searchText;
    private Button searchUsernameButn;

    private ListView studentDetailListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);



        searchUsernameButn = (Button)findViewById(R.id.usernameSearch);

        studentDetailListview = (ListView)findViewById(R.id.studentDetailListview);

        //dataRet();

        searchUsernameButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUsernameDialog();
            }
        });



    }

    public void openUsernameDialog(){
        UsernameDialog usernameDialog = new UsernameDialog();
        usernameDialog.show(getSupportFragmentManager(),"Username Search");

    }



    public void dataRet()
    {
        arrayListName.clear();
        arrayListRoom.clear();
        arrayListHostel.clear();
        arrayListUserId.clear();
        arrayListContact.clear();
        final DatabaseReference refComplaint = database.getReference().child("DETAILS").child("STUDENT").child("HOSTEL");
        refComplaint.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //count = (int)dataSnapshot.getChildrenCount();
                final String letusername = dataSnapshot.getKey();
                //arrayListUsername.add(letusername);
                final DatabaseReference ref = database.getReference().child("DETAILS").child("STUDENT").child("HOSTEL").child(letusername);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name = (String)dataSnapshot.child("Name").getValue();
                        String contact=(String)dataSnapshot.child("CONTACT").getValue();
                        String hostel = (String)dataSnapshot.child("HOSTEL").getValue();
                        String room = (String)dataSnapshot.child("ROOM").getValue();

                        Log.i(letusername,name+"nnamme");
                        if(name.equals(searchText) || letusername.equals(searchText))
                        {
                            count++;
                            arrayListUsername.add(letusername);
                            arrayListName.add(name);
                            arrayListContact.add(contact);
                            arrayListRoom.add(room);
                            arrayListHostel.add(hostel);
                        }
                        arrayListContact.add(contact);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






                //String letUsername = dataSnapshot.getKey();
                /*Log.i(letUsername,"Retrieved Username");
                String name = (String)dataSnapshot.child(letUsername).child("Name").getValue();
                Log.i(name,"Rnname");*/


               /* if(letUsername==searchText || name==searchText)
                {
                    count++;*/


                    /*arrayListName.add(dataSnapshot.child(letUsername).child("Name").getValue().toString());

                    arrayListRoom.add(dataSnapshot.child(letUsername).child("ROOM").getValue().toString());
                    arrayListRoom.add(dataSnapshot.child(letUsername).child("HOSTEL").getValue().toString());
                    arrayListContact.add(dataSnapshot.child(letUsername).child("CONTACT").getValue().toString());*/
                //}





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
                //count =(int) dataSnapshot.getChildrenCount();
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

                studentDetailListview.setAdapter(customAdapter);

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    @Override
    public void applyTexts(String user) {
        searchText = user;
        dataRet();
        Log.i(searchText,"username");
    }

    class CustomAdapter extends BaseAdapter {

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
            convertView = getLayoutInflater().inflate(R.layout.student_details_list,null);

            TextView textviewUsername = (TextView)convertView.findViewById(R.id.detailsUsername);


            TextView textviewHostelNo = (TextView)convertView.findViewById(R.id.detailsHostel);
             TextView textviewRoomlNo = (TextView)convertView.findViewById(R.id.detailsRoom);
            TextView textviewName = (TextView)convertView.findViewById(R.id.detailsName);
            TextView textviewContact = (TextView)convertView.findViewById(R.id.detailsContact);


            textviewUsername.setText(arrayListUsername.get(position));
           textviewHostelNo.setText(arrayListHostel.get(position));
            textviewRoomlNo.setText(arrayListRoom.get(position));
            textviewName.setText(arrayListName.get(position));
            textviewContact.setText(arrayListContact.get(position));

            return convertView;
        }
    }
}
