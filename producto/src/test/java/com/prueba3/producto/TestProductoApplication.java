package com.prueba3.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class TestProductoApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
