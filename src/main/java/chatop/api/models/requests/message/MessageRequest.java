package chatop.api.models.requests.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    private String message;
    @JsonProperty(value = "user_id")
    private int userId;
    @JsonProperty(value = "rental_id")
    private int rentalId;

    public MessageRequest() {
    }

    public MessageRequest(String message, int userId, int rentalId) {
        this.message = message;
        this.userId = userId;
        this.rentalId = rentalId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }
}
