package com.prueba3.usuario.config;


import net.datafaker.Faker;
import com.prueba3.usuario.model.Usuario;
import com.prueba3.usuario.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UsuarioRepository usuarioRepository) {
        return args -> {
            Faker faker = new Faker();

            // Crea 10 usuarios de ejemplo
            for (int i = 0; i < 10; i++) {
                String nombre = faker.name().fullName();
                String email = faker.internet().emailAddress();
                String password = faker.internet().password();

                // Evita duplicados por email
                if (!usuarioRepository.existsByEmail(email)) {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setEmail(email);
                    usuario.setPassword(password); // Si usas password encriptada, encripta aquí

                    usuarioRepository.save(usuario);
                }
            }
        };
    }
}