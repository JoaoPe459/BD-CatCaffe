package br.edu.ufersa.CatCaffe.models;

import br.edu.ufersa.CatCaffe.models.services.UsuarioServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(JpaApplication.class, args);
        UsuarioServices usuarioServices = context.getBean(UsuarioServices.class);
    }
}
