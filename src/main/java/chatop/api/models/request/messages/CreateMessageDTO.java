package chatop.api.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageDTO {

    private String message;

    private Integer userId;

    private Integer rentalId;

}
