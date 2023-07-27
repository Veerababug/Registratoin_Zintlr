package testcases;

import org.testng.annotations.Test;

import Base.Base_Class;

public class Register extends Base_Class{
	
	@Test
	public void create_Account() throws Exception {
		application.launchBrowser("chrome");
		application.navigate("url");
		application.click("cookies_xpath");
		application.click("email_xpath");
		application.type("email_xpath", "emailid");
		application.click("signup_xpath");
		application.type("username_id", "username");
		application.type("emailid_xpath", "emailid");
		application.selectFromDropDown("country_name","droptext");
		application.type("phone_css", "Mobile");
		application.type("password_css", "pass");
		application.type("confirmpassword_css", "pass1");
		application.click("checkbox_css");
		application.click("sign_xpath");
		
	}

	
}
