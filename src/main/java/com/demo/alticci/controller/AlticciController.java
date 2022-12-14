package com.demo.alticci.controller;

import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.alticci.service.IAlticciService;

@RestController
@Validated
@RequestMapping("/alticci")
public class AlticciController {

    private final IAlticciService alticciService;

    public AlticciController(final IAlticciService alticciService) {
        this.alticciService = alticciService;
    }

    @GetMapping("/{n}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<BigInteger> getAlticciNumber(
            @PathVariable @NotNull @Min(value = 0, message = "n must greater or equal to 0") @Max(value = 2500, message = "n must be less or equal to 2500") Long n) {
        return ResponseEntity.ok().body(alticciService.computeAlticciSequence(n));
    }
}
