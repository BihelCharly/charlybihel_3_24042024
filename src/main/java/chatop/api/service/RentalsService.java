package chatop.api.service;

import chatop.api.entities.Rentals;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalsService {
    IRentalsRepository iRentalsRepository;

    public RentalsService(IRentalsRepository iRentalsRepository) {
        this.iRentalsRepository = iRentalsRepository;
    }

    public void createRentals(Rentals rentals) {
        this.iRentalsRepository.save(rentals);
    }

    public List<Rentals> getAllRentals() {
        return this.iRentalsRepository.findAll();
    }

    public Rentals getOneRentals(int id) {
        Optional<Rentals> optionalRentals = this.iRentalsRepository.findById(id);
        return optionalRentals.orElse(null);
    }
}
