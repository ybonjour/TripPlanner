package ch.tripplanner.routes;

import android.location.Location;

public class RouteRequest {
	private final Location start;
	private final Location destination;
	private final RouteType type;
	
	public RouteRequest(Location start, Location destination, RouteType type){
		if(start == null) throw new IllegalArgumentException("start");
		if(destination == null) throw new IllegalArgumentException("destination");
		if(type == null) throw new IllegalArgumentException("type");
		
		this.start = start;
		this.destination = destination;
		this.type= type;
	}
	
	public Location getStart(){
		return this.start;
	}
	
	public Location getDestination(){
		return this.destination;
	}
	
	public RouteType getType(){
		return this.type;
	}
}