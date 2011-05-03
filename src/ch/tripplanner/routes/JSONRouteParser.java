package ch.tripplanner.routes;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;

public class JSONRouteParser implements RouteParser {

	private static final String NAME_STATUS = "status";
	private static final String NAME_ROUTE = "route";
	private static final String NAME_START_LONGITUDE = "startLongitude";
	private static final String NAME_START_LATITUDE = "startLatitude";
	private static final String NAME_DESTINATION_LONGITUDE = "destinationLongitude";
	private static final String NAME_DESTINATION_LATITUDE = "destinationLatitude";
	private static final String NAME_TYPE = "type";
	private static final String NAME_DURATION = "duration";
	
	private static final String STATUS_OK = "OK";
	
	private final JSONObject mJSON;
	
	public JSONRouteParser(String input){
		if(input == null || input.length() == 0) throw new IllegalArgumentException();
		
		try{
			this.mJSON = new JSONObject(input);
		}catch(JSONException e){
			throw new IllegalArgumentException(e);
		}
	}
	
	public boolean isStatusOk() {
		try {
			return this.mJSON.getString(NAME_STATUS).equals(STATUS_OK);
		}
		catch(JSONException e){
			//TODO: Error handling
			throw new RuntimeException(e);
		}
	}
	
	public Route parseRoute() {	
		if(!isStatusOk()) throw new RuntimeException("No correct route. Please check isStatusOk() before calling parseRoute()");
		
		Route result;
		try{
			JSONObject route = this.mJSON.getJSONObject(NAME_ROUTE);			
			result =  new Route(getRouteType(route),
					getStartLocation(route),
					getDestinationLocation(route),
					getDuration(route));
		}catch(JSONException e){
			//TODO: error handling
			throw new RuntimeException(e);
		}

		return result;
	}
	
	private RouteType getRouteType(JSONObject route) throws JSONException{
		if(route == null) throw new IllegalArgumentException();
		
		int typeCode;
		try{
			typeCode = route.getInt(NAME_TYPE);
		}catch(JSONException e){
			throw e;
		}
		
		return RouteType.getType(typeCode);
	}
	
	private Location getStartLocation(JSONObject route) throws JSONException{
		return getLocation(route, NAME_START_LATITUDE, NAME_START_LONGITUDE);
	}
	
	private Location getDestinationLocation(JSONObject route) throws JSONException{
		return getLocation(route, NAME_DESTINATION_LATITUDE, NAME_DESTINATION_LONGITUDE);
	}
	
	private Location getLocation(JSONObject route, String nameLatitude, String nameLongitude) throws JSONException{
		if(route == null) throw new IllegalArgumentException();
		
		Location result = new Location("");
		
		double latitude;
		double longitude;
		try{
			latitude = route.getDouble(nameLatitude);
			longitude = route.getDouble(nameLongitude);
		}catch(JSONException e){
			throw e;
		}
		
		result.setLatitude(latitude);
		result.setLongitude(longitude);
		return result; 
	}
	
	private int getDuration(JSONObject route) throws JSONException{
		if(route == null) throw new IllegalArgumentException();
		
		int duration;
		try{
			duration = route.getInt(NAME_DURATION);
		}catch(JSONException e){
			throw e;
		}
		
		return duration;
	}
}
