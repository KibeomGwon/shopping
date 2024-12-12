package shop._8.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import shop._8.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
