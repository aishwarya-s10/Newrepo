package UserMS.dto;

import UserMS.entity.Cart;
import UserMS.entity.CompositeKey;

public class CartDTO {
	int buyerId;
	int prodId;
	Integer quantity;
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartDTO(int buyerId, int prodId, Integer quantity) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//**************************************************************************************** ENTITY TO DTO
	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		CompositeKey comkey= new CompositeKey();
		comkey.setBuyerId(cart.getComkey().getBuyerId());
		comkey.setProdId(cart.getComkey().getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}

//**************************************************************************************** DTO TO ENTITY		
	public Cart createEntity() {
		Cart cart = new Cart();
		CompositeKey comkey= new CompositeKey();
		comkey.setBuyerId(cart.getComkey().getBuyerId());
		comkey.setProdId(cart.getComkey().getProdId());
		cart.setQuantity(this.getQuantity());
		return cart;
	}
		
	@Override
	public String toString() {
		return "CartDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}

}
