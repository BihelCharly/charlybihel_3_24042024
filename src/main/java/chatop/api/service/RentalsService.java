package chatop.api.service;

import chatop.api.models.requests.rentals.PostRentalDTO;
import chatop.api.models.requests.rentals.PutRentalDTO;
import chatop.api.models.responses.rentals.GetRentalDTO;
import chatop.api.mappers.RentalDTOMapper;
import chatop.api.models.entities.Rental;
import chatop.api.models.responses.rentals.RentalReponse;
import chatop.api.repository.IRentalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RentalsService {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images";
    private final RentalDTOMapper rentalDTOMapper;
    private final IRentalsRepository iRentalsRepository;

    @Autowired
    ModelMapper modelMapper;

    public RentalsService(RentalDTOMapper RentalDTOMapper, IRentalsRepository iRentalsRepository) {
        this.rentalDTOMapper = RentalDTOMapper;
        this.iRentalsRepository = iRentalsRepository;
    }
    // https://www.baeldung.com/spring-boot-thymeleaf-image-upload
    // TO CREATE 1 RENTAL
    public RentalReponse createOneRental(PostRentalDTO postRentalDTO) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, postRentalDTO.getPicture().getOriginalFilename());
        fileNames.append(postRentalDTO.getPicture().getOriginalFilename());
        Files.write(fileNameAndPath, postRentalDTO.getPicture().getBytes());
        //Rental pictureConverted = Rental.builder().build(fileNames.toString());
        //iRentalsRepository.save(modelMapper.map(newDTO, Rental));
        return RentalReponse.builder().message("Rental created !"+ fileNames.toString()).build();
    }

    // TO GET ALL RENTALS
    // méthode sans modelmapper
    public Stream<GetRentalDTO> getAllRentals() {
        return this.iRentalsRepository.findAll().stream().map(rentalDTOMapper);
    }

    // TO GET ONE RENTAL
    // méthode avec modelmapper
    public GetRentalDTO getOneRental(int id) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            return modelMapper.map(optionalRental, GetRentalDTO.class);
        } else {
            return null;
        }
    }

    // TO UPDATE ONE RENTAL
    // méthode avec modelmapper
    public RentalReponse updateOneRental(int id, PutRentalDTO putRentalDTO) {
        Optional<Rental> optionalRental = this.iRentalsRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental existingRental = optionalRental.get();
            existingRental.setName(putRentalDTO.getName());
            existingRental.setSurface(putRentalDTO.getSurface());
            existingRental.setPrice(putRentalDTO.getPrice());
            existingRental.setDescription(putRentalDTO.getDescription());
            existingRental.setUpdatedAt(new Date());
            Rental updatedRental = iRentalsRepository.save(existingRental);
            modelMapper.map(updatedRental, putRentalDTO.getClass());
            return RentalReponse.builder().message("Rental updated !").build();
        } else {
            return RentalReponse.builder().message("Rental not found with ID : " + id).build();
        }
    }
}
