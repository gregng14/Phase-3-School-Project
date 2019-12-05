package travelexperts;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/*
 * TravelExpertsREST
 * REST service where the information for the Travel Experts database is served out
 * 
 * Author: Ivan Ng
 * Date Created: September 17, 2016
 * Last Updated: September 24, 2016
 * Assignment: PROJ207 - Phase Three - Travel Experts Android App
 * 
 * Path to the main application
 * http://IPAddress:8080/TravelExpertsWebService/rs/travelexpertsREST/ <-- Add whatever path for the service you want
 */
@Path("/travelexpertsREST")
public class TravelExpertsREST {

	// Put wherever your MySQL database is.
	//May be case sensitive because I used the one on the Pi
	private String suchIP = "Your_DB_IP";
	private String dbName = "TravelExperts";
	private String userid = "user";
	private String password = "password";
	/*
	@GET
	@Path("/getagencies")
    @Produces(MediaType.TEXT_PLAIN)
	public String getAgencies() {

		StringBuilder sb = new StringBuilder();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + suchIP + ":3306/TravelExperts", userid, password);
			PreparedStatement stmt = conn.prepareStatement("SELECT AgencyId, AgncyCity FROM Agencies;");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				for (int i=1; i <= rsmd.getColumnCount(); i++) {
					sb.append(rsmd.getColumnName(i) + ":" + rs.getString(i) + "\t");
				}
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();	
	}
	*/
	
	@GET
	@Path("/getagencies")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAgenciesJSON() {

		ArrayList<JSONObject> jsonList= new ArrayList<JSONObject>();
		//JSONObject obj = new JSONObject();
		JSONObject obj;
		//StringBuilder sb = new StringBuilder();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + suchIP + ":3306/" + dbName, userid, password);
			PreparedStatement stmt = conn.prepareStatement("SELECT AgencyId, AgncyAddress, AgncyCity, AgncyProv,AgncyPostal, AgncyCountry, AgncyPhone, AgncyFax FROM Agencies;");
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				obj = new JSONObject();
				for (int i=1; i <= rsmd.getColumnCount(); i++) {
					
					//sb.append(rsmd.getColumnName(i) + ":" + rs.getString(i) + "\t");
					obj.put(rsmd.getColumnName(i), rs.getString(i));
					
							
				}
				jsonList.add(obj);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return sb.toString();
		//return obj.toJSONString();
		//return obj.toJSONString();
		return jsonList.toString();
	}
	
	@GET
	@Path("/getagents/{agencyid}")
    @Produces(MediaType.APPLICATION_JSON)
	public String getAgentsJSON(@PathParam("agencyid") int agencyId) {

		ArrayList<JSONObject> jsonList= new ArrayList<JSONObject>();
		//JSONObject obj = new JSONObject();
		JSONObject obj;
		//StringBuilder sb = new StringBuilder();
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + suchIP + ":3306/" + dbName, userid, password);
			PreparedStatement stmt = conn.prepareStatement("SELECT AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, AgtBusPhone, AgtEmail, AgtPosition, AgencyId FROM Agents WHERE AgencyId=?;");
			stmt.setInt(1, agencyId);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				obj = new JSONObject();
				for (int i=1; i <= rsmd.getColumnCount(); i++) {
					
					//sb.append(rsmd.getColumnName(i) + ":" + rs.getString(i) + "\t");
					if (rs.getString(i) == null) {
						obj.put(rsmd.getColumnLabel(i), "");
					} else {
						obj.put(rsmd.getColumnName(i), rs.getString(i));
					}
							
				}
				jsonList.add(obj);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return sb.toString();
		//return obj.toJSONString();
		//return obj.toJSONString();
		return jsonList.toString();
	}
/*
	@POST
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String postSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from Jersey Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postSomething");
        }
        return response;	
	}

	@PUT
	@Path("/<add method name here>")
    @Produces(MediaType.TEXT_PLAIN)
	public String putSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start putSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from Jersey Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End putSomething");
        }
        return response;	
	}

	@DELETE
	@Path("/<add method name here>")
	public void deleteSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteSomething");
        }
	}
	*/
}
