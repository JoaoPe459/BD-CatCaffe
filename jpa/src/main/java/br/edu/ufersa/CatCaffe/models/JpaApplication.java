package br.edu.ufersa.CatCaffe.models;

import br.edu.ufersa.CatCaffe.models.dtos.UsuarioRecordDto;
import br.edu.ufersa.CatCaffe.models.entities.Cliente;
import br.edu.ufersa.CatCaffe.models.entities.Usuario;
import br.edu.ufersa.CatCaffe.models.services.UsuarioServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(JpaApplication.class, args);
        UsuarioServices usuarioServices = context.getBean(UsuarioServices.class);
        UsuarioRecordDto novoUsuario = new UsuarioRecordDto(
                null,
                "joão",
                "joao@gmail.com",
                "84998935363",
                "senha123");
        usuarioServices.saveUsuario(novoUsuario);
    }
}
