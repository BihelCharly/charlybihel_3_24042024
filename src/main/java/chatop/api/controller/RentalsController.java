package chatop.api.controller;

import chatop.api.models.requests.rentals.PutRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.models.entities.Rental;
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

    // TO CREATE 1 RENTAL
    @Operation(summary = "create", description = "Create one new rental")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createOneRental(@ModelAttribute("rentals") Rental rental)
    { this.rentalsService.createOneRental(rental); }

    // TO GET ALL RENTALS
    // méthode 1 sans modelmapper
    @Operation(summary ="get all", description = "Get all rentals from RENTALS")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<GetRentalDTO> getAllRentals()
    { return this.rentalsService.getAllRentals(); }

    // TO GET ONE RENTAL
    // méthode 2 avec modelmapper
    @Operation(summary = "get one", description = "Get one rental by ID from RENTALS")
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetRentalDTO getOneRental(@PathVariable int id)
    { return rentalsService.getOneRental(id); }

    // TO UPDATE ONE RENTAL
    @Operation(summary = "update one", description = "Update one rental by ID from RENTALS")
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PutRentalDTO updateOneRental(@PathVariable int id, @ModelAttribute PutRentalDTO putRentalDTO)
    {
        return rentalsService.updateOneRental(id, putRentalDTO);
    }
}
