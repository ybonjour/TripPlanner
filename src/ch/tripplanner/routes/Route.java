package ch.tripplanner.routes;
import android.location.Location;


public class Route {
	
	private Location start;
	private Location destination;
	private long duration;
	private final RouteType type;
	
	public Route(RouteType type){
		this.type = type;
	}
	
	public Route(RouteType type, Location start, Location destination, long duration){
		this(type);
		
		if(start == null) throw new IllegalArgumentException("start");
		if(destination == null) throw new IllegalArgumentException("destination");
		
		this.start = start;
		this.destination = destination;
		this.duration = duration;
	}
	
	public static Route create(RouteType type){
		return new Route(type);
	}
	
	public Location getStart(){
		return this.start;
	}
	
	public void setStart(Location start){
		if(start == null) throw new IllegalArgumentException("start");
		this.start = start;
	}
	
	public Location getDestination(){
		return this.destination;
	}
	
	public void setDestination(Location destination){
		if(destination == null) throw new IllegalArgumentException("destination");
		this.destination = destination;
	}
	
	/**
	 * @return Duration in milliseconds to get from start to destination.
	 */
	public long getDuration(){
		return this.duration;
	}
	
	public void setDuration(long duration){
		this.duration = duration;
	}
	
	public RouteType getType(){
		return this.type;
	}
}
