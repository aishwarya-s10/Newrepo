package UserMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import UserMS.controller.userController;
import UserMS.dto.CartDTO;
import UserMS.dto.Product;
import UserMS.dto.WishlistDTO;
import UserMS.entity.CompositeKey;
import UserMS.entity.Wishlist;
import UserMS.repository.WishlistRepository;


@Service
public class WishListService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WishlistRepository wishlistrepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	userController controller;
	
	String producturi="http://localhost:8300/api/products";
	
//*************************************************************************************** GET WISHLISTS
	public  List<WishlistDTO> getAllwishlist()
	{
		logger.info("Fetching All Wishlists ");
		List<Wishlist> wishlist=wishlistrepository.findAll();
		List<WishlistDTO> wishlistDTO= new ArrayList<>();
		for(Wishlist i :wishlist)
		{
			WishlistDTO wishlistdto=WishlistDTO.valueOf(i);
			wishlistDTO.add(wishlistdto);
		}
		return wishlistDTO;
	}
	
//*************************************************************************************** GET SPECIFIC WISHLIST
	public WishlistDTO getSpecificWishlist(Integer buyerId, Integer prodId) {
		logger.info("Fetching Wishlist ");
		CompositeKey comkey= new CompositeKey();
		comkey.setBuyerId(buyerId);
		comkey.setProdId(prodId);
		WishlistDTO wishlistDTO = null;
		wishlistrepository.findAll();
		Optional<Wishlist> optWishList = wishlistrepository.findById(comkey);
		if(optWishList.isPresent()) {
			Wishlist wishlist = optWishList.get();
			wishlistDTO = WishlistDTO.valueOf(wishlist);
		}
		return wishlistDTO;
	}

//**************************************************************************************** DELETE SPECIFIC WISHLIST	
	public void deleteSpecificWishlist(Integer buyerId, Integer prodId) {
		logger.info("Deleting Specific Wishlist ");
		CompositeKey comkey= new CompositeKey();
		comkey.setBuyerId(buyerId);
		comkey.setProdId(prodId);
		wishlistrepository.deleteById(comkey);
	}
//********************************************************************************** DELETE WISHLIST
	public void deleteWishlist() throws Exception{
		logger.info("Deleting Wishlist ");
		wishlistrepository.deleteAll();
	}
	   
//**************************************************************************************** ADD TO WISHLIST	
	public void addWishlist(WishlistDTO wishlist) {
		logger.info("Adding into Wishlist ");
		Wishlist wish = new Wishlist();
		wish.setBuyerId(wishlist.getBuyerId());
		wish.setProdId(wishlist.getProdId());
		wishlistrepository.save(wish);
	}
//******************************************************************************************* WISHLISTTOCART
	
//	public void removeFromWishList(WishlistDTO wishlistDTO) throws Exception {
//		Wishlist wishlistEntity = wishlistrepository.findByIdBuyerIdAndIdProdId(wishlistDTO.getBuyerId(),
//				wishlistDTO.getProdId());
//		if (wishlistEntity != null) {
//			wishlistrepository.deleteById(new CompositeKey(wishlistDTO.getBuyerId(), wishlistDTO.getProdId()));
//		} else {
//			throw new Exception("wishlist.NOT_AVAILABLE");
//		}
//	}
//	
	
	
	
	
//	public void wishListMoveToCart(WishlistDTO wishlistDTO, int quantity) throws Exception {
//		
//		Wishlist wishlistEntity = wishlistrepository.findByIdBuyerIdAndIdProdId(wishlistDTO.getBuyerId(),
//				wishlistDTO.getProdId());
//		if (wishlistEntity != null) {
//			if (controller.isBuyerPrivileged(wishlistDTO.getBuyerId())) {
//
//				final String baseUrl = producturi + wishlistDTO.getProdId();
//				ResponseEntity<Product> result = restTemplate.getForEntity(baseUrl, Product.class);
//				if (result.getBody().getStock() >= quantity) {
//					CartDTO cartDTO = new CartDTO(wishlistDTO.getBuyerId(), wishlistDTO.getProdId(), quantity);
//
//					List<CartDTO> cartlist = controller.getAllCartItem(wishlistDTO.getBuyerId());
//
//					if (cartlist.size() == 0) {
//						removeFromWishList(wishlistDTO);
//						controller.addToCart(cartDTO);
//					} else {
//						for (CartDTO cart2 : cartlist) {
//							if (cart2.getProdId() == wishlistDTO.getProdId()) {
//								if (cart2.getQuantity() + quantity <= result.getBody().getStock()) {
//									removeFromWishList(wishlistDTO);
//									cart2.setQuantity(cart2.getQuantity() + quantity);
//									controller.addToCart(cart2);
//									
//
//								} else {
//									throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
//
//								}
//							}
//
//						}
//					}
//
//				} else {
//					throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
//				}
//
//			} else {
//				if (quantity < 10) {
//					final String baseUrl = producturi + wishlistDTO.getProdId();
//					ResponseEntity<Product> result = restTemplate.getForEntity(baseUrl, Product.class);
//					if (result.getBody().getStock() >= quantity) {
//
//						CartDTO cart = new CartDTO(wishlistDTO.getBuyerId(), wishlistDTO.getProdId(), quantity);
//
//						List<CartDTO> cartlist = controller.getAllCartItem(wishlistDTO.getBuyerId());
//
//						if (cartlist.size() == 0) {
//							removeFromWishList(wishlistDTO);
//							controller.addToCart(cart);
//						} else {
//							for (CartDTO cart2 : cartlist) {
//								if (cart2.getProdId() == wishlistDTO.getProdId()) {
//									if (cart2.getQuantity() + quantity <= result.getBody().getStock() && cart2.getQuantity() + quantity<=10 ) {
//										removeFromWishList(wishlistDTO);
//										cart2.setQuantity(cart2.getQuantity() + quantity);
//									controller.addToCart(cart2);
//										
//
//									} else {
//										throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
//
//									}
//								}
//
//							}
//						}
//
//					} else {
//						throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
//					}
//
//				} else {
//					throw new Exception("wishlist.NOT_PRIVILEGE_BUYER");
//				}
//
//			}
//		} else {
//			throw new Exception("wishlist.NOT_AVAILABLE");
//		}
//
//	}
}