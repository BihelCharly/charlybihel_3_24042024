package chatop.api.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int rental_id;
    @Column(unique = true)
    private int user_id;
    private String message;
    private Date created_at;
    private Date updated_at;

    public Messages() {
    }

    public Messages(int id, int rental_id, int user_id, String message, Date created_at, Date updated_at) {
        this.id = id;
        this.rental_id = rental_id;
        this.user_id = user_id;
        this.message = message;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
