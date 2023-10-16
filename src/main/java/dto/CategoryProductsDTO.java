package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductsDTO {
    private Integer id;
    private String name;
    private List<ProductDTO> products;
    private StatusDTO status;
}
