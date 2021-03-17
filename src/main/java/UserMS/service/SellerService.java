package UserMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UserMS.Validator.validator;
import UserMS.dto.LoginDTO;
import UserMS.dto.SellerDTO;
import UserMS.entity.Seller;
import UserMS.repository.SellerRepository;

@Service
public class SellerService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerRepository sellerrepository;

//******************************************************************************** GET ALL SELLER
	public  List<SellerDTO> getAllSeller()
	{
		logger.info("Fetching All Seller Details");
		List<Seller> sells=sellerrepository.findAll();
		List<SellerDTO> sellDTO= new ArrayList<>();
		for(Seller i :sells)
		{
			SellerDTO sellerDTO=SellerDTO.valueOf(i);
			sellDTO.add(sellerDTO);
		}
		return sellDTO;
	}
	
//******************************************************************************** GET SPECIFIC SELLER
	public SellerDTO getSpecificSeller(int sellerId )throws Exception
	{
		logger.info("Fetching Seller Details for SellerId: " +sellerId);
		SellerDTO sellDTO=null;
		Optional<Seller> optseller=sellerrepository.findById(sellerId);
		if(optseller.isPresent())
		{
			Seller sell=optseller.get();
			sellDTO=SellerDTO.valueOf(sell);
		}
		else {
			throw new Exception("SERVICE.INVALID_SELLER");
		}
		return sellDTO;
	}
	
//******************************************************************************** DELETE SPECIFIC SELLER
	public void deleteSpecificSeller(int sellerId)
	{
		logger.info("Deleting Seller with SellerId: " +sellerId);
		sellerrepository.deleteById(sellerId);
	}
	
//******************************************************************************** REGISTER SELLER
   public void  registerSeller(SellerDTO sellerDTO) throws Exception
   {
	   logger.info("Successfully Registered"); 
	   Seller seller = sellerDTO.createEntity();
	   validator.validateSeller(seller);
	   sellerrepository.save(seller);
   }
   
//******************************************************************************** LOGIN FOR SELLER
   public boolean login(LoginDTO loginDTO) throws Exception
   {
	   logger.info("Login for Seller", loginDTO.getEmail(),loginDTO.getPassword());
		List<Seller> seller = sellerrepository.findAll();
		for(Seller s : seller) {
			if(s.getEmail().equals(loginDTO.getEmail()) && s.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		}
		return false;
	}
   
}
