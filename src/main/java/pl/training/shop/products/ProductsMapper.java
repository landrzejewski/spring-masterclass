package pl.training.shop.products;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    Product toProduct(ProductTransferObject productTransferObject);

    ProductTransferObject toProductTransferObject(Product product);

    ResultPageTransferObject<ProductTransferObject> toResultPageTransferObject(ResultPage<Product> resultPage);

}
