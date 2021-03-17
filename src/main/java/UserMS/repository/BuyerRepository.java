package UserMS.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import UserMS.dto.BuyerDTO;
import UserMS.entity.Buyer;
import UserMS.entity.CompositeKey;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
	Buyer getByEmail(String email);
	Optional<Buyer> findById(Integer buyerId);
	//Optional<Seller> getByPhonenumber(String phonenumber);
}
