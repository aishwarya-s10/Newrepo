package UserMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UserMS.Validator.validator;
import UserMS.dto.BuyerDTO;
import UserMS.dto.LoginDTO;
import UserMS.entity.Buyer;
import UserMS.repository.BuyerRepository;

@Service
public class BuyerService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerrepository;

//********************************************************************************** GET ALL BUYERS
	public  List<BuyerDTO> getAllBuyer()
	{
		logger.info("Fetching All Buyer Details");
		List<Buyer> buy=buyerrepository.findAll();
		List<BuyerDTO> buyerDTO= new ArrayList<>();
		for(Buyer i :buy)
		{
			BuyerDTO buyer1DTO=BuyerDTO.valueOf(i);
			buyerDTO.add(buyer1DTO);
		}
		return buyerDTO;
	}
	
//********************************************************************************** GET SPECIFIC BUYERS
	public BuyerDTO getSpecificBuyer(Integer buyerId) throws Exception
	{
		logger.info("Fetching Buyer Details for BuyerId: " +buyerId);
		BuyerDTO buyerDTO = null;
		Optional<Buyer> opt=buyerrepository.findById(buyerId);
		if(opt.isPresent())
		{
			Buyer buy=opt.get();
			buyerDTO = BuyerDTO.valueOf(buy);
		}
		else {
			throw new Exception("SERVICE.INVALID_BUYER");
		}
		return buyerDTO;
	}
	
//********************************************************************************** DELETE SPECIFIC BUYERS
	public void deleteSpecificBuyer(Integer buyerId)
	{
		logger.info("Deleting Buyer with BuyerId: " +buyerId);
		buyerrepository.deleteById(buyerId);
	}
	
//********************************************************************************** REGISTER BUYERS
	public void  registerBuyer(BuyerDTO buyerDTO) throws Exception
	   {
		   Buyer buyer = buyerDTO.createEntity();
		   validator.validateBuyer(buyer);
		   buyerrepository.save(buyer);
	   }
   
//********************************************************************************** LOGIN BUYER
   public boolean login(LoginDTO loginDTO) 
   {
	   logger.info("Login for Buyer", loginDTO.getEmail(),loginDTO.getPassword());
		Iterable<Buyer> buyer = buyerrepository.findAll();
		for(Buyer b : buyer) {
			if(b.getEmail().equals(loginDTO.getEmail()) && b.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		}
		return false;
	}


	
   
   public boolean IsPrivileged(int buyerId) {
	   Buyer buyer = new Buyer();
		if((buyer.getIsPrivileged())==false) {
			return false;
		}
		else {
			
			return true;
		}
		
	}
}

