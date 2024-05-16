package chatop.api.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "RENTALS")
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float surface;
    private float price;
    private byte[] picture;
    private String description;
    private int owner_id;
    private Date created_at;
    private Date updated_at;
    private int ownerd_id;
}
