package chatop.api.exception;

import chatop.api.models.response.EmptyResponse;
import chatop.api.models.response.IResponse;
import chatop.api.models.response.message.MessageResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExcepton {

    // ERROR 401 HANDLER
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Hidden
    IResponse handleResourceNotFound(InvalidCredentialsException ex) {
        log.error("Error 401 - Unauthorized");
        return ex.getMessage() == null ? new EmptyResponse() : new MessageResponse(ex.getMessage());
    }

    // ERROR 400 HANDLER
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Hidden
    IResponse handleResourceNotFound(BadRequestException ex) {
        log.error("Error 400 - Bad Request");
        return ex.getMessage() == null ? new EmptyResponse() : new MessageResponse(ex.getMessage());
    }

}
