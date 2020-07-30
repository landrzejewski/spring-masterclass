package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Product addProduct(Product product) {
        return productsRepository.saveAndFlush(product);
    }

    public ResultPage<Product> getProductsByName(String nameFragment, int pageNumber, int pageSize) {
        var productsPage = productsRepository.findByNameContaining(nameFragment, PageRequest.of(pageNumber, pageSize));
        return new ResultPage<>(productsPage.getContent(), pageNumber, productsPage.getTotalPages());
    }

}
