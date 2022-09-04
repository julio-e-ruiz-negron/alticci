package com.demo.alticci.service;

import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface IAlticciService {
    BigInteger computeAlticciSequence(
            @NotNull @Min(value = 0, message = "index must greater or equal to 0") @Max(value = 2500, message = "index must be less or equal to 2500") Long index);
}
