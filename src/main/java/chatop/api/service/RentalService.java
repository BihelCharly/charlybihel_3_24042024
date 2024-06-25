package chatop.api.service;

import chatop.api.converter.rental.CreateRentalDTOConverter;
import chatop.api.converter.rental.UpdateRentalDTOConverter;
import chatop.api.models.entity.User;
import chatop.api.models.request.rentals.CreateRentalDTO;
import chatop.api.models.request.rentals.UpdateRentalDTO;
import chatop.api.models.response.rental.GetRentalResponseDTO;
import chatop.api.mapper.rental.GetRentalDTOMapper;
import chatop.api.models.entity.Rental;
import chatop.api.models.response.rental.RentalResponse;
import chatop.api.repository.IRentalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final GetRentalDTOMapper getRentalDTOMapper;
    private final CreateRentalDTOConverter createRentalDTOConverter;
    private final UpdateRentalDTOConverter updateRentalDTOConverter;
    private final IRentalRepository iRentalRepository;

    @Autowired
    ModelMapper modelMapper;

    // TO GET ALL RENTALS
    public Stream<GetRentalResponseDTO> getAllRentals() {
        return this.iRentalRepository.findAll().stream().map(getRentalDTOMapper);
    }

    // TO GET ONE RENTAL
    public GetRentalResponseDTO getOneRental(int id) {
        Optional<Rental> optionalRental = this.iRentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            return modelMapper.map(optionalRental, GetRentalResponseDTO.class);
        } else {
            return null;
        }
    }

    // TO CREATE ONE RENTAL
    public RentalResponse createOneRental(CreateRentalDTO createRentalDTO) {
        // CHECK IF RENTAL ALREADY EXISTS BY NAME
        Optional<Rental> optionalRental = this.iRentalRepository.findByName(createRentalDTO.getName());
        // IF RENTAL DOESN'T EXIST THEN
        if (optionalRental.isEmpty()) {
            // CONVERT DTO TO ENTITY WITH CONVERTER AND RETURN RESPONSE
            Rental newRental = createRentalDTOConverter.convert(createRentalDTO);
            if (newRental != null) {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                newRental.setOwnerId(user.getId());
                newRental.setCreatedAt(new Date());
                iRentalRepository.save(newRental);
                return RentalResponse.builder().message("Rental created !").build();
            }
            // IF RENTAL DOESN'T EXIST THEN RETURN RESPONSE
        } else {
            return RentalResponse.builder().message("Rental already exists with this name").build();
        }
        return null;
    }

    // TO UPDATE ONE RENTAL
    public RentalResponse updateOneRental(int id, UpdateRentalDTO updateRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();

            existingRental.setName(updateRentalDTO.getName());
            existingRental.setSurface(updateRentalDTO.getSurface());
            existingRental.setPrice(updateRentalDTO.getPrice());
            existingRental.setDescription(updateRentalDTO.getDescription());
            existingRental.setUpdatedAt(new Date());

            Rental updatedRental = updateRentalDTOConverter.convert(updateRentalDTO);
            iRentalRepository.save(existingRental);

            return RentalResponse.builder().message("Rental updated !").build();
        } else {
            return RentalResponse.builder().message("Rental not found with ID : " + id).build();
        }
    }
}
