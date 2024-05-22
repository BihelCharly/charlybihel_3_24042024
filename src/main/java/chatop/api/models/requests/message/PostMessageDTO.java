package chatop.api.models.requests.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostMessageDTO {

    private String message;

    private Integer userId;

    private Integer rentalId;
}
