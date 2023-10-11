package payload.response.category;

import dto.CategoryDTO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllCategoryResponse {
    private List<CategoryDTO> categories;

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
