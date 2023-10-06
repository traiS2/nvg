package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer category_id;
    private String name;
    private StatusDTO status;
}
