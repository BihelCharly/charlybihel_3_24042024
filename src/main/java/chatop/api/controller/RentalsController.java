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

    // je n'arrive pas à faire passer le champ picture pour le fichier image
    // quand je supprime le champ picture ça fonctionne ; Mais j'ai une erreur quand je change l'id dans postman à 2 ou 3 ...
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createRentals( @PathVariable int id, Rentals rentals){
        this.rentalsService.createRentals(id, rentals);

    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rentals> getAllRentals() {
        return this.rentalsService.getAllRentals();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Rentals getOneRentals(@PathVariable int id) {
        return this.rentalsService.getOneRentals(id);
    }

    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Rentals updateRentals(@PathVariable int id , Rentals rentals) {
        return this.rentalsService.updateRentals(id, rentals);
    }
}
