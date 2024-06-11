package chatop.api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StorageService {

    // DIRECTORY TO SAVE IMAGES
    private final Path imagesDirectory = Paths.get("src/main/resources/static/images");

    @Value("${app.baseUrl}")
    private String baseUrl;

    public String uploadPicture(MultipartFile picture) {
        try {
            createDirectory(imagesDirectory);
            String fileName = createFileName(picture);
            Path targetLocation = imagesDirectory.resolve(fileName);
            Files.copy(picture.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return baseUrl + "/static/images/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDirectory(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }

    private String createFileName(MultipartFile picture) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));
        String fileNameWithoutExtension = originalFileName;
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            fileNameWithoutExtension = originalFileName.substring(0, dotIndex);
            fileExtension = originalFileName.substring(dotIndex);
        }
        return RandomStringUtils.randomAlphanumeric(8) + "_" + fileNameWithoutExtension + fileExtension;
    }
}
