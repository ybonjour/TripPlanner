package ch.tripplanner.routes;

public abstract class RouteRequester {

	//invariant: request != null
	private final RouteRequest request;
	
	public RouteRequester(RouteRequest request){
		if(request == null) throw new IllegalArgumentException("request");
		this.request = request;
	}
	
	protected RouteRequest getRequest(){
		return this.request;
	}
	
	public abstract Route executeWebRequest();
}
