package chatop.api.service;

import chatop.api.models.requests.rentals.PostRentalDTO;
import chatop.api.models.requests.rentals.PutRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.mappers.RentalDTOMapper;
import chatop.api.models.entities.Rental;
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

    private final RentalDTOMapper rentalDTOMapper;
    private final StorageService storageService;
    private final IRentalsRepository iRentalsRepository;

    @Autowired
    ModelMapper modelMapper;

    // TO GET ALL RENTALS
    public Stream<GetRentalDTO> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(rentalDTOMapper);
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
    public RentalResponse createOneRental(PostRentalDTO postRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findByName(postRentalDTO.getName());
        if (optionalRental.isEmpty()) {
            Rental newRental = new Rental();
            newRental.setName(postRentalDTO.getName());
            newRental.setSurface(postRentalDTO.getSurface());
            newRental.setPrice(postRentalDTO.getPrice());
            newRental.setPicture(storageService.uploadPicture(postRentalDTO.getPicture()));
            newRental.setDescription(postRentalDTO.getDescription());
            iRentalsRepository.save(newRental);
            return RentalResponse.builder().message("Rental created !").build();
        } else {
            return RentalResponse.builder().message("Rental already exists with this name").build();
        }
    }


    // TO UPDATE ONE RENTAL
    public RentalResponse updateOneRental(int id, PutRentalDTO putRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();
            existingRental.setName(putRentalDTO.getName());
            existingRental.setSurface(putRentalDTO.getSurface());
            existingRental.setPrice(putRentalDTO.getPrice());
            existingRental.setDescription(putRentalDTO.getDescription());
            existingRental.setUpdatedAt(new Date());
            Rental updatedRental = iRentalsRepository.save(existingRental);
            modelMapper.map(updatedRental, putRentalDTO.getClass());
            return RentalResponse.builder().message("Rental updated !").build();
        } else {
            return RentalResponse.builder().message("Rental not found with ID : " + id).build();
        }
    }
}
