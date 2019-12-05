package com.cgi.travelexpertsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * AgentDB Class
 * Handles all the database connections to the Agents table. Was more of a test to work with SQLite
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 14, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
public class AgentDB {

    private SQLiteHelper helper;
    private SQLiteDatabase db;

    public AgentDB(Context context) {
        helper = new SQLiteHelper(context);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Agent getAgent(int agentId) {
        //Log.d("Agent ID", Integer.toString(agentId));
        Cursor cursor = db.query("agents", null, "agent_id="+agentId, null, null, null, null);

        cursor.moveToFirst();
        //Log.d("Agent before", cursor.getString(1));
        //Agent currAgent = new Agent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        Agent currAgent = new Agent();
        cursor.close();
        //Log.d("Agent stuff", Integer.toString(currAgent.getAgtId()) + currAgent.getAgtName());
        return currAgent;
    }

    public ArrayList<Agent> getAllAgents() {
        ArrayList<Agent> listOfAgents = new ArrayList<>();

        Cursor cursor = db.query("agents", null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //Agent currAgt = new Agent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            Agent currAgt = new Agent();
            listOfAgents.add(currAgt);
            cursor.moveToNext();
        }
        cursor.close();

        return listOfAgents;
    }

}
