package UserMS.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import UserMS.controller.userController;
import UserMS.dto.*;
import UserMS.entity.Cart;
import UserMS.entity.CompositeKey;
import UserMS.repository.CartRepository;

@Service
public class CartService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CartRepository cartrepository;

	//@Value("${producturi}")
	String producturi="http://localhost:8300/api/products";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	userController controller;

	
	
//********************************************************************************** GET ALL CARTS
	public  List<CartDTO> getAllcartDetails()
	{
		logger.info("Fetching All Cart Details");
		List<Cart> cart=cartrepository.findAll();
		List<CartDTO> cartDTO= new ArrayList<>();
		for(Cart i :cart)
		{
			CartDTO cartdto=CartDTO.valueOf(i);
			cartDTO.add(cartdto);
		}
		return cartDTO;
	}
	
	
	public List<CartDTO> getAllCartItem(int buyerId) {
		Iterable<Cart> cartEntity = cartrepository.findAll();
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		for (Cart cartEntity2 : cartEntity) {
			if (cartEntity2.getComkey().getBuyerId() == buyerId) {
				CartDTO cartDTO = new CartDTO(cartEntity2.getComkey().getBuyerId(), cartEntity2.getComkey().getProdId(),
						cartEntity2.getQuantity());
				cartList.add(cartDTO);
			}

		}
		return cartList;
	}
	
	
//********************************************************************************** GET SPECIFIC CART
   public CartDTO getSpecificCart(Integer buyerId, Integer prodId)
   {
	   logger.info("Fetching Cart Details");
	   CompositeKey comkey= new CompositeKey();
	   comkey.setBuyerId(buyerId);
	   comkey.setProdId(prodId);
	   CartDTO cartdto=null;
	   Optional<Cart> optCart=cartrepository.findById(comkey);
	   if(optCart.isPresent())
	   {
		   Cart cart=optCart.get();
		   cartdto=CartDTO.valueOf(cart);
	   }
	   return cartdto;
   }
   
//********************************************************************************** DELETE SPECIFIC CART
   public void deleteSpecificCart(Integer buyerId, Integer prodId) throws Exception
   {
	   logger.info("Deleting Cart ");
	   CompositeKey comkey= new CompositeKey();
	   comkey.setBuyerId(buyerId);
	   comkey.setProdId(prodId);
	   cartrepository.deleteById(comkey);
   }

   public void deleteCart()throws Exception{
	   cartrepository.deleteAll();
   }
   
//********************************************************************************** DELETE CART
   public void removeFromCart(CartDTO cartDTO) throws Exception {
		
		Optional<Cart> cartEntity = cartrepository.findById(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()));
		if(cartEntity.isPresent()) {
			cartrepository.deleteById(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()));
			}
		else {
			throw new Exception("cart.ITEM_NOT_IN_CART");
		}
	}
   
//*********************************************************************************** ADD TO CART
   public void addCartDetails(Cart cart)
   {
	   cartrepository.save(cart);
   }
//******************************************************************************
   public List<CartDTO> checkout(int buyerId) {
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		Iterable<Cart> cart = cartrepository.findAll();
		for (Cart cart2 : cart) {
			if (cart2.getComkey().getBuyerId() == buyerId) {
				CartDTO cartDTO = new CartDTO(cart2.getComkey().getBuyerId(), cart2.getComkey().getProdId(),
						cart2.getQuantity());
				cartList.add(cartDTO);
				try {
					removeFromCart(cartDTO);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cartList;
	}
  
   

   public void addToCart(CartDTO cartDTO) throws Exception {
		final String baseUrl = producturi + cartDTO.getProdId();
		ResponseEntity<Product> result = restTemplate.getForEntity(baseUrl, Product.class);
		int stock = result.getBody().getStock();

		Optional<Cart> cartEntity = cartrepository.findById(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()));
		if (cartEntity.isPresent()) {
			if (controller.isBuyerPrivileged(cartDTO.getBuyerId())) {
				if (cartDTO.getQuantity() + cartEntity.get().getQuantity() <= stock) {
					cartDTO.setQuantity(cartDTO.getQuantity() + cartEntity.get().getQuantity());
					Cart cartEntity1 = new Cart(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()),
							cartDTO.getQuantity());
					cartrepository.save(cartEntity1);

				} else {
					throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
				}

			} else {
				if (cartDTO.getQuantity() + cartEntity.get().getQuantity() <= stock
						&& cartDTO.getQuantity() + cartEntity.get().getQuantity() <= 10) {
					cartDTO.setQuantity(cartDTO.getQuantity() + cartEntity.get().getQuantity());
					Cart cartEntity1 = new Cart(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()),
							cartDTO.getQuantity());

					cartrepository.save(cartEntity1);
				}

				else {
					if (cartDTO.getQuantity() + cartEntity.get().getQuantity() > 10) {
						throw new Exception("wishlist.NOT_PRIVILEGE_BUYER");

					} else {
						throw new Exception("wishlist.STOCK_NOT_AVAILABLE");

					}
				}

			}

		} 
		
		else {
			if (controller.isBuyerPrivileged(cartDTO.getBuyerId())) {
				if (cartDTO.getQuantity() <= stock) {
					Cart cartEntity1 = new Cart(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()),
							cartDTO.getQuantity());
					cartrepository.save(cartEntity1);

				} else {
					throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
				}

			} else {

				if (cartDTO.getQuantity() <= 10 && cartDTO.getQuantity() <= stock) {
					Cart cartEntity1 = new Cart(new CompositeKey(cartDTO.getBuyerId(), cartDTO.getProdId()),
							cartDTO.getQuantity());
					cartrepository.save(cartEntity1);

				} else {
					if (cartDTO.getQuantity() > stock) {
						throw new Exception("wishlist.STOCK_NOT_AVAILABLE");
					} else {
						throw new Exception("wishlist.NOT_PRIVILEGE_BUYER");

					}
				}
			}

		}

	}
   
   
}
