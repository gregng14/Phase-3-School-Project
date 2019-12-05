package com.cgi.travelexpertsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * AgentsAdapter
 * Enables the program to convert a list of Agents into the listview's items
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 14, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 * Created by 723243 on 9/14/2016.
 */
public class AgentsAdapter extends ArrayAdapter<Agent> {

    public AgentsAdapter(Context context, ArrayList<Agent> agents) {
        super(context, 0, agents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Agent agent = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.agents_item, parent, false);
        }
        // Loopup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvAgentName);
        TextView tvPosition = (TextView) convertView.findViewById(R.id.tvPosition);
        // Popuilate the data into the template view using the data object
        tvName.setText(agent.getAgtName());
        tvPosition.setText(agent.getAgtPosition());
        // Return the completed view to render on screen
        return convertView;
    }
}

