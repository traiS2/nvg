package payload.request.category;

import lombok.Getter;

@Getter
public class CreateCategoryRequest {
    private String name;
    private int retailCounterId;
}
