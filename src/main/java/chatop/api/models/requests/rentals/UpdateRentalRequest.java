package chatop.api.models.requests.rentals;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateRentalRequest {
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal surface;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;

    public UpdateRentalRequest() {
    }

    public UpdateRentalRequest(String name, BigDecimal surface, BigDecimal price, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSurface() {
        return surface;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
