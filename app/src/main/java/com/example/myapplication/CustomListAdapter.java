package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Trip> implements Filterable {
    private static final String TAG = "CustomListAdapter";
    ArrayList<Trip> mTrip=new ArrayList<Trip>();
    ArrayList<Trip> mList=new ArrayList<Trip>();

    Context mContext;
    int mResource;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Trip> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
        mTrip=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int Tid= getItem(position).getTid();
        String TName= getItem(position).getTname();
        String TidString= String.valueOf(Tid);

        Trip trip= new Trip(Tid,TName);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tid=(TextView) convertView.findViewById(R.id.textViewTid);
        TextView tname=(TextView) convertView.findViewById(R.id.textViewTName);

        tname.setText(TName);
        tid.setText(TidString);

        return convertView;
        //return super.getView(position, convertView, parent);
    }

    public ArrayList<Trip> customFilter(String newText){
        ArrayList<Trip> filteredTitle = new ArrayList<Trip>();

        // We'll go through all the title and see
        // if they contain the supplied string
        for (Trip c : mTrip) {
            if (c.getTname().toUpperCase().contains( newText.toString().toUpperCase() )) {
                // if `contains` == true then add it
                // to our filtered list
                filteredTitle.add(c);
            }
        }

        return filteredTitle;
    }

}
