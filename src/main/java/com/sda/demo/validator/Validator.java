package com.sda.demo.validation;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static void validateException(BindingResult result) {
        if (!result.getFieldErrors().isEmpty()) {
            List<String> listOfErrors = new ArrayList<>();
            result.getFieldErrors().forEach(e -> listOfErrors.add(listOfErrors.size() + 1 + ": " +
                    e.getDefaultMessage()));

            throw new RuntimeException(listOfErrors.toString());
        }
    }

}
