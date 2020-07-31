package pl.training.shop.products;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductTransferObject extends RepresentationModel<ProductTransferObject> {

    private Long id;
    @NotEmpty
    private String name;
    @Length(min = 3, max = 255)
    private String description;
    private String price;
    private ProductTypeTransferObject type;

}
