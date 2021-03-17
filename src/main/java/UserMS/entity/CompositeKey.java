package UserMS.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@SuppressWarnings("serial")
@Embeddable
public class CompositeKey implements Serializable{
	@Column(name="BUYERID",nullable=false)
	Integer buyerId;

	@Column(name="PRODID",nullable=false)
	Integer prodId;

	public Integer getBuyerId() {
		return buyerId;
	}

	public CompositeKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompositeKey(Integer buyerId, Integer prodId) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
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

}
