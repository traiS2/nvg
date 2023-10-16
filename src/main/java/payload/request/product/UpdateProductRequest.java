package payload.request.product;

import lombok.Getter;

@Getter
public class UpdateProductRequest {
    private int id;
    private String name;
    private String image;
    private int status_id;
    private int category_id;
}
