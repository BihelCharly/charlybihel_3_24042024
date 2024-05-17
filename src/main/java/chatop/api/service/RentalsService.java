package chatop.api.service;

import chatop.api.dto.rentals.GetRentalsDTO1;
import chatop.api.dto.rentals.GetRentalsDTO2;
import chatop.api.mappers.RentalsDTOMapper;
import chatop.api.models.entities.Rentals;
import chatop.api.repository.IRentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RentalsService {

    private final RentalsDTOMapper rentalsDTOMapper;
    private final IRentalsRepository iRentalsRepository;

    @Autowired
    ModelMapper modelMapper;

    public RentalsService(RentalsDTOMapper rentalsDTOMapper, IRentalsRepository iRentalsRepository) {
        this.rentalsDTOMapper = rentalsDTOMapper;
        this.iRentalsRepository = iRentalsRepository;
    }

    public void createRentals(Rentals rentals) {
        this.iRentalsRepository.save(rentals);
    }

    public Stream<GetRentalsDTO1> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(rentalsDTOMapper);
    }

    public GetRentalsDTO2 convertEntityToDto(Rentals rentals) {
        return modelMapper.map(rentals, GetRentalsDTO2.class);
    }

    public GetRentalsDTO2 getOneRentals(int id) {
        Optional<Rentals> optionalRentals = this.iRentalsRepository.findById(id);
        if (optionalRentals.isPresent()) {
            return convertEntityToDto(optionalRentals.orElse(null));
        }
        return null;
    }

}
