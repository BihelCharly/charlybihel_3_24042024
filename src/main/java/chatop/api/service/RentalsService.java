package chatop.api.service;

import chatop.api.entities.Rentals;
import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentalsService {
    IRentalsRepository iRentalsRepository;

    public RentalsService(IRentalsRepository iRentalsRepository) {
        this.iRentalsRepository = iRentalsRepository;
    }

    public void createRentals(int id, Rentals rentals) {
        rentals.setId(id);
        this.iRentalsRepository.save(rentals);
    }

    public List<Rentals> getAllRentals() {
        return this.iRentalsRepository.findAll();
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
