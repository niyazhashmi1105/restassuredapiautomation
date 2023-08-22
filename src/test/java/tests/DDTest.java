package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import payload.User;
import utils.DataProviders;

public class DDTest {
	
	
	@Test(priority=1, dataProvider ="Data", dataProviderClass=DataProviders.class)
	public void testCreateUserDDT(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
	User userPayload = new User();
	userPayload.setId(Integer.parseInt(userID));
	userPayload.setUsername(userName);
	userPayload.setFirstName(fname);
	userPayload.setLastName(lname);
	userPayload.setEmail(useremail);
	userPayload.setPassword(pwd);
	userPayload.setPhone(ph);
	Response response=UserEndpoints.createUser(userPayload);
	Assert.assertEquals(response.getStatusCode(),200);
}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByNameDDT(String userName)
	{
			Response response=UserEndpoints.deleteUser(userName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}
}