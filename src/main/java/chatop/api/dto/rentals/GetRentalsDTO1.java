package chatop.api.dto.rentals;

import java.math.BigDecimal;

public record GetRentalsDTO1(

        int id,
        String name,
        BigDecimal surface,
        BigDecimal price
)
                          {
}
