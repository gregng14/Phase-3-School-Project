package com.cgi.travelexpertsapp;

/**
 * Agent Class
 * Holds the data for the Agents in the company
 *
 * Author: Ivan Ng
 * Date Created: September 14, 2016
 * Last Updated: September 24, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */
public class Agent {

    private int agtId;
    private String agtFName;
    private String agtMInitial;
    private String agtLName;
    private String agtBusPhone;
    private String agtEmail;
    private String agtPosition;
    private int agtAgencyId;

    public Agent() {
    }

    public Agent(int agtId, String agtFName, String agtMInitial, String agtLName, String agtBusPhone, String agtEmail, String agtPosition, int agtAgencyId) {
        this.agtId = agtId;
        this.agtFName = agtFName;
        this.agtMInitial = agtMInitial;
        this.agtLName = agtLName;
        this.agtBusPhone = agtBusPhone;
        this.agtEmail = agtEmail;
        this.agtPosition = agtPosition;
        this.agtAgencyId = agtAgencyId;
    }

    public int getAgtId() {
        return agtId;
    }

    public void setAgtId(int agtId) {
        this.agtId = agtId;
    }

    public String getAgtFName() {
        return agtFName;
    }

    public void setAgtFName(String agtFName) {
        this.agtFName = agtFName;
    }

    public String getAgtMInitial() {
        return agtMInitial;
    }

    public void setAgtMInitial(String agtMInitial) {
        this.agtMInitial = agtMInitial;
    }

    public String getAgtLName() {
        return agtLName;
    }

    public void setAgtLName(String agtLName) {
        this.agtLName = agtLName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    public int getAgtAgencyId() {
        return agtAgencyId;
    }

    public void setAgtAgencyId(int agtAgencyId) {
        this.agtAgencyId = agtAgencyId;
    }

    // Gets the first and last name for a string
    public String getAgtName() {
        return agtFName + " " + agtLName;
    }
}
