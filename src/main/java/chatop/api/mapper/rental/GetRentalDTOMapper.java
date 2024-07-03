package chatop.api.mapper.rental;

import chatop.api.models.response.rental.RentalResponseDTO;
import chatop.api.models.entity.Rental;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetRentalDTOMapper implements Function<Rental, RentalResponseDTO> {

    public RentalResponseDTO apply(@NotNull Rental rental) {
        return RentalResponseDTO.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .ownerId(rental.getOwnerId())
                .build();
    }
}
