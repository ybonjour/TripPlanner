package ch.tripplanner.routes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RouteWebRequester extends RouteRequester{
	
	public RouteWebRequester(RouteRequest request){
		super(request);
	}
	
	public Route executeWebRequest() {
		String route = requestRoute();
		
		RouteParser parser = new JSONRouteParser(route);
		if(!parser.isStatusOk()){
			//TODO: Error handling
			throw new RuntimeException();
		}
		
		return parser.parseRoute();
	}

	private String requestRoute() {
		HttpClient client = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(getUrl());
		
		try{
			HttpResponse response = client.execute(httpRequest);
			return extractRouteStringFromResponse(response);
		}
		catch(Exception e){
			//TODO Error handling
			throw new RuntimeException(e);
		}
	}
	
	private String getUrl() {
		return String.format("http://service.tripplanner.ch/ServiceMock.php?startLongitude=%s&startLatitude=%s&destinationLongitude=%s&destinationLatitude=%s&mode=%s",
				this.getRequest().getStart().getLongitude(),
				this.getRequest().getStart().getLatitude(),
				this.getRequest().getDestination().getLongitude(),
				this.getRequest().getDestination().getLatitude(),
				this.getRequest().getType().getCode());
	}

	private String extractRouteStringFromResponse(HttpResponse response) throws Exception {
		HttpEntity entity = response.getEntity();
		if (entity == null) throw new Exception("No entity in response");
		
		InputStream instream = entity.getContent();
		return convertStreamToString(instream);
	}

	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}