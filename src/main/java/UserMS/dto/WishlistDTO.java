package UserMS.dto;


import UserMS.entity.Wishlist;

public class WishlistDTO {
	
	Integer buyerId;
	
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
	
//**************************************************************************************** ENTITY TO DTO
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
		wishlistDTO.setProdId(wishlist.getProdId());
		return wishlistDTO;
	}

//**************************************************************************************** DTO TO ENTITY	
	public Wishlist createEntity() {
		Wishlist wishlist = new Wishlist();
		wishlist.setBuyerId(wishlist.getBuyerId());
		wishlist.setProdId(wishlist.getProdId());
		return wishlist;
	}
	
	@Override
	public String toString() {
		return "WishlistDTO [buyerid=" + buyerId + ", prodid=" + prodId + "]";
	}

}

