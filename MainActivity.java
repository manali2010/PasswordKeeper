package com.example.user_pc.finalp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button bn1,bn2,bn3;
    CheckBox cb;
    Firebase ref,q;
    Query q1;
    static String s1;
    static ArrayList<String> username=new ArrayList<>();
    static ArrayList<String> password=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        bn1=(Button)findViewById(R.id.bn1);
        bn2=(Button)findViewById(R.id.bn2);
        username.add(ed1.getText().toString());

        Firebase.setAndroidContext(this);
        ref=new Firebase("https://masallah.firebaseio.com");
        q=ref.child("Register1");
        cb=(CheckBox)findViewById(R.id.cb);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    ed2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    ed2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        ed1.setText(Register.s1);
        ed2.setText(Register.s2);

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=ed1.getText().toString();
                q1=q.orderByChild("name").equalTo(ed1.getText().toString());
                q1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String, Object> new1 = (Map<String, Object>) dataSnapshot.getValue();
                        String s3 = String.valueOf(new1.get("pass"));
                        String s4=String.valueOf(new1.get("name"));
                        if (s3.equals(ed2.getText().toString())) {

                            Intent i = new Intent(MainActivity.this, Information.class);
                            startActivity(i);
                            ed1.setText("");
                            ed2.setText("");
                        }
                        else
                            Toast.makeText(MainActivity.this, "Register", Toast.LENGTH_LONG).show();




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
        });



    }


}
