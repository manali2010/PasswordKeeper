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
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText ed, ed1;
    Button bn;
    String s8;
    Firebase ref, q, q1,q2;
    Query q3;
    static String s1, s2;
     ArrayList<String> username = new ArrayList<>();
     ArrayList<String> password = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://masallah.firebaseio.com");
        q = ref.child("Register1");
        q1=ref.child("registered");
        q2=q1.child("group1");
        q3=q1.orderByKey().equalTo("group1");
        ed = (EditText) findViewById(R.id.ed);
        ed1 = (EditText) findViewById(R.id.ed1);
        bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s8 = ed.getText().toString();
                if (ed.getText().toString().length() < 4 && ed1.getText().toString().length() < 5) {
                    ed.setError("Insufficient length");
                    ed1.setError("Short password");
                } else {
                    adddata(ed.getText().toString(), ed1.getText().toString());

                }
            }
        });
        this.refreshdata();


    }

    public void refreshdata()
    {
        q3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                movies.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Movie m = new Movie();
                    m.setName(ds.getValue(Movie.class).getName());
                    username.add(m.getName());
                    Toast.makeText(Register.this, m.getName(), Toast.LENGTH_SHORT).show();
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





    public void adddata(String email, String password) {
Movie m=new Movie();
        Addinfo a = new Addinfo();
        a.setname(email);
        m.setName(email);
        a.setpass(password);
        if(!(username.contains(email))) {
            q.child(email).setValue(a);
            q2.push().setValue(m);
            s1 = ed.getText().toString();
            s2 = ed1.getText().toString();
            ed.setText("");
            ed1.setText("");
            Intent i = new Intent(Register.this, MainActivity.class);
            startActivity(i);
        }else
            Toast.makeText(Register.this, "user already exists", Toast.LENGTH_SHORT).show();
        refreshdata();



        }



    }


