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
    public BigInteger computeAlticciSequence(
            Long index) {
        if (index == 0) {
            return BigInteger.valueOf(0L);
        }
        if (index == 1 || index == 2) {
            return BigInteger.valueOf(1L);
        }
        // Return cached computed sequence for index (if it exists)
        if (computedAlticciSequences.containsKey(index)) {
            return computedAlticciSequences.get(index);
        }
        BigInteger computedAlticciSequence = computeAlticciSequence(index - 3).add(computeAlticciSequence(index - 2));
        computedAlticciSequences.putIfAbsent(index, computedAlticciSequence);
        return computedAlticciSequence;
    }
}