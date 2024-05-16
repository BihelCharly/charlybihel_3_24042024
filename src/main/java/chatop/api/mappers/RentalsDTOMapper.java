package chatop.api.mappers;

import chatop.api.dto.RentalsDTO;
import chatop.api.models.entities.Rentals;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RentalsDTOMapper implements Function<Rentals, RentalsDTO> {
    @Override
    public RentalsDTO apply(Rentals rentals) {
        return new RentalsDTO(rentals.getName(), rentals.getSurface(), rentals.getPrice());
    }
}
