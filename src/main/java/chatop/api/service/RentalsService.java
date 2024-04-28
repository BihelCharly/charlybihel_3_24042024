package chatop.api.service;

import chatop.api.repository.IRentalsRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalsService {
    IRentalsRepository iRentalsRepository;

    public RentalsService(IRentalsRepository iRentalsRepository) {
        this.iRentalsRepository = iRentalsRepository;
    }
}
