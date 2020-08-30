package pl.training.products;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductsMapper {

    Product toProduct(ProductTo productTo);

    ProductTo toProductTo(Product product);

    ResultPageTo<ProductTo> toResultPageTo(ResultPage<Product> resultPage);

}
