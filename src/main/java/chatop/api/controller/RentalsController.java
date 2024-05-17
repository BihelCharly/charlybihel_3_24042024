package chatop.api.controller;

import chatop.api.dto.rentals.GetRentalsDTO1;
import chatop.api.dto.rentals.GetRentalsDTO2;
import chatop.api.models.entities.Rentals;
import chatop.api.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Tag(name="RENTALS", description = "Operations for Rentals")
@RestController
@RequestMapping(path = "/rentals")
public class RentalsController {

    private final RentalsService rentalsService;

    public RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @Operation(summary = "create", description = "Create one new rental")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRentals(@ModelAttribute("rentals") Rentals rentals){
        this.rentalsService.createRentals(rentals);
    }

    //@Operation(summary = "get one", description = "Get one rental by ID from RENTALS")
    //@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //public Rentals getOneRentals(@PathVariable int id) {
    //    return this.rentalsService.getOneRentals(id);
    //}

    // méthode 1 sans modelmapper
    @Operation(summary ="get all", description = "Get all rentals from RENTALS")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<GetRentalsDTO1> getAllRentals() {
        return this.rentalsService.getAllRentals();
    }

    // méthode 2 avec modelmapper
    @Operation(summary = "get one", description = "Get one rental by ID from RENTALS")
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetRentalsDTO2 getOneRentals(@PathVariable int id) {
        return rentalsService.getOneRentals(id);
    }

}
