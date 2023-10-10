package payload.request.category;

import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
    private Integer category_id;
    private String name;
}
