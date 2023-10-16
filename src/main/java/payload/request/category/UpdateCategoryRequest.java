package payload.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    private int id;
    @NotEmpty(message = "Category name is required")
    private String name;
    private int retailCounterId;
    private int statusId;
}
