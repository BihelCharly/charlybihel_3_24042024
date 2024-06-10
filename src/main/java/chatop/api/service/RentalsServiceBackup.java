package chatop.api.service;

import chatop.api.mappers.rentals.GetRentalDTOMapper;
import chatop.api.models.entities.Rental;
import chatop.api.models.requests.rentals.CreateRentalDTO;
import chatop.api.models.requests.rentals.UpdateRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
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
public class RentalsServiceBackup {

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
        Optional<Rental> optionalRental = this.iRentalsRepository.findByName(createRentalDTO.getName());
        if (optionalRental.isEmpty()) {
            Rental newRental = new Rental();
            newRental.setName(createRentalDTO.getName());
            newRental.setSurface(createRentalDTO.getSurface());
            newRental.setPrice(createRentalDTO.getPrice());
            newRental.setPicture(storageService.uploadPicture(createRentalDTO.getPicture()));
            newRental.setDescription(createRentalDTO.getDescription());
            newRental.setCreatedAt(new Date());
            iRentalsRepository.save(newRental);
            return RentalResponse.builder().message("Rental created !").build();
        } else {
            return RentalResponse.builder().message("Rental already exists with this name").build();
        }
    }

    // TO UPDATE ONE RENTAL
    public RentalResponse updateOneRental(int id, UpdateRentalDTO updateRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();
            existingRental.setName(updateRentalDTO.getName());
            existingRental.setSurface(updateRentalDTO.getSurface());
            existingRental.setPrice(updateRentalDTO.getPrice());
            existingRental.setDescription(updateRentalDTO.getDescription());
            existingRental.setUpdatedAt(new Date());
            Rental updatedRental = iRentalsRepository.save(existingRental);
            modelMapper.map(updatedRental, updateRentalDTO.getClass());
            return RentalResponse.builder().message("Rental updated !").build();
        } else {
            return RentalResponse.builder().message("Rental not found with ID : " + id).build();
        }
    }
}
