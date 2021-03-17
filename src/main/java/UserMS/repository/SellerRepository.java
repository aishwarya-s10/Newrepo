package UserMS.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import UserMS.entity.CompositeKey;
import UserMS.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
	Seller getByEmail(String email);

	//Optional<Seller> getByPhonenumber(String phonenumber);
}
