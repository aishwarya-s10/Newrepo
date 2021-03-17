package UserMS.Validator;

import UserMS.entity.Buyer;
import UserMS.entity.Seller;

public class validator {

//*****************************************************************************VALIDATE SELLER
	public static void  validateSeller(Seller seller) throws Exception
	{
		if(!validator.validateName(seller.getName()))
		{
			throw new Exception("VALIDATOR.INVALID_NAME");
		}
		if(!validator.validateEmailID(seller.getEmail()))
		{
			throw new Exception("VALIDATOR.INVALID_EMAIL");
		}
		if(!validator.validatePassword(seller.getPassword()))
		{
			throw new Exception("VALIDATOR.INVALID_PASSWORD");
		}
		if(!validator.validatePhonenumber(seller.getPhonenumber()))
		{
			throw new Exception("VALIDATOR.INVALID_PHONENUMBER");
		}
			
	}
	
//*****************************************************************************VALIDATE BUYER	
	public static void  validateBuyer(Buyer buyer) throws Exception
	{
		if(!validator.validateName(buyer.getName()))
		{
			throw new Exception("VALIDATOR.INVALID_NAME");
		}
		if(!validator.validateEmailID(buyer.getEmail()))
		{
			throw new Exception("VALIDATOR.INVALID_EMAIL");
		}
		if(!validator.validatePassword(buyer.getPassword()))
		{
			throw new Exception("VALIDATOR.INVALID_PASSWORD");
		}
		if(!validator.validatePhonenumber(buyer.getPhonenumber()))
		{
			throw new Exception("VALIDATOR.INVALID_PHONENUMBER");
		}
			
	}
	
//***********************************************************************************************
	public static Boolean validateName(String name) {
			String regex = "^[a-zA-Z-,]+(\\s{0,1}[a-zA-Z-])*$";
			if(name.matches(regex)) {
				return true;
			}
			return false;
		}
	
	public static Boolean validateEmailID(String email) {
			String regex = "[a-zA-Z0-9]+@[a-zA-Z]+\\.(com)";
			if(email.matches(regex)) {
				return true;
			}
			return false;
		}
	
	public static Boolean validatePhonenumber(String phonenumber) {
			String regex = "^[789]\\d{9}$";
			if(phonenumber.matches(regex)) {
				return true;
			}
			return false;
		}

	public static Boolean validatePassword(String password) {
			String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{7,20}$"; 
			if(password.matches(regex)) {
				return true;
			}
			return false;
		}

}

