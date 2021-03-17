package UserMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(CompositeKey.class)
@Table(name="wishlist")
public class Wishlist {
	
	@Id
	@Column(name="BUYERID",nullable=false)
	Integer buyerId;
	
	@Id
	@Column(name="PRODID",nullable=false)
	Integer prodId;
	

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Wishlist(Integer buyerId, Integer prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
	}

	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}

		
}
