package com.cgi.travelexpertsapp;

/**
 * MainActivity
 * The main UI display for the Travel Experts app
 *
 * Author: Ivan Ng
 * Date Created: September 17, 2016
 * Last Updated: September 28, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> testArrayList;
    private ArrayList<Agent> agencyAgentsList;
    private String suchIP = "10.163.101.104";
    private StringBuilder builder;
    private Integer agencyNumber;
    private String agencyCity;
    private Integer curAgencyId;

    private static ArrayList<Agency> agencyList;
    private ArrayList<Agent> curAgencyAgents;
    //private StringBuilder builderAgents;
    private Integer curAgencyIndex;

    private static AgentsAdapter adapter;
    //private static ListView agentsList;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetAgencies().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cgi.travelexpertsapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cgi.travelexpertsapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnMapReadyCallback {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_TITLE = "section_title";
        //private ArrayList<String> addresses = new ArrayList<String>();
        private String curIndexAddress;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();

            int indexNumber = sectionNumber - 1;

            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt("current_index", indexNumber);
            //args.putString(ARG_SECTION_TITLE, (agencyList.get(indexNumber)).toString());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            int curIndex = getArguments().getInt("current_index");
            final int aFinalIndex = getArguments().getInt("current_index");
            Log.d("OnCreateView", curIndex + "");

            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            TextView agencyView = (TextView) rootView.findViewById(R.id.tvAgencyInfo);
            ListView agentsList = (ListView) rootView.findViewById(R.id.lvAgents);

            agentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Agent agentToSend = (agencyList.get(aFinalIndex)).getAgentAtIndex(i);

                    Intent intent = new Intent(getActivity().getApplicationContext(), AgentActivity.class);

                    intent.putExtra("agtName", agentToSend.getAgtName());
                    intent.putExtra("agtPosition", agentToSend.getAgtPosition());
                    intent.putExtra("agtBusPhone", agentToSend.getAgtBusPhone());
                    intent.putExtra("agtEmail", agentToSend.getAgtEmail());

                    startActivity(intent);
                }
            });

            // Google Map
            Log.d("onCreateView", "Before MapFrag");
            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            Log.d("onCreateView", "After MapFrag");

            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)) + "\n" +
                    "Hello there " + agencyList.get(curIndex).toString());

            int numOfAgents;
            if ((agencyList.get(curIndex).getAgencyAgents()) == null) {
                numOfAgents = 0;
            } else {
                ArrayList<Agent> testAgentList = agencyList.get(curIndex).getAgencyAgents();
                numOfAgents = testAgentList.size();
            }
            Log.d("OnCreateView", "Populating the agencyView with " + numOfAgents);
            agencyView.setText(agencyList.get(curIndex).printAgencyAddress() + "\n" +
                    numOfAgents);

            // Load the list

            if (agencyList.get(curIndex).getAgencyAgents() == null) {
                agencyList.get(curIndex).setAgencyAgents(new ArrayList<Agent>());
            }

            adapter = new AgentsAdapter(getActivity().getApplicationContext(), (agencyList.get(curIndex)).getAgencyAgents());
            agentsList.setAdapter(adapter);

            curIndexAddress = agencyList.get(curIndex).printAgencyAddress();
            textView.setText(curIndexAddress);

            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d("onViewCreated", "Created yo");
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            Log.d("Hi", "Hello");
            List<Address> result;
            double latitude = 0;
            double longitude = 0;
            // Address thing
            try {
                result = new Geocoder(getActivity().getApplicationContext()).getFromLocationName(curIndexAddress, 1);
                if (result.size() == 0) {
                    // Defaults to SAIT
                    latitude = 51.0658;
                    longitude = -114.0898;
                } else {
                    //Log.d("Result", result.toString());
                    latitude = result.get(0).getLatitude();
                    longitude = result.get(0).getLongitude();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Marker newMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
            //Marker newMarker = googleMap.addMarker(new MarkerOptions().position(new LatLng(51.0658, -114.0898)).title("SAIT"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(14.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.moveCamera(cameraUpdate);
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            return agencyList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return (agencyList.get(position)).toString();
        }
    }

    public class GetAgencies extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://" + suchIP + ":8080/TravelExpertsWebService/rs/travelexpertsREST/getagencies");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                builder = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    Log.d("Line read", "Hello");
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
            testArrayList = new ArrayList<Integer>();
            agencyList = new ArrayList<Agency>();

            try {
                Log.d("Builder", builder.toString());
                JSONArray jsonArray = new JSONArray(builder.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int agencyId = jsonObject.getInt("AgencyId");
                    String agencyAdd = jsonObject.getString("AgncyAddress");
                    String agencyCity = jsonObject.getString("AgncyCity");
                    String agencyProv = jsonObject.getString("AgncyProv");
                    String agencyPostal = jsonObject.getString("AgncyPostal");
                    String agencyCountry = jsonObject.getString("AgncyCountry");
                    String agencyPhone = jsonObject.getString("AgncyPhone");
                    String agencyFax = jsonObject.getString("AgncyFax");

                    Agency tempAgency = new Agency(agencyId, agencyAdd, agencyCity, agencyProv, agencyPostal, agencyCountry, agencyPhone, agencyFax);
                    curAgencyId = tempAgency.getAgencyId();
                    Log.d("Blah", "Getting URL index " + i);
                    curAgencyIndex = i;
                    Log.d("GetAgencies", "Passing in " + curAgencyId + " " + curAgencyIndex + " into GetAgencyAgents2");
                    ArrayList<Agent> themAgents = new GetAgencyAgents2(curAgencyId, curAgencyIndex).execute().get();
                    tempAgency.setAgencyAgents(themAgents);
                    agencyList.add(tempAgency);
                }

                builder.delete(0, builder.length());

                // Create the adapter that will return a fragment for each of the three
                // primary sections of the activity.
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(mViewPager);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    // Ignore this
    /*
    public class GetAgencyAgents extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://" + suchIP + ":8080/TravelExpertsWebService/rs/travelexpertsREST/getagents/" + curAgencyId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                builderAgents = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    builderAgents.append(line);
                }

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
            curAgencyAgents = new ArrayList<Agent>();

            try {
                JSONArray jsonArray = new JSONArray(builderAgents.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Agent tempAgent = new Agent();

                    tempAgent.setAgtId(jsonObject.getInt("AgentId"));
                    tempAgent.setAgtFName(jsonObject.getString("AgtFirstName"));

                    /*
                    if (jsonObject.getString("AgtMiddleInitial") == null || jsonObject.getString("AgtMiddleInitial").equals("")) {
                        tempAgent.setAgtMInitial("");
                    } else {
                        tempAgent.setAgtMInitial(jsonObject.getString("AgtMiddieInitial"));
                    }
                    */
    /*
                    tempAgent.setAgtMInitial("NA");
                    tempAgent.setAgtLName(jsonObject.getString("AgtLastName"));
                    tempAgent.setAgtBusPhone(jsonObject.getString("AgtBusPhone"));
                    tempAgent.setAgtEmail(jsonObject.getString("AgtEmail"));
                    tempAgent.setAgtPosition(jsonObject.getString("AgtPosition"));
                    tempAgent.setAgtAgencyId(jsonObject.getInt("AgencyId"));

                    curAgencyAgents.add(tempAgent);
                }

                builderAgents.delete(0, builderAgents.length());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    */
    public class GetAgencyAgents2 extends AsyncTask<Void, Void, ArrayList<Agent>> {

        int curAgencyId2;
        int curAgencyIndex2;

        protected GetAgencyAgents2(int curAgencyId2, int curAgencyIndex2) {
            this.curAgencyId2 = curAgencyId2;
            this.curAgencyIndex2 = curAgencyIndex2;
        }

        @Override
        protected ArrayList<Agent> doInBackground(Void... voids) {
            StringBuilder builderAgents = new StringBuilder();

            try {
                Log.d("GetAgencyAgents2", "Getting URL connection with " + curAgencyId2 + ", index " + curAgencyIndex2);
                URL url = new URL("http://" + suchIP + ":8080/TravelExpertsWebService/rs/travelexpertsREST/getagents/" + curAgencyId2);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

                while ((line = br.readLine()) != null) {
                    builderAgents.append(line);
                }

                Log.d("BG GetAgencyAgents2", builderAgents.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            curAgencyAgents = new ArrayList<Agent>();

            try {
                Log.d("GetAgencyAgents2", "Reading JSON Array for " + curAgencyId2);
                Log.d("GetAgencyAgents2", curAgencyId2 + "'s agentsBuilder is " + builderAgents.toString());
                JSONArray jsonArray = new JSONArray(builderAgents.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Agent tempAgent = new Agent();

                    tempAgent.setAgtId(jsonObject.getInt("AgentId"));
                    tempAgent.setAgtFName(jsonObject.getString("AgtFirstName"));
                    tempAgent.setAgtMInitial("NA");
                    tempAgent.setAgtLName(jsonObject.getString("AgtLastName"));
                    tempAgent.setAgtBusPhone(jsonObject.getString("AgtBusPhone"));
                    tempAgent.setAgtEmail(jsonObject.getString("AgtEmail"));
                    tempAgent.setAgtPosition(jsonObject.getString("AgtPosition"));
                    tempAgent.setAgtAgencyId(jsonObject.getInt("AgencyId"));

                    curAgencyAgents.add(tempAgent);
                }
                Log.d("Hello", "GetAgencyAgents2 Yo " + curAgencyId2 + curAgencyAgents.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return curAgencyAgents;
        }
        // Old code that can still be useful; keeping it for now
        /*
        @Override
        protected void onPostExecute(String builderAgents) {
            super.onPostExecute(builderAgents);
            curAgencyAgents = new ArrayList<Agent>();

            try {
                Log.d("GetAgencyAgents2", "Reading JSON Array for " + curAgencyId2);
                Log.d("GetAgencyAgents2", curAgencyId2 + "'s agentsBuilder is " + builderAgents.toString());
                JSONArray jsonArray = new JSONArray(builderAgents.toString());
                //Log.d("GetAgencyAgents2", "Reading JSON Array");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Agent tempAgent = new Agent();

                    tempAgent.setAgtId(jsonObject.getInt("AgentId"));
                    tempAgent.setAgtFName(jsonObject.getString("AgtFirstName"));

                    /*
                    if (jsonObject.getString("AgtMiddleInitial") == null || jsonObject.getString("AgtMiddleInitial").equals("")) {
                        tempAgent.setAgtMInitial("");
                    } else {
                        tempAgent.setAgtMInitial(jsonObject.getString("AgtMiddieInitial"));
                    }
                    */ /*
                    tempAgent.setAgtMInitial("NA");
                    tempAgent.setAgtLName(jsonObject.getString("AgtLastName"));
                    tempAgent.setAgtBusPhone(jsonObject.getString("AgtBusPhone"));
                    tempAgent.setAgtEmail(jsonObject.getString("AgtEmail"));
                    tempAgent.setAgtPosition(jsonObject.getString("AgtPosition"));
                    tempAgent.setAgtAgencyId(jsonObject.getInt("AgencyId"));

                    curAgencyAgents.add(tempAgent);
                }
                Log.d("Hello", "GetAgencyAgents2 Yo " + curAgencyId2 + curAgencyAgents.size());
                agencyList.get(curAgencyIndex2).setAgencyAgents(curAgencyAgents);
                //Log.d("Hello", "I am at adapter in GetAgents2");
                //adapter.notifyDataSetChanged();
                //adapter = new AgentsAdapter(getApplicationContext(), (agencyList.get(curAgencyIndex2)).getAgencyAgents());
                //agentsList.setAdapter(adapter);

                Log.d("Checking", (agencyList.get(0).getAgencyAgents().size()) + " " + (agencyList.get(1).getAgencyAgents().size()));

                //builderAgents.delete(0, builderAgents.length());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } */
    }
}

