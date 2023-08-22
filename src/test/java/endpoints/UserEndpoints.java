package endpoints;

import io.restassured.response.Response;
import payload.User;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class UserEndpoints {
	
	static ResourceBundle getURL() {
		  ResourceBundle routes = ResourceBundle.getBundle("routes");
		  return routes;
		
	}
	
	public static Response createUser(User payload) {
		
		String postURL=getURL().getString("post_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(postURL);
		
		return response;
	}

	
	public static Response getUserByName(String userName) {
		
		String getURL=getURL().getString("get_url");
		Response response = given()
			.pathParam("username",userName)
		.when()
			.get(getURL);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) {
		
		String putURL=getURL().getString("update_url");
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(putURL);
			
			return response;
		
	}
	
	public static Response deleteUser(String userName) {
		
		String deleteURL=getURL().getString("delete_url");
		Response response = given()
								.pathParam("username", userName)
							.when()
								.delete(deleteURL);
		return response;
	}
}
