package com.example.user_pc.finalp;

import android.app.DownloadManager;
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
import com.firebase.client.Query;
import java.util.ArrayList;
public class Information extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button bn;
    Firebase ref,q,q1,q2,q3,p1,p2,p3;
    static String s1;
    Query q4;
    ArrayList<Movie> movies=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        ref=new Firebase("https://masallah.firebaseio.com");
        q1=new Firebase("https://masallah.firebaseio.com");
        q=ref.child("Data1");
        q2=q1.child(MainActivity.s1);
        p1=ref.child("Data1");
        q4=p1.orderByKey().equalTo(MainActivity.s1);
this.refreshdata();
        ed3=(EditText)findViewById(R.id.ed3);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        p2=p1.child(ed1.getText().toString());

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Information.this,Applications.class);
                startActivity(i);
            }
        });
        bn=(Button)findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=ed1.getText().toString();
                addData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString());
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
            }
        });

    }

    public void addData(String name,String user,String pass)
    {
        Movie m=new Movie();
        m.setName(name);
        m.setuser(user);
        m.setpass(pass);
            if(!(names.contains(name))) {
                q.child(MainActivity.s1).push().setValue(m);
                q2.child(s1).setValue(m);
            }else
                Toast.makeText(Information.this, "This application name already Exists", Toast.LENGTH_SHORT).show();

refreshdata();
    }
    public void refreshdata()
    {
q4.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        movies.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Movie m = new Movie();
            m.setName(ds.getValue(Movie.class).getName());
            names.add(m.getName());
            Toast.makeText(Information.this, m.getName(), Toast.LENGTH_SHORT).show();


        }
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



    }


