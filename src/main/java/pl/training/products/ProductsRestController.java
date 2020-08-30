package pl.training.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("${api-prefix}/products")
@RestController
@RequiredArgsConstructor
public class ProductsRestController {

    private final ProductsService productsService;
    private final ProductsMapper productsMapper;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<ProductTo> add(@RequestBody ProductTo productTo) {
        Product product = productsMapper.toProduct(productTo);
        Long productId = productsService.addProduct(product).getId();
        URI locationUri = uriBuilder.requestUriWithAppendedId(productId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping
    public ResultPageTo<ProductTo> getAll(@RequestParam(defaultValue = "") String nameFragment,
                                          @RequestParam(defaultValue = "0") int pageNumber,
                                          @RequestParam(defaultValue = "5") int pageSize) {
        ResultPage<Product> resultPage = productsService.getProductsByName(nameFragment, pageNumber, pageSize);
        return productsMapper.toResultPageTo(resultPage);
    }

}
