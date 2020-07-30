package pl.training.shop.products;

import lombok.Data;

@Data
public class ProductTransferObject {

    private String name;
    private String description;
    private long price;

}
