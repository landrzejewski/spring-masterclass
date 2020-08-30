package pl.training.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String nameFragment, Pageable pageable);

}
