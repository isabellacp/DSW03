package br.ufscar.trabalho;

import br.ufscar.trabalho.dao.IAgenciaDAO;
import br.ufscar.trabalho.dao.IClienteDAO;
import br.ufscar.trabalho.dao.IUsuarioDAO;
import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TrabalhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IClienteDAO clienteDAO, IAgenciaDAO agenciaDAO, BCryptPasswordEncoder encoder) {
        return (args) -> {
            if (usuarioDAO.getUsuarioByEmail("admin@email.com") == null) {
                Usuario u1 = new Usuario();
                u1.setEmail("admin@email.com");
                u1.setPassword(encoder.encode("admin"));
                u1.setNome("Admin");
                u1.setRole("ROLE_ADMIN");
                usuarioDAO.save(u1);
            }
            if (usuarioDAO.getUsuarioByEmail("cliente@email.com") == null) {
                Usuario u2 = new Usuario();
                u2.setEmail("cliente@email.com");
                u2.setPassword(encoder.encode("cliente"));
                u2.setNome("Cliente");
                u2.setRole("ROLE_CLIENTE");
                usuarioDAO.save(u2);
                Cliente cliente = new Cliente();
                cliente.setTelefone("123");
                cliente.setSexo("asd");
                cliente.setCpf("123123");
                cliente.setUsuario(u2);
                cliente.setNascimento("asd");
                clienteDAO.save(cliente);
            }
            if (usuarioDAO.getUsuarioByEmail("agencia@email.com") == null) {
                Usuario u3 = new Usuario();
                u3.setEmail("agencia@email.com");
                u3.setPassword(encoder.encode("agencia"));
                u3.setNome("Agencia");
                u3.setRole("ROLE_Agencia");
                usuarioDAO.save(u3);
                Agencia agencia = new Agencia();
                agencia.setNome("Agencia");
                agencia.setCnpj("123123");
                agencia.setUsuario(u3);
                agenciaDAO.save(agencia);
            }
        };
    }


}
