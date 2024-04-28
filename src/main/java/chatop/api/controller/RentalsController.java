package chatop.api.controller;

import chatop.api.entities.Rentals;
import chatop.api.service.RentalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rentals")
public class RentalsController {

    private final RentalsService rentalsService;

    public RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createRentals(Rentals rentals){
        this.rentalsService.createRentals(rentals);

    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rentals> getAllRentals() {
        return this.rentalsService.getAllRentals();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Rentals getOneRentals(@PathVariable int id) {
        return this.rentalsService.getOneRentals(id);
    }
}
