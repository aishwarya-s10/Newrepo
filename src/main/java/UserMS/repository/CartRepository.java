package UserMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import UserMS.entity.Cart;
import UserMS.entity.CompositeKey;

@Repository
public interface CartRepository extends JpaRepository<Cart, CompositeKey> {

}
