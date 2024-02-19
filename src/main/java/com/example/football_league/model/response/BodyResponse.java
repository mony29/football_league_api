package com.example.football_league.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyResponse<T> {
    private Boolean success;
    private Timestamp date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    public static <T> ResponseEntity<?> getBodyResponse(T payload) {
        BodyResponse<T> response = BodyResponse.<T>builder()
                .success(true)
                .date(new Timestamp(System.currentTimeMillis()))
                .payload(payload)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
