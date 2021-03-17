package UserMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UserMS.entity.CompositeKey;
import UserMS.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, CompositeKey> {
//public List<Wishlist> findByIdBuyerId(int buyerId);
	
	//public Wishlist findByIdBuyerIdAndIdProdId(int buyerId,int ProdId);
}