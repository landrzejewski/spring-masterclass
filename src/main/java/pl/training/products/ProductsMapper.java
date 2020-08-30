package pl.training.products;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    Product toProduct(ProductTo productTo);

    ProductTo toProductTo(Product product);

    ResultPageTo<ProductTo> toResultPageTo(ResultPage<Product> resultPage);

}
