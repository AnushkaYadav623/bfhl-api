package in.acropolis.bfhl.exception;

import in.acropolis.bfhl.config.BfhlUserProperties;
import in.acropolis.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final BfhlUserProperties userProperties;

    public GlobalExceptionHandler(BfhlUserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> handleValidation(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse());
    }

    private BfhlResponse errorResponse() {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setUserId(buildUserId());
        response.setEmail(userProperties.getEmail());
        response.setRollNumber(userProperties.getRollNumber());
        response.setOddNumbers(new ArrayList<>());
        response.setEvenNumbers(new ArrayList<>());
        response.setAlphabets(new ArrayList<>());
        response.setSpecialCharacters(new ArrayList<>());
        response.setSum("0");
        response.setConcatString("");
        return response;
    }

    private String buildUserId() {
        String namePart = userProperties.getFullName()
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "_");
        String dobPart = userProperties.getDob().replace("/", "");
        return namePart + "_" + dobPart;
    }
}
