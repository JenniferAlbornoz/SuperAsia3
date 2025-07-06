package com.prueba3.carrito;

import org.springframework.boot.SpringApplication;

public class TestCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.from(CarritoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
