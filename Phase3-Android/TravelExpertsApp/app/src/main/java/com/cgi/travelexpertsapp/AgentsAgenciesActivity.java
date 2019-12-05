package com.cgi.travelexpertsapp;

/*
 * AgentsAgenciesActivity
 * The main list area for the agents and agencies... Still a WIP
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 19, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AgentsAgenciesActivity extends AppCompatActivity {

    // Put the IP for where your Tomcat server is
    private String suchIP = "YourIPHere";
    private Spinner theSpinner;
    private ListView theListView;
    private TextView theTextView;
    private AgencyDB agencyData;
    private AgentDB agentData;
    private StringBuilder builder = new StringBuilder();
    private Integer agencyNumber;
    private String agencyCity;
    private ArrayList<Integer> testArrayList;
    private ArrayList<Agent> testAgentList;
    ArrayAdapter<Integer> spinAdapter;
    private Integer selectedAgency;
    private String agtName;
    private String agtPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents_agencies);

        // Random
        //TextView theTextView = (TextView) findViewById(R.id.TextView);
        theSpinner = (Spinner) findViewById(R.id.spinner);
        theListView = (ListView) findViewById(R.id.listView);
        theTextView = (TextView) findViewById(R.id.textView);

        theSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Item clicked", Integer.toString(i));
                Log.d("Item clicked", (theSpinner.getSelectedItem()).toString());
                selectedAgency = (Integer)theSpinner.getSelectedItem();
                new GetAgents().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Agent agentToSend = testAgentList.get(i);

                Intent intent = new Intent(getApplicationContext(), AgentActivity.class);

                intent.putExtra("agtName", agentToSend.getAgtName());
                intent.putExtra("agtPosition", agentToSend.getAgtPosition());
                intent.putExtra("agtBusPhone", agentToSend.getAgtBusPhone());
                intent.putExtra("agtEmail", agentToSend.getAgtEmail());

                startActivity(intent);
            }
        });

        // Test lists for stuff
        //ArrayList<Integer> testArrayList = new ArrayList<Integer>();
        ArrayList<Agent> testAgentList = new ArrayList<Agent>();

        // Database for SQLite
        agencyData = new AgencyDB(this);
        agencyData.open();
        agentData = new AgentDB(this);
        agentData.open();

        // Populating the test lists
        //testArrayList = agencyData.getAllAgencies();
        //testAgentList = agentData.getAllAgents();

        /*
        // Adapter population (Spinner) (SQLite Database)
        ArrayAdapter<Integer> spinAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, testArrayList);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theSpinner.setAdapter(spinAdapter);
        */
        /*
        // Adapter population (List view)
        AgentsAdapter adapter = new AgentsAdapter(this, testAgentList);
        theListView.setAdapter(adapter);
        */

        /*
        if (testArrayList.size() > 0) {
            Toast.makeText(this, "ArrayList has more than 0 items yo", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "ArrayList is empty yo", Toast.LENGTH_LONG).show();
        } */

        new GetAgencies().execute();
    }

    class GetAgencies extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://" + suchIP + ":8080/TravelExpertsWebService/rs/travelexpertsREST/getagencies");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = br.readLine()) != null) {
                    //Log.d("Line read", "Hello");
                    builder.append(line);
                }
                //Log.d("Line wasn't read", "Hello");
                //Toast.makeText(getApplicationContext(), builder.toString(), Toast.LENGTH_LONG).show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            StringBuilder outputString = new StringBuilder();
            testArrayList = new ArrayList<Integer>();

            try {
                //JSONObject obj = new JSONObject(builder.toString());
                JSONArray jsonArray = new JSONArray(builder.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    agencyNumber = jsonObject.getInt("AgencyId");
                    //theTextView.setText(agencyNumber.toString());
                    agencyCity = jsonObject.getString("AgncyCity");
                    outputString.append(agencyNumber + " - " + agencyCity + "\n");
                    //theTextView.setText(jsonObject.getInt("AgencyId"));
                    //int tempInteger = Integer.parseInt(agencyNumber);
                    //Toast.makeText(getApplicationContext(), tempInteger, Toast.LENGTH_LONG).show();
                    testArrayList.add(agencyNumber);
                    //Log.d("testArrayList", testArrayList.get(i).toString());
                }
                theTextView.setText(outputString.toString());
                //theTextView.setText(obj.toString());

                // Load the spinner with REST web service JSON call
                spinAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item, testArrayList);
                spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                theSpinner.setAdapter(spinAdapter);

                builder.delete(0, builder.length());
                outputString.delete(0, outputString.length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class GetAgents extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://" + suchIP + ":8080/TravelExpertsWebService/rs/travelexpertsREST/getagents/" + selectedAgency);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = br.readLine()) != null) {
                    //Log.d("Line read", "Hello");
                    builder.append(line);
                }
                //Log.d("Line wasn't read", "Hello");
                //Toast.makeText(getApplicationContext(), builder.toString(), Toast.LENGTH_LONG).show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            StringBuilder outputString = new StringBuilder();
            testAgentList = new ArrayList<Agent>();

            try {
                //JSONObject obj = new JSONObject(builder.toString());
                JSONArray jsonArray = new JSONArray(builder.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    Agent tempAgent = new Agent();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //tempAgent.setAgtName(jsonObject.getString("AgtFirstName") + " " + jsonObject.getString("AgtLastName"));
                    tempAgent.setAgtPosition(jsonObject.getString("AgtPosition"));
                    tempAgent.setAgtBusPhone(jsonObject.getString("AgtBusPhone"));
                    tempAgent.setAgtEmail(jsonObject.getString("AgtEmail"));
                    //outputString.append(agtName + " - " + agtPosition + "\n");
                    //theTextView.setText(jsonObject.getInt("AgencyId"));
                    //int tempInteger = Integer.parseInt(agencyNumber);
                    //Toast.makeText(getApplicationContext(), tempInteger, Toast.LENGTH_LONG).show();
                    testAgentList.add(tempAgent);
                    //Log.d("testArrayList", testArrayList.get(i).toString());
                }
                //theTextView.setText(outputString.toString());
                //theTextView.setText(obj.toString());

                // Load the list
                AgentsAdapter adapter = new AgentsAdapter(getApplicationContext(), testAgentList);
                theListView.setAdapter(adapter);

                builder.delete(0, builder.length());
                outputString.delete(0, outputString.length());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
