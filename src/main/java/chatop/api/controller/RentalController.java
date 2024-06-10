package chatop.api.controller;

import chatop.api.models.requests.rentals.CreateRentalDTO;
import chatop.api.models.requests.rentals.UpdateRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.models.responses.rentals.RentalResponse;
import chatop.api.service.RentalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Tag(name = "RENTALS", description = "Operations for Rentals")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rentals")
public class RentalsController {

    private final RentalsService rentalsService;

    // TO GET ALL RENTALS
    @Operation(summary = "get all", description = "Get all rentals from RENTALS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All rentals founded", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GetRentalDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<GetRentalDTO> getAllRentals() {
        return this.rentalsService.getAllRentals();
    }

    // TO GET ONE RENTAL
    @Operation(summary = "get one", description = "Get one rental by ID from RENTALS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental founded", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetRentalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetRentalDTO getOneRental(@PathVariable int id) {
        return this.rentalsService.getOneRental(id);
    }

    // TO CREATE ONE RENTAL
    @Operation(summary = "create", description = "Create one new rental")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalResponse createOneRental(@ModelAttribute CreateRentalDTO createRentalDTO) {
        return this.rentalsService.createOneRental(createRentalDTO);
    }

    // TO UPDATE ONE RENTAL
    @Operation(summary = "update one", description = "Update one rental by ID from RENTALS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class)))
    })
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalResponse updateOneRental(@PathVariable int id, @ModelAttribute UpdateRentalDTO updateRentalDTO) {
        return this.rentalsService.updateOneRental(id, updateRentalDTO);
    }

}
