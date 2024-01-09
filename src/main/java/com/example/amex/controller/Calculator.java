package com.example.amex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class Calculator {
    @GetMapping("/add/{number1}/{number2}")
    public String addition(@PathVariable Integer number1, @PathVariable Integer number2) {
        int i = number1 + number2;
        String s =  String.valueOf(i);
        return s;
    }
}
