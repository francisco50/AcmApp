package com.egcoding.acmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventListAdapter extends BaseAdapter {

    Context context;
    ArrayList<CustomListAdapter> customListAdapters;

    public EventListAdapter(Context context, ArrayList<CustomListAdapter> customListAdapters) {
        this.context = context;
        this.customListAdapters = customListAdapters;
    }

    @Override
    public int getCount() {

        return customListAdapters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_list_layout, parent, false);

        TextView title = view.findViewById(R.id.eventName);
        TextView time = view.findViewById(R.id.eventTime);
        TextView description = view.findViewById(R.id.eventDescription);
        TextView location = view.findViewById(R.id.eventLocation);

        title.setText(customListAdapters.get(position).getTitle());
        time.setText(customListAdapters.get(position).getTime());
        description.setText(customListAdapters.get(position).getDescription());
        location.setText(customListAdapters.get(position).getLocation());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
