package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomEListAdapter extends ArrayAdapter<Expenses> {

    private static final String TAG = "CustomListAdapter";

    Context mContext;
    int mResource;

    public CustomEListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Expenses> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int Tid= getItem(position).getEid();
        String TName= getItem(position).getEType();
        String TidString= String.valueOf(Tid);

        Expenses trip= new Expenses(Tid,TName);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tid=(TextView) convertView.findViewById(R.id.textViewTid);
        TextView tname=(TextView) convertView.findViewById(R.id.textViewTName);

        tname.setText(TName);
        tid.setText(TidString);

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}

