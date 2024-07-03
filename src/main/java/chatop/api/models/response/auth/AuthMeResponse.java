package chatop.api.models.response.auth;

import chatop.api.models.response.IResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AuthMeResponse implements IResponse {

    private int id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updateAt;
}
