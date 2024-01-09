package com.example.amex.controller;

import com.example.amex.service.SubtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class Calculator {

    @Autowired
    SubtractionService subtractionService;

    @GetMapping("/add/{number1}/{number2}")
    public String addition(@PathVariable Integer number1, @PathVariable Integer number2) {
        String s = subtractionService.subTwo(number1, number2);
        return s;
    }
}
