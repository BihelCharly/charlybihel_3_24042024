package chatop.api.models.response.rental;

import chatop.api.models.response.IResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalsListResponse implements IResponse {

    List<RentalResponseDTO> listOfRentalsDTO;

}
