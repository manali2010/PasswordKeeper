package com.example.user_pc.finalp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Update extends AppCompatActivity {
EditText ed1,ed2;
    Firebase ref,q1,q2;
    Button bn;
    ArrayList<Movie> movies=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        bn=(Button)findViewById(R.id.bn);
        Firebase.setAndroidContext(this);
        ref=new Firebase("https://masallah.firebaseio.com");
        q1=ref.child(MainActivity.s1);
        q2=q1.child(Applications.listItemName);
       bn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Movie m=new Movie();
               m.setName(Applications.listItemName);
               m.setuser(ed1.getText().toString());
               m.setpass(ed2.getText().toString());
               q2.setValue(m);
               ed1.setText(" ");
               ed2.setText(" ");
               Toast.makeText(Update.this, "Successfully updated", Toast.LENGTH_SHORT).show();
               Intent i=new Intent(Update.this,Applications.class);
               startActivity(i);
           }
       });


    }
    public void refreshdata()
    {
        q2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adddata(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public void adddata(DataSnapshot dataSnapshot)
    {
        movies.clear();
        for(DataSnapshot ds:dataSnapshot.getChildren()) {
            Movie m = new Movie();
            m.setuser(ds.getValue(Movie.class).getuser());
            m.setpass(ds.getValue(Movie.class).getpass());

        }
    }

}
