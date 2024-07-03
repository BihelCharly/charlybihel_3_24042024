package chatop.api.controller;

import chatop.api.models.request.rentals.CreateRentalDTO;
import chatop.api.models.request.rentals.UpdateRentalDTO;
import chatop.api.models.response.EmptyResponse;
import chatop.api.models.response.rental.RentalResponseDTO;
import chatop.api.models.response.rental.RentalResponse;
import chatop.api.models.response.rental.RentalsListResponse;
import chatop.api.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@Tag(name = "RENTALS", description = "Operations for Rentals")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rentals")
@ApiResponse( responseCode = "401",description = "Unauthorized", content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = EmptyResponse.class)
))
public class RentalController {

    private final RentalService rentalService;

    // TO GET ALL RENTALS
    @Operation(summary = "get all", description = "Get a list of rentals")
    @ApiResponse(responseCode = "200", description = "All rentals founded", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RentalsListResponse.class)))
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Stream<RentalResponseDTO> getAllRentals() {

        return this.rentalService.getAllRentals();

    }

    // TO GET ONE RENTAL
    @Operation(summary = "get one", description = "Get one rental by ID")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDTO.class))
    })
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalResponseDTO getOneRental(@PathVariable int id) {

        return this.rentalService.getOneRental(id);

    }

    // TO CREATE ONE RENTAL
    @Operation(summary = "create one", description = "Create one new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalResponse createOneRental(@ModelAttribute CreateRentalDTO createRentalDTO) {

        return this.rentalService.createOneRental(createRentalDTO);

    }

    // TO UPDATE ONE RENTAL
    @Operation(summary = "update one", description = "Update one existing rental by ID")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponse.class)))
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RentalResponse updateOneRental(@PathVariable int id, @ModelAttribute UpdateRentalDTO updateRentalDTO) {

        return this.rentalService.updateOneRental(id, updateRentalDTO);

    }

}
