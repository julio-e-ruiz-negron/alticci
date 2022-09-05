package com.demo.alticci.service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class AlticciService implements IAlticciService {
    // Cache to store computed alticci sequences
    public final Map<Long, BigInteger> computedAlticciSequences = new ConcurrentHashMap<>();

    @Override
    public BigInteger computeAlticciSequence(Long n) {
        if (n == 0) {
            return BigInteger.valueOf(0L);
        }
        if (n == 1 || n == 2) {
            return BigInteger.valueOf(1L);
        }
        // Return cached computed sequence for index (if it exists)
        if (computedAlticciSequences.containsKey(n)) {
            return computedAlticciSequences.get(n);
        }
        BigInteger computedAlticciSequence = computeAlticciSequence(n - 3).add(computeAlticciSequence(n - 2));
        computedAlticciSequences.putIfAbsent(n, computedAlticciSequence);
        return computedAlticciSequence;
    }
}