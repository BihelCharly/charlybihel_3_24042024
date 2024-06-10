package chatop.api.service;

import chatop.api.converter.rental.CreateRentalDTOConverter;
import chatop.api.converter.rental.UpdateRentalDTOConverter;
import chatop.api.models.requests.rentals.CreateRentalDTO;
import chatop.api.models.requests.rentals.UpdateRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.mapper.rental.GetRentalDTOMapper;
import chatop.api.models.entity.Rental;
import chatop.api.models.responses.rentals.RentalResponse;
import chatop.api.repository.IRentalsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RentalsService {

    private final CreateRentalDTOConverter createRentalDTOConverter;
    private final UpdateRentalDTOConverter updateRentalDTOConverter;
    private final GetRentalDTOMapper getRentalDTOMapper;
    private final StorageService storageService;
    private final IRentalsRepository iRentalsRepository;

    @Autowired
    ModelMapper modelMapper;

    // TO GET ALL RENTALS
    public Stream<GetRentalDTO> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(getRentalDTOMapper);
    }

    // TO GET ONE RENTAL
    public GetRentalDTO getOneRental(int id) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            return modelMapper.map(optionalRental, GetRentalDTO.class);
        } else {
            return null;
        }
    }

    // TO CREATE ONE RENTAL
    public RentalResponse createOneRental(CreateRentalDTO createRentalDTO) {
        // CHECK IF RENTAL ALREADY EXISTS BY NAME
        Optional<Rental> optionalRental = this.iRentalsRepository.findByName(createRentalDTO.getName());
        // IF RENTAL DOESNT EXIST THEN
        if (optionalRental.isEmpty()) {
            // CONVERT DTO TO ENTITY WITH CONVERTER AND RETURN RESPONSE
            Rental newRental = createRentalDTOConverter.convert(createRentalDTO);
            if (newRental != null) {
                newRental.setCreatedAt(new Date());
                iRentalsRepository.save(newRental);
                return RentalResponse.builder().message("Rental created !").build();
            }
            // IF RENTAL DOESNT EXIST THEN RETURN RESPONSE
        } else {
            return RentalResponse.builder().message("Rental already exists with this name").build();
        }
        return null;
    }

    // TO UPDATE ONE RENTAL
    public RentalResponse updateOneRental(int id, UpdateRentalDTO updateRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();
            updateRentalDTOConverter.convert(existingRental);
            Rental updatedRental = iRentalsRepository.save(existingRental);
            modelMapper.map(updatedRental, updateRentalDTO.getClass());
            return RentalResponse.builder().message("Rental updated !").build();
        } else {
            return RentalResponse.builder().message("Rental not found with ID : " + id).build();
        }
    }
}
