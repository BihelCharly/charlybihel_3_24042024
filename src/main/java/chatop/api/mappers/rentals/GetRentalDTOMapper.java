package chatop.api.mappers.rentals;

import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.models.entities.Rental;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetRentalDTOMapper implements Function<Rental, GetRentalDTO> {

    public GetRentalDTO apply(@NotNull Rental rental) {
        return GetRentalDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .ownerId(rental.getOwnerId())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .build();
    }
}
