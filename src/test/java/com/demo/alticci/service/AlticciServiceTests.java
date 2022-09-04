package com.demo.alticci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AlticciServiceTests {

	@Autowired
	private IAlticciService alticciService;

	@Test
	void testInvalidInputThrowsConstraintViolationException() {
		assertThrows(ConstraintViolationException.class, () -> alticciService.computeAlticciSequence(-1L));
		assertThrows(ConstraintViolationException.class, () -> alticciService.computeAlticciSequence(2501L));
	}

	@Test
	void testValidInput() {
		assertEquals(BigInteger.valueOf(1L), alticciService.computeAlticciSequence(1L),
				() -> "computeAlticciSequence failed");
	}

	@Test
	void testInvalidInput() {
		assertNotEquals(BigInteger.valueOf(0L), alticciService.computeAlticciSequence(1L),
				() -> "computeAlticciSequence failed");
	}
}
