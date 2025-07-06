package com.prueba3.inventario;

import org.springframework.boot.SpringApplication;

public class TestInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventarioApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
