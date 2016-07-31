package com.example.user_pc.finalp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Map;

public class Show extends AppCompatActivity {
    MyAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();
    Firebase ref,q;
    Query q1;
    RecyclerView mRecyclerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        ref=new Firebase("https://masallah.firebaseio.com");
        q=ref.child(MainActivity.s1);
        mRecyclerId=(RecyclerView)findViewById(R.id.mRecyclerId);
        mRecyclerId.setLayoutManager(new LinearLayoutManager(this));
        q1=q.orderByChild("name").equalTo(Applications.s3);
        q1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Movie m = new Movie();
                Map<String, Object> new1 = (Map<String, Object>) dataSnapshot.getValue();
                String s1 = String.valueOf(new1.get("name"));
                String s2 = String.valueOf(new1.get("user"));
                String s3 = String.valueOf(new1.get("pass"));
                m.setName("Application Name:  "+s1);
                m.setuser("Username        :  "+s2);
                m.setpass("Password        :  "+s3);
                movies.add(m);
                //updatedata(dataSnapshot);
                if (movies.size() > 0) {
                    adapter = new MyAdapter(Show.this, movies);

                    mRecyclerId.setAdapter(adapter);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(Show.this,MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
