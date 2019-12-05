package com.cgi.travelexpertsapp;

/**
 * Agency Class
 * Holds the Agencies that the Travel Experts have
 *
 * Author: Ivan Ng
 * Date Created: September 24, 2016
 * Last Updated: September 26, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 *
 */

import java.util.ArrayList;

/**
 * Created by 723243 on 9/24/2016.
 */
public class Agency {

    // Fields for the agency information
    private int agencyId;
    private String agencyAddress;
    private String agencyCity;
    private String agencyProv;
    private String agencyPostal;
    private String agencyCountry;
    private String agencyPhone;
    private String agencyFax;

    // Stores the agents that work at this agency
    ArrayList<Agent> agencyAgents;

    public Agency(int agencyId, String agencyAddress, String agencyCity, String agencyProv, String agencyPostal, String agencyCountry, String agencyPhone, String agencyFax) {
        this.agencyId = agencyId;
        this.agencyAddress = agencyAddress;
        this.agencyCity = agencyCity;
        this.agencyProv = agencyProv;
        this.agencyPostal = agencyPostal;
        this.agencyCountry = agencyCountry;
        this.agencyPhone = agencyPhone;
        this.agencyFax = agencyFax;
        this.agencyAgents = new ArrayList<Agent>();
    }

    public Agency() {
        // Do nothing
    }

    public Agency(int agencyId, String agencyAddress, String agencyCity, String agencyProv, String agencyPostal, String agencyCountry, String agencyPhone, String agencyFax, ArrayList<Agent> agencyAgents) {
        this.agencyId = agencyId;
        this.agencyAddress = agencyAddress;
        this.agencyCity = agencyCity;
        this.agencyProv = agencyProv;
        this.agencyPostal = agencyPostal;
        this.agencyCountry = agencyCountry;
        this.agencyPhone = agencyPhone;
        this.agencyFax = agencyFax;
        this.agencyAgents = new ArrayList<Agent>();
        this.agencyAgents = agencyAgents;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyCity() {
        return agencyCity;
    }

    public void setAgencyCity(String agencyCity) {
        this.agencyCity = agencyCity;
    }

    public String getAgencyProv() {
        return agencyProv;
    }

    public void setAgencyProv(String agencyProv) {
        this.agencyProv = agencyProv;
    }

    public String getAgencyPostal() {
        return agencyPostal;
    }

    public void setAgencyPostal(String agencyPostal) {
        this.agencyPostal = agencyPostal;
    }

    public String getAgencyCountry() {
        return agencyCountry;
    }

    public void setAgencyCountry(String agencyCountry) {
        this.agencyCountry = agencyCountry;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    public String getAgencyFax() {
        return agencyFax;
    }

    public void setAgencyFax(String agencyFax) {
        this.agencyFax = agencyFax;
    }

    public ArrayList<Agent> getAgencyAgents() {
        return agencyAgents;
    }

    public void setAgencyAgents(ArrayList<Agent> agencyAgents) {
        this.agencyAgents = agencyAgents;
    }

    public Agent getAgentAtIndex(int index) {
        return agencyAgents.get(index);
    }

    @Override
    public String toString() {
        return agencyId + " - " + agencyCity;
    }

    public String printAgencyAddress() {

        String addressInfo = agencyAddress + "\n" + agencyCity + ", " + agencyProv + "\n" + agencyPostal;

        return addressInfo;
    }
}
