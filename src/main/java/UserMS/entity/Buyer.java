package UserMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buyer")
public class Buyer {
	@Id
	@Column(name="BUYERID",nullable=false)
	Integer buyerId;
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
	@Column(name="REWARDPOINTS")
	Integer rewardPoints;
	@Column(name="ISPRIVILEGED")
	boolean isPrivileged;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
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
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public 	boolean getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(	boolean isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	

}
