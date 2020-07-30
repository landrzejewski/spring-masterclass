package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/products")
@RestController
@RequiredArgsConstructor
public class ProductsRestController {

    private final ProductsMapper productsMapper;
    private final ProductsService productsService;
    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<ProductTransferObject> add(@RequestBody ProductTransferObject productTransferObject) {
        var product = productsMapper.toProduct(productTransferObject);
        var productId = productsService.addProduct(product).getId();
        var locationUri = uriBuilder.requestUriWithAppendedId(productId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping
    public ResultPageTransferObject<ProductTransferObject> getByName(@RequestParam(defaultValue = "") String nameFragment,
                                                                  @RequestParam(defaultValue = "0") int pageNumber,
                                                                  @RequestParam(defaultValue = "5") int pageSize) {
        var resultPage = productsService.getProductsByName(nameFragment, pageNumber, pageSize);
        return productsMapper.toResultPageTransferObject(resultPage);
    }

}
