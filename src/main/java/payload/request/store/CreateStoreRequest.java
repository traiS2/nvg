package payload.request.store;

import lombok.Getter;

@Getter
public class CreateStoreRequest {
    private Long userId;
    private String name;
    private int provinceId;
    private int districtId;
    private int communeId;
    private String detailAddress;
}
