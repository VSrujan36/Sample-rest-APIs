package com.example.amex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class AdditionController {

    @GetMapping("/add/{number1}/{number2}/{number3}")
    public String additionThree(@PathVariable Integer number1, @PathVariable Integer number2, @PathVariable Integer number3) {
        int i = number1 + number2 + number3;
        String additionResultt =  String.valueOf(i);
        return additionResultt;
    }
}
