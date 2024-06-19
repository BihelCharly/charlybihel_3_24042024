package chatop.api.converter.rental;

import chatop.api.models.entity.Rental;
import chatop.api.models.request.rentals.UpdateRentalDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateRentalDTOConverter implements Converter<UpdateRentalDTO, Rental> {

    public Rental convert(@NotNull UpdateRentalDTO updateRentalDTO) {
        return Rental.builder()
                .name(updateRentalDTO.getName())
                .surface(updateRentalDTO.getSurface())
                .price(updateRentalDTO.getPrice())
                .description(updateRentalDTO.getDescription())
                .build();
    }
}
