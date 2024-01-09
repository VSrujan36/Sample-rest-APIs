package com.example.amex.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SubtractionService {

    public String subTwo(Integer number1, Integer number2){
       int i = number1 - number2;
       String subtractionResult = String.valueOf(i);
       return subtractionResult;

    }
}
