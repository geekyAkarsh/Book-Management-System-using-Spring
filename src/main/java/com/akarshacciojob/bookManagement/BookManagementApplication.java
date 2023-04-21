package com.akarshacciojob.bookManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookManagementApplication.class, args);

		Book book = new Book(123,"abc","xyz",25);

	}

}
