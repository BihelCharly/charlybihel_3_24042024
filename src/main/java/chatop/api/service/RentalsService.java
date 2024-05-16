package chatop.api.service;

import chatop.api.dto.RentalsDTO;
import chatop.api.mappers.RentalsDTOMapper;
import chatop.api.models.entities.Rentals;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RentalsService {

    private final RentalsDTOMapper rentalsDTOMapper;
    private final IRentalsRepository iRentalsRepository;

    public RentalsService(RentalsDTOMapper rentalsDTOMapper, IRentalsRepository iRentalsRepository) {
        this.rentalsDTOMapper = rentalsDTOMapper;
        this.iRentalsRepository = iRentalsRepository;
    }

    public void createRentals(Rentals rentals) {
        this.iRentalsRepository.save(rentals);
    }

    public Stream<RentalsDTO> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(rentalsDTOMapper);
    }

    public Rentals getOneRentals(int id) {
        Optional<Rentals> optionalRentals = this.iRentalsRepository.findById(id);
        return optionalRentals.orElse(null);
    }

    public Rentals updateRentals(int id, Rentals rentals) {
        Rentals rentalsInDataBase = this.getOneRentals(id);
        if(rentalsInDataBase.getId() == rentals.getId()) {
            rentalsInDataBase.setName(rentals.getName());
            rentalsInDataBase.setSurface(rentals.getSurface());
            rentalsInDataBase.setPrice(rentals.getPrice());
            rentalsInDataBase.setDescription(rentals.getDescription());
            rentals.setUpdated_at(new Date());
            rentalsInDataBase.setUpdated_at(rentals.getUpdated_at());
            this.iRentalsRepository.save(rentalsInDataBase);
        }
        return rentalsInDataBase;
    }
}
