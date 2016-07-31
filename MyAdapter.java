package com.example.user_pc.finalp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by user-pc on 29-06-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Movie> movies;
    public MyAdapter(Context c,ArrayList<Movie> movies)
    {
        this.c=c;
        this.movies=movies;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String name=movies.get(position).getName();
        holder.textView.setText(name);
        String user=movies.get(position).getuser();
        holder.textView2.setText(user);
        String pass=movies.get(position).getpass();
        holder.textView3.setText(pass);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
