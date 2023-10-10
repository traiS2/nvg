package payload.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateCategoryStatusRequest {
    @NotEmpty(message = "Category id is required")
    private Integer category_id;
    @NotEmpty(message = "Status id is required")
    private Integer status_id;
}
