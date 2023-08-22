package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import payload.User;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}

	@Test
	public void createUser() {
		
		System.out.println("***************Creating User**********************");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("***************User creatged**********************");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		System.out.println("****************Reading User***********************");
		Response response = UserEndpoints.getUserByName(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("***************User Info displayed*****************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		
		System.out.println("**************Updating User*********************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		
		System.out.println("*****************User Updated*********************");
		Response responseAfterupdate = UserEndpoints.getUserByName(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		System.out.println("*****************Deleting User*********************");
		
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println("***************User Deleted**********************");
		
		
	}
	
	
}
