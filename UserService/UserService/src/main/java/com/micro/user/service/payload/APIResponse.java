package com.micro.user.service.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class APIResponse<T> {

    private List<T> data;
    private String message;
    private boolean success;
    private HttpStatus status;


}
