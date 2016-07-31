package com.example.user_pc.finalp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user-pc on 29-06-2016.
 */
public class MyHolder extends RecyclerView.ViewHolder {
    TextView textView,textView2,textView3;
    public MyHolder(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.textView);
        textView2=(TextView)itemView.findViewById(R.id.textView2);
        textView3=(TextView)itemView.findViewById(R.id.textView3);

    }
}
