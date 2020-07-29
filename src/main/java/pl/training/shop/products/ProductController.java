package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;

@RequestMapping("api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    public ResponseEntity<ProductTransferObject> addProduct(@Valid @RequestBody ProductTransferObject productTransferObject, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toProduct(productTransferObject);
        var productId = productService.add(product).getId();
        var locationUri = uriBuilder.requestUriWithId(productId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping
    public PagedResultTransferObject<ProductTransferObject> getProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {
        var products = productService.getAll(pageNumber, pageSize);
        return productMapper.toProductTransferObjectsPage(products);
    }

    // Check MvcConfiguration and WebInitializer config
    @RequestMapping(value = "{id}/files", method = RequestMethod.POST)
    public String submit(@PathVariable Long id, @RequestParam MultipartFile file) {
        // Save file to some kind of storage
        return "File " + file.getOriginalFilename() + " uploaded";
    }

}
