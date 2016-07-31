package com.example.user_pc.finalp;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

public class Applications extends AppCompatActivity {
    static String s3,listItemName;
    Firebase ref,q,q2,q4,q5,q6;
    Query q1,q3;
    ListView lv;
    FloatingActionButton fab;
    ArrayList<Movie> movies=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    String s;

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getTitle()=="Update")
        {
            Object[] a1=names.toArray();
            Object a2=a1[info.position];
            listItemName=a2.toString();
            Toast.makeText(Applications.this, listItemName, Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Applications.this,Update.class);
            startActivity(i);

        }
        if(item.getTitle()=="Delete")
        {
            Object[] a1=names.toArray();
            Object a2=a1[info.position];
            listItemName=a2.toString();
            s=listItemName;
            q2=ref.child(MainActivity.s1);
            q4=q2.child(listItemName);
            q2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                    removedata();




                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            AlertDialog.Builder adbuilder=new AlertDialog.Builder(this);
            adbuilder.setMessage("Are u really want to delete this?");
            adbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

            q4.removeValue();

                }
            });
            AlertDialog dialog=adbuilder.create();
            dialog.show();



        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"Update");
        menu.add(0,v.getId(),0,"Delete");


    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.list);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Applications.this,Information.class);
                startActivity(i);
            }
        });
        registerForContextMenu(lv);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://masallah.firebaseio.com");
        q = ref.child("Data1");
        q1 = q.orderByKey().equalTo(MainActivity.s1);
        this.refreshdata();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object[] s1 = names.toArray();
                Object s2 = s1[position];
                s3 = s2.toString();
                Intent i = new Intent(Applications.this, Show.class);
                startActivity(i);
            }
        });
    }
    public void removedata()
    {
        q5=ref.child("Data1").child(MainActivity.s1);
        q3=q5.orderByChild("name").equalTo(s);

        q3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                movies.clear();
               ref.child("Data1").child(MainActivity.s1).child(dataSnapshot.getKey()).removeValue();
              //  Toast.makeText(Applications.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                names.remove(s);
                Intent i=new Intent(Applications.this,Information.class);
                startActivity(i);


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
public void refreshdata()
{
  q1.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
          getupdates(dataSnapshot);
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
    private void getupdates(DataSnapshot dataSnapshot)
    {
        movies.clear();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            Movie m=new Movie();
            m.setName(ds.getValue(Movie.class).getName());

                    names.add(m.getName());



        }
        if(names.size()>0)
        {
            ArrayAdapter adapter=new ArrayAdapter(Applications.this,android.R.layout.simple_list_item_1,names);
            lv.setAdapter(adapter);
        }
    }

}
