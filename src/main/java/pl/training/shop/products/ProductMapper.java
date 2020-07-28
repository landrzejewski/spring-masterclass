package pl.training.shop.products;

import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.payments.LocalMoney;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default FastMoney toFastMoney(String price) {
        if (price == null) {
            return LocalMoney.of(0);
        }
        return FastMoney.parse(price);
    }

    default String toPrice(FastMoney price) {
        if (price == null) {
            return "";
        }
        return price.toString();
    }

    Product toProduct(ProductTransferObject productTransferObject);

    ProductTransferObject toProductTransferObject(Product product);

    PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage);

    @ValueMapping(source = "BOOK", target = "EBOOK")
    @ValueMapping(source = "AUDIO", target = "MUSIC")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductTypeTransferObject toProductTypeTransferObject(ProductType productType);

    @ValueMapping(source = "EBOOK", target = "BOOK")
    @ValueMapping(source = "MUSIC", target = "AUDIO")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductType toProductType(ProductTypeTransferObject productTypeTransferObject);

}
