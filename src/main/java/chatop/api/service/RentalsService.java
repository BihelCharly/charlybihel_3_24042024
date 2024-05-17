package chatop.api.service;

import chatop.api.models.requests.rentals.PutRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.mappers.RentalDTOMapper;
import chatop.api.models.entities.Rental;
import chatop.api.repository.IRentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RentalsService {

    private final RentalDTOMapper rentalDTOMapper;
    private final IRentalsRepository iRentalsRepository;

    @Autowired
    ModelMapper modelMapper;

    public RentalsService(RentalDTOMapper RentalDTOMapper, IRentalsRepository iRentalsRepository) {
        this.rentalDTOMapper = RentalDTOMapper;
        this.iRentalsRepository = iRentalsRepository;
    }

    // TO CREATE 1 RENTAL
    public void createOneRental(Rental rental) {
        this.iRentalsRepository.save(rental);
    }

    // TO GET ALL RENTALS
    // méthode sans modelmapper
    public Stream<GetRentalDTO> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(rentalDTOMapper);
    }

    // TO GET ONE RENTAL
    // méthode avec modelmapper
    public GetRentalDTO getOneRental(int id) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            return modelMapper.map(optionalRental, GetRentalDTO.class);
        }
        return null;
    }

    // TO UPDATE ONE RENTAL
    // méthode avec modelmapper
    public PutRentalDTO updateOneRental(int id, PutRentalDTO putRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();
            existingRental.setName(putRentalDTO.getName());
            existingRental.setSurface(putRentalDTO.getSurface());
            existingRental.setPrice(putRentalDTO.getPrice());
            existingRental.setDescription(putRentalDTO.getDescription());
            Rental updatedRental = iRentalsRepository.save(existingRental);
            return modelMapper.map(updatedRental, putRentalDTO.getClass());
        }
        return null;
    }
}
