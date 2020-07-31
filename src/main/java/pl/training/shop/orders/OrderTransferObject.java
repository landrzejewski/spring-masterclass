package pl.training.shop.orders;

import lombok.Data;
import pl.training.shop.common.web.IdTransferObject;

import java.util.List;

@Data
public class OrderTransferObject {

    private List<IdTransferObject> products;

}
