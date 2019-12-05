package com.cgi.travelexpertsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * AgencyDB
 * This DB class was more for testing with the SQLite database for loading agencies
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 14, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
/**
 * Created by 723243 on 9/14/2016.
 */
public class AgencyDB {

    private SQLiteHelper helper;
    private SQLiteDatabase db;

    public AgencyDB(Context context) {
        helper = new SQLiteHelper(context);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public ArrayList<Integer> getAllAgencies() {

        ArrayList<Integer> listOfAgencies = new ArrayList<Integer>();
        //Connection conn = null;

        String sql = "SELECT agency_id FROM agencies;";
        Cursor cursor = db.rawQuery(sql,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            listOfAgencies.add(cursor.getInt(0));
            cursor.moveToNext();
        }

        /* Well this failed badly
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.30.110:3306/travelexperts", "notyou", "");

            String sql = "SELECT DISTINCT AgencyId FROM agencies;";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            //listOfAgencies = new ArrayList<Integer>();

            if (rs.next()) {
                int currAgencyId = rs.getInt("AgencyId");
                listOfAgencies.add(currAgencyId);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } */

        return listOfAgencies;
    }
}
