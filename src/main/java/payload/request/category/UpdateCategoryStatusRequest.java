package payload.request.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCategoryStatusRequest {
    private int category_id;
    private int status_id;
}
