package UserMS.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import UserMS.dto.BuyerDTO;
import UserMS.dto.CartDTO;
import UserMS.dto.LoginDTO;
import UserMS.dto.SellerDTO;
import UserMS.dto.WishlistDTO;
import UserMS.service.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class userController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerService sellservice;
	
	@Autowired
	BuyerService buyservice;
	
	@Autowired
	CartService cartservice;
	
	@Autowired
	WishListService wishlistService;
	
	@Autowired
	Environment environment;
	
	//@Value("${product.uri}")
	String producturi="http://localhost:8300/api/products";
//**************************************************************************************************** SELLER	
	@GetMapping(value="/seller",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SellerDTO> getAllSells()
	{
		return sellservice.getAllSeller();
	}
	
	@GetMapping(value= "/seller/{sellerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public SellerDTO getSpecificsell(@PathVariable Integer sellerId) throws Exception
	{
		return sellservice.getSpecificSeller(sellerId);
	}
	
	@PostMapping(value = "/login/seller")
	public ResponseEntity<String> loginSeller(@RequestBody LoginDTO loginDTO) throws Exception {
		if (sellservice.login(loginDTO) == true) {
			return new ResponseEntity<String>("LOGIN SUCCESSFULLY", HttpStatus.OK);
		} else if (sellservice.login(loginDTO) == false) {
			return new ResponseEntity<String>("LOGIN FAILED. WRONG CREDENTIALS ", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("LOGIN FAILED. USER NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = "/seller/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDTO) throws Exception {
		sellservice.registerSeller(sellerDTO);
		return new ResponseEntity<String>("Seller Added Successfully", HttpStatus.OK);                     
	}
	
	@DeleteMapping(value = "/seller/deactivate/{sellerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSpecificSeller(@PathVariable Integer sellerId) {
		sellservice.deleteSpecificSeller(sellerId);
		return new ResponseEntity<String>("Seller Deleted Successfully", HttpStatus.OK);
		
}
	
//****************************************************************************************************** BUYER	
	@GetMapping(value="/buyer",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<BuyerDTO> getAllbuyers()
	{
		return  buyservice.getAllBuyer();
	}
	
	@GetMapping(value= "/buyer/{buyerId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public BuyerDTO getSpecificBuyer(@PathVariable Integer buyerId)throws Exception
	{
		return buyservice.getSpecificBuyer(buyerId);
	}
	

//	@PutMapping(value = "/buyer/privilege")
//	public ResponseEntity<String> updateBuyerPrivilege(@RequestParam(name = "email") String email ,@RequestParam(name = "privilege") boolean privilege) {
//
//		ResponseEntity<String> responseEntity = null;
//
//		try {
//			
//			
//			buyservice.updateBuyerPrivilege(email, privilege);
//			String successMessage = environment.getProperty("Buyer.PRIVILEGE_UPDATE_SUCCESS");
//			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);
//
//		} catch (Exception exception) {
//			LOGGER.error("Error: " + exception.getMessage(), exception);
//
//			String errorMessage;
//
//			if (exception.getMessage() == null) {
//				errorMessage = environment.getProperty("General.EXCEPTION");
//				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
//				return responseEntity;
//
//			} else {
//				errorMessage = environment.getProperty(exception.getMessage());
//
//				if (errorMessage == null)
//					errorMessage = environment.getProperty("General.EXCEPTION");
//				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
//				return responseEntity;
//
//			}
//		}
//
//		return responseEntity;
//
//	}

	
	public boolean isBuyerPrivileged(@RequestParam int buyerId) {

		return buyservice.IsPrivileged(buyerId);
	}
	
	@PostMapping(value = "/buyer/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO) throws Exception {
		buyservice.registerBuyer(buyerDTO);
		return new ResponseEntity<String>("Buyer Added Successfully", HttpStatus.OK);                     
	}
	
	@PostMapping(value = "/login/buyer")
	public ResponseEntity<String> loginBuyer(@RequestBody LoginDTO loginDTO) throws Exception{
		if (buyservice.login(loginDTO) == true) {
			return new ResponseEntity<String>("LOGIN SUCCESSFULLY", HttpStatus.OK);
		} else if (buyservice.login(loginDTO) == false) {
			return new ResponseEntity<String>("LOGIN FAILED. WRONG CREDENTIALS ", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>("LOGIN FAILED. USER NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value = "/buyer/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSpecificBuyer(@PathVariable Integer buyerId) {
		buyservice.deleteSpecificBuyer(buyerId);
		return new ResponseEntity<String>("Buyer Deleted Successfully", HttpStatus.OK);
		
}
	
//************************************************************************************************************** CART
	@GetMapping(value="/cart",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> getallcart()
	{
		return  cartservice.getAllcartDetails();
	}
	
	@GetMapping(value = "api/cart")
	public List<CartDTO> getAllCartItem(@RequestParam int buyerId) {
		return cartservice.getAllCartItem(buyerId);

	}
	
	@GetMapping(value= "/cart/{buyerId}/{proId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public CartDTO getSpecificCart(@PathVariable Integer buyerId,@PathVariable  Integer proId)
	{
		return cartservice.getSpecificCart(buyerId,proId);
	}

	@PostMapping(value = "/cart/add",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) throws Exception {

		ResponseEntity<String> responseEntity = null;
		cartservice.addToCart(cartDTO);
		String successMessage = environment.getProperty("cart.ADD_SUCCESS");
		responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return responseEntity;

	}
	
	@GetMapping(value = "/cart/checkout/{buyerId}")
	public List<CartDTO> checkOutFromCart(@PathVariable int buyerId) {
		return cartservice.checkout(buyerId);
	}
	

	
	@DeleteMapping(value = "/cart/remove")
	public ResponseEntity<String> deleteCart() throws Exception {
		cartservice.deleteCart();
		String successMessage = "Cart Removed";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return response;
	}

//*********************************************************************************************************** WISHLIST	
	@GetMapping(value="/wishlist",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> getallwishlist()
	{
		return  wishlistService.getAllwishlist();
	}
	
	@GetMapping(value="/wishlist/{buyerId}/{prodId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public WishlistDTO getSpecificWishlist(@PathVariable Integer buyerId, @PathVariable Integer prodId) {
		return wishlistService.getSpecificWishlist(buyerId, prodId);
	}
	
	@PostMapping(value="/wishlist/add", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addWishlist(@RequestBody WishlistDTO wishlist) throws Exception {
		ResponseEntity<String> resEntity=null;
		try {
			wishlistService.addWishlist(wishlist);
			resEntity= new ResponseEntity<String>("product added to Wishlist Successfully",HttpStatus.OK);
		}
		catch(Exception e)
		{
			ResponseStatusException rsc =new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()), e);
			throw rsc;
		}
		return resEntity;
	}
	
	@DeleteMapping(value = "/wishlist/{buyerId}/{prodId}")
	public ResponseEntity<String> deleteWishlist(@PathVariable Integer buyerId,@PathVariable Integer prodId) throws Exception {
		wishlistService.deleteSpecificWishlist(buyerId,prodId);
		String successMessage = "Product removed from wishlist successfully.";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping(value = "/wishlist/remove")
	public ResponseEntity<String> deleteWishlist() throws Exception {
		wishlistService.deleteWishlist();
		String successMessage = "Wishlist Removed";
		ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		return response;
	}
	
	
//	@PutMapping(value = "/wishlist/move")
//	public ResponseEntity<String> MoveToCart(@RequestBody WishlistDTO wishlistDTO,@RequestParam int quantity) throws Exception {
//
//		ResponseEntity<String> responseEntity = null;
//
//		try {
//			wishlistService.wishListMoveToCart(wishlistDTO,quantity);
//			String successMessage = environment.getProperty("wishlist.MOVE_TO_CART_SUCCESS");
//			responseEntity = new ResponseEntity<String>(successMessage, HttpStatus.OK);
//
//		} catch (Exception exception) {
//			logger.error("Error: " + exception.getMessage(), exception);
//
//			String errorMessage;
//
//			if (exception.getMessage() == null) {
//				errorMessage = environment.getProperty("General.EXCEPTION");
//				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
//				return responseEntity;
//
//			} else {
//				errorMessage = environment.getProperty(exception.getMessage());
//
//				if (errorMessage == null)
//					errorMessage = environment.getProperty("General.EXCEPTION");
//				responseEntity = new ResponseEntity<String>(errorMessage, HttpStatus.OK);
//				return responseEntity;
//
//			}
//		}
//
//		return responseEntity;
//		
//		
//	}
	
	
//	@DeleteMapping(value="/wishlist/remove")
//	public ResponseEntity<String> deletewishlist()
//	{
//		
//		ResponseEntity<String> response=null;
//		boolean status=wishlistService.deleteWishlist();
//		if(status)
//		{
//			response=new ResponseEntity<String>(environment.getProperty("DELETION_WISHLIST_ITEM"),HttpStatus.OK);
//		}
//		else
//		{
//			response=new ResponseEntity<String>(environment.getProperty("ITEM_DOES_NOT_EXIST"),HttpStatus.BAD_REQUEST);
//		}
//		return response;
//	}
		
//		@PostMapping(value="/wishlist/add", consumes=MediaType.APPLICATION_JSON_VALUE)
//		public void addWishList(@RequestBody WishlistDTO wishlist) {
//			logger.info("Adding a wishlist");
//			wishlistService.addWishList(wishlist);
//		}
//		@PostMapping(value = "/seller/login")
//		public ResponseEntity<LoginDTO> validateSellerLogin(@RequestBody LoginDTO login)  {
//			//logger.info("Adding BuyerDetails");
//			ResponseEntity<LoginDTO> temp=null;
//			try {
//				List<BuyerDTO> list=buyservice.validateLogin(login);
//				
//				temp=new ResponseEntity<LoginDTO>(login, HttpStatus.OK);
//			}
//			
//			catch(Exception e) {
//				ResponseStatusException rsc =new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()), e);
//				throw rsc;
//			}
//			return temp;
//			
//	
//			//return buyservice.getAllBuyDetails();
//		}
		
		
		
		
}
	


 