package ch.tripplanner.routes;

public class RouteType {
	
	private static final int PUBLIC_TRANSPORTATION_CODE = 1;
	private static final int CAR_CODE = 2;
	
	public static final RouteType PUBLIC_TRANSPORTATION = new RouteType(PUBLIC_TRANSPORTATION_CODE);
	public static final RouteType CAR = new RouteType(CAR_CODE);
	
	private final int code;
	
	private RouteType(int code){
		if(code != PUBLIC_TRANSPORTATION_CODE && code != CAR_CODE) throw new IllegalArgumentException();
		
		this.code = code;
	}
	
	public static RouteType getType(int code){
		switch(code){
		case PUBLIC_TRANSPORTATION_CODE:
			return PUBLIC_TRANSPORTATION;
		case CAR_CODE:
			return CAR;
		default:
			throw new IllegalArgumentException("Code is unkown");
		}
	}
	
	public int getCode(){
		return this.code;
	}
}
