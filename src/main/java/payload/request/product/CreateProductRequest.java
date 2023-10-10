package payload.request.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CreateProductRequest {
    @NotEmpty(message = "Product name is required")
    private String name;
    @NotEmpty(message = "Product description is required")
    private String image;
    private int category_id;
}
