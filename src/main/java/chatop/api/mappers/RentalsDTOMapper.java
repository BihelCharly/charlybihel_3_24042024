package chatop.api.mappers;

import chatop.api.dto.rentals.GetRentalsDTO1;
import chatop.api.models.entities.Rentals;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RentalsDTOMapper implements Function<Rentals, GetRentalsDTO1> {
    @Override
    public GetRentalsDTO1 apply(Rentals rentals) {
        return new GetRentalsDTO1(rentals.getId(), rentals.getName(), rentals.getSurface(), rentals.getPrice());
    }
}
