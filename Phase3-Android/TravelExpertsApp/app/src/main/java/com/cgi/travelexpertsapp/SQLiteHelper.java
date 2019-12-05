package com.cgi.travelexpertsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteHelper class
 * Handles the table creation for the agents table. Was for testing purposes
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 14, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "agentsqlite.db";
    private static final int DB_VERSION = 5;

    private Context context;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase agentDatabase) {
        String sql = "CREATE TABLE agents (" +
                     "agent_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "agent_name TEXT NOT NULL UNIQUE, " +
                     "agent_phone TEXT NOT NULL UNIQUE, " +
                     "agent_email TEXT NOT NULL UNIQUE, " +
                     "agent_position TEXT);";
        agentDatabase.execSQL(sql);

        String agent1 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                        "VALUES ('Mario Plumber', '4031234567', 'mario@te.ca', 'Manager');";
        agentDatabase.execSQL(agent1);

        String agent2 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                "VALUES ('Luigi Plumber', '4031234568', 'luigi@te.ca', 'Junior Agent');";
        agentDatabase.execSQL(agent2);

        String agent3 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                "VALUES ('Wario Money', '4031234569', 'wario@te.ca', 'CEO');";
        agentDatabase.execSQL(agent3);

        String agent4 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                "VALUES ('Waluigi Money', '4031234570', 'waluigi@te.ca', 'CIO');";
        agentDatabase.execSQL(agent4);

        String agent5 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                "VALUES ('Yoshi Dinosaur', '4031234571', 'yoshi@te.ca', 'The Dinosaur');";
        agentDatabase.execSQL(agent5);

        String agent6 = "INSERT INTO agents (agent_name, agent_phone, agent_email, agent_position) " +
                "VALUES ('Toad Mushroom', '4031234572', 'toad@te.ca', 'The Mushroom');";
        agentDatabase.execSQL(agent6);

        String sql2 = "CREATE TABLE agencies (" +
                     "agency_id INTEGER PRIMARY KEY)";
                //"agent_name TEXT NOT NULL UNIQUE, " +
                //"agent_phone TEXT NOT NULL UNIQUE, " +
                //"agent_email TEXT NOT NULL UNIQUE);";
        agentDatabase.execSQL(sql2);

        String agency1 = "INSERT INTO agencies (agency_id) " +
                "VALUES (1);";
        agentDatabase.execSQL(agency1);

        String agency2 = "INSERT INTO agencies (agency_id) " +
                "VALUES (2);";
        agentDatabase.execSQL(agency2);

        String agency3 = "INSERT INTO agencies (agency_id) " +
                "VALUES (3);";
        agentDatabase.execSQL(agency3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase agentDatabase, int i, int i1) {

        agentDatabase.execSQL("DROP TABLE if exists agents;");
        agentDatabase.execSQL("DROP TABLE if exists agencies;");

        onCreate(agentDatabase);
    }
}
