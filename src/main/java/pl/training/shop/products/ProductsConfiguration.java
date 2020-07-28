package pl.training.shop.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsConfiguration {

    @Bean
    public ProductService productsService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}
