package chatop.api.models.request.rentals;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRentalDTO {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal surface;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
