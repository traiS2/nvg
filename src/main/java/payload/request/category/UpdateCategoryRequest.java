package payload.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    private int category_id;
    @NotEmpty(message = "Category name is required")
    private String name;
}
