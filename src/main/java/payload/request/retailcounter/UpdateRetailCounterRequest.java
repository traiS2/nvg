package payload.request.retailcounter;

import lombok.Getter;

@Getter
public class UpdateRetailCounterRequest {
    public int id;
    public String name;
    public int statusId;
}
