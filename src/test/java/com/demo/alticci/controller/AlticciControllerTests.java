package com.demo.alticci.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@SpringBootTest
@AutoConfigureMockMvc
class AlticciControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testValidInput() throws Exception {
		this.mockMvc.perform(
				get("/alticci/1"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1")));
	}

	@Test
	void testInvalidInputLessThanMinNumber() throws Exception {
		this.mockMvc.perform(
				get("/alticci/{index}", -1L))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof ConstraintViolationException))
				.andExpect(result -> assertEquals("getAlticciNumber.n: n must greater or equal to 0",
						result.getResolvedException().getMessage()));
	}

	@Test
	void testInvalidInputMoreThanMaxNumber() throws Exception {
		this.mockMvc.perform(
				get("/alticci/{index}", 2501L))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof ConstraintViolationException))
				.andExpect(result -> assertEquals("getAlticciNumber.n: n must be less or equal to 2500",
						result.getResolvedException().getMessage()));
	}

	@Test
	void testInvalidInputiString() throws Exception {
		this.mockMvc.perform(
				get("/alticci/{index}", "a"))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(
						result.getResolvedException() instanceof MethodArgumentTypeMismatchException))
				.andExpect(result -> assertEquals(
						"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: \"a\"",
						result.getResolvedException().getMessage()));
	}
}
