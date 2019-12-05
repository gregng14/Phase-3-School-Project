package com.cgi.travelexpertsapp;

/*
 * Agent Activity
 * Displays the additional information for the agent that the user selected
 *
 * Author: Ivan Ng
 * Date Created: September 19, 2016
 * Last Updated: September 19, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AgentActivity extends AppCompatActivity {

    private TextView tvAgtName;
    private TextView tvAgtPosition;
    private TextView tvAgtBusPhone;
    private TextView tvAgtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);

        // Defining the views to display stuff on
        tvAgtName = (TextView) findViewById(R.id.tvAgtName);
        tvAgtPosition = (TextView) findViewById(R.id.tvPosition);
        tvAgtBusPhone = (TextView) findViewById(R.id.tvAgtBusPhone);
        tvAgtEmail = (TextView) findViewById(R.id.tvAgtEmail);

        Intent intentPassedIn = getIntent();

        String agtName = intentPassedIn.getStringExtra("agtName");
        String agtPosition = intentPassedIn.getStringExtra("agtPosition");
        String agtBusPhone = intentPassedIn.getStringExtra("agtBusPhone");
        String agtEmail = intentPassedIn.getStringExtra("agtEmail");

        tvAgtName.setText(agtName);
        tvAgtPosition.setText(agtPosition);
        tvAgtBusPhone.setText(agtBusPhone);
        tvAgtEmail.setText(agtEmail);

    }
}
