package pl.training.search.github;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Repository {

    private String name;
    private String description;

}
