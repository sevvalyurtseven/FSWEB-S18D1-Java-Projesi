package com.example.s18g1.util;

import com.example.s18g1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void checkName(String name){
    if(name == null || name.isEmpty()){
        throw new BurgerException("name cannot be null or empty!", HttpStatus.BAD_REQUEST);
    }
}
}
