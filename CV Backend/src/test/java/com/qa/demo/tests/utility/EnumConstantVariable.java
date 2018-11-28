package com.qa.demo.tests.utility;


public class EnumConstantVariable 
{
	
	public enum LoginTag {joeBlogs}
	
	private static String accountCreationURL = "/createuser";
	private static String websiteURL = "http://localhost:3000";
	
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;
    private static EnumConstantVariable enumReferance;
    
    private EnumConstantVariable()
    {
    }
    
    public void SetVariables(LoginTag detailsToUse)
    {
    	switch(detailsToUse)
    	{
    	case joeBlogs:
    		
    		firstName = "Joe";
    		lastName = "Bloggs";
    		email = "JohnDoe@Email.com";
    		password = "Password123";
    		break;
    		
		default:
			throw new IllegalArgumentException("invalid dataset specified, please check the EnumConstantValible LoginTag");
    	}
    }
    
    static public EnumConstantVariable GetConstantVariables(LoginTag detailsToUse)
    {
    	if(enumReferance == null)
    	{
    		enumReferance = new EnumConstantVariable();
    	}
    	
    	enumReferance.SetVariables(detailsToUse);
    	return enumReferance;
    }

	public String getAccountCreationURL() {
		return accountCreationURL;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}   
}
