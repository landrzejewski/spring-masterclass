package pl.training.shop.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagedResultTransferObject<T> extends RepresentationModel<PagedResultTransferObject<T>> {

    private List<T> data;
    private int pageNumber;
    private int totalPages;

}
