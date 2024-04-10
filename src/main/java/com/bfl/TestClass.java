package com.bfl;

import java.math.BigDecimal;
import java.text.ParseException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestClass {

	public static void main(String[] args) throws ParseException {

		BCryptPasswordEncoder cd = new BCryptPasswordEncoder();
		System.out.println(cd.encode("bfl123"));
		
		System.out.println(new BigDecimal("1.00").negate());


	}

}
