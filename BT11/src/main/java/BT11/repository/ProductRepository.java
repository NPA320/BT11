package BT11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BT11.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
