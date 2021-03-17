package UserMS.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	@EmbeddedId
	CompositeKey comkey;
	@Column(name="QUANTITY",nullable=false)
	Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Cart(CompositeKey comkey, Integer quantity) {
		super();
		this.comkey = comkey;
		this.quantity = quantity;
	}
	public CompositeKey getComkey() {
		return comkey;
	}
	public void setComkey(CompositeKey comkey) {
		this.comkey = comkey;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
