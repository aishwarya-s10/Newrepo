package UserMS.dto;
import UserMS.entity.Seller;

public class SellerDTO {
	
	Integer sellerId;
	String name;
	String email;
	String phonenumber;
	String password;
	boolean isactive;
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public 	boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(	boolean isactive) {
		this.isactive = isactive;
	}

//**************************************************************************************** ENTITY TO DTO
	public static SellerDTO valueOf(Seller sell)
	{
		SellerDTO sellDTO= new SellerDTO();
		sellDTO.setEmail(sell.getEmail());
		
		sellDTO.setName(sell.getName());
		sellDTO.setPassword(sell.getPassword());
		sellDTO.setPhonenumber(sell.getPhonenumber());
		sellDTO.setSellerId(sell.getSellerId());
		return sellDTO;	
	}
	
//**************************************************************************************** DTO TO ENTITY
	public Seller createEntity()
	{
		Seller sell = new Seller();
		sell.setEmail(this.getEmail());
	
		sell.setName(this.getName());
		sell.setPassword(this.getPassword());
		sell.setSellerId(this.getSellerId());
		sell.setPhonenumber(this.getPhonenumber());
		return sell;
	}
	
	@Override
	public String toString() {
		return "SellerDTO [sellerId=" + sellerId + ", name=" + name + ", email=" + email + ", phonenumber="
				+ phonenumber + ", password=" + password + ", isactive=" + isactive + "]";
	}
	
}
