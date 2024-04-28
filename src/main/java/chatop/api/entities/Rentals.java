package chatop.api.entities;


import jakarta.persistence.*;

import java.util.Date;

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
    private  String description;
    private int owner_id;
    private Date created_at;
    private Date updated_at;
    private int ownerd_id;

    public Rentals() {
    }

    public Rentals(int id, String name, float surface, float price, byte[] picture, String description, int owner_id, Date created_at, Date updated_at, int ownerd_id) {
        this.id = id;
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.owner_id = owner_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.ownerd_id = ownerd_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getOwnerd_id() {
        return ownerd_id;
    }

    public void setOwnerd_id(int ownerd_id) {
        this.ownerd_id = ownerd_id;
    }
}
