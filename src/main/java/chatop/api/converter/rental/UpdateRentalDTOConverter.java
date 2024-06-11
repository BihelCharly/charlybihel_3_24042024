package chatop.api.converter.rental;

import chatop.api.models.entity.Rental;
import chatop.api.models.request.rentals.UpdateRentalDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateRentalDTOConverter implements Converter<Rental, UpdateRentalDTO> {

    public UpdateRentalDTO convert(@NotNull Rental rental) {
        return UpdateRentalDTO.builder()
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .description(rental.getDescription())
                .build();
    }
}
