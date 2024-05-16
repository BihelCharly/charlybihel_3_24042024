package chatop.api.mappers;

import chatop.api.dto.RentalsDTO1;
import chatop.api.models.entities.Rentals;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RentalsDTOMapper implements Function<Rentals, RentalsDTO1> {
    @Override
    public RentalsDTO1 apply(Rentals rentals) {
        return new RentalsDTO1(rentals.getId(), rentals.getName(), rentals.getSurface(), rentals.getPrice());
    }
}
