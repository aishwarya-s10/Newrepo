package UserMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	@Id
	@Column(name="SELLERID",nullable=false)
	Integer sellerId;
	@Column(name="NAME",nullable=false,length=50)
	String name;
	@Column(name="EMAIL",nullable=false,length=45)
	String email;
	@Column(name="PHONENUMBER",nullable=false,length=45)
	String phonenumber;
	@Column(name="PASSWORD",nullable=false,length=45)
	String password;
	@Column(name="ISACTIVE",length=1)
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
	
	

}
