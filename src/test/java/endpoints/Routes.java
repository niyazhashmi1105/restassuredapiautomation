package endpoints;

public class Routes {
	
	public static final String baseURL = "https://petstore.swagger.io/v2";
	
	//User
	public static final String postURL = baseURL+"/user";
	public static final String getURL = baseURL+"/user/{username}";
	public static final String putURL = baseURL+"/user/{username}";
	public static final String deleteURL = baseURL+"/user/{username}";

}
