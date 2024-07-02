package chatop.api.models.request.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @JsonProperty(value = "user_id")
    private int userId;

    @NotNull
    @JsonProperty(value = "rental_id")
    private int rentalId;

}
