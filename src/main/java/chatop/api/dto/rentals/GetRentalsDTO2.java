package chatop.api.dto.rentals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRentalsDTO2 {
    private int id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
}
