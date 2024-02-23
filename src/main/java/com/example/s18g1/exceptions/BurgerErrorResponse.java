package com.example.s18g1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BurgerErrorResponse {
    private String message;
    private Integer status;
    private long timestamp;
}
