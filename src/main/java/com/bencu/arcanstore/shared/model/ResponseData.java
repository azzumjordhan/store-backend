package com.bencu.arcanstore.shared.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    private Boolean status;
    private HttpStatus httpStatus;
    private Integer httpStatusCode;
    private ArrayList<String> messages = new ArrayList<>();
    private T data;
}
