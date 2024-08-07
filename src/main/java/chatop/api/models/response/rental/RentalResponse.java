package chatop.api.models.response.rental;

import chatop.api.models.response.IResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse implements IResponse {

    private String message;

}
