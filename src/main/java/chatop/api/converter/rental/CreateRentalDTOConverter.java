package chatop.api.converter.rental;

import chatop.api.models.entity.Rental;
import chatop.api.models.request.rentals.CreateRentalDTO;
import chatop.api.service.StorageService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateRentalDTOConverter implements Converter<CreateRentalDTO, Rental> {

    private final StorageService storageService;

    public Rental convert(@NotNull CreateRentalDTO createRentalDTO) {
        return Rental.builder()
                .name(createRentalDTO.getName())
                .surface(createRentalDTO.getSurface())
                .price(createRentalDTO.getPrice())
                .picture(storageService.uploadPicture(createRentalDTO.getPicture()))
                .description(createRentalDTO.getDescription())
                .build();
    }
}
