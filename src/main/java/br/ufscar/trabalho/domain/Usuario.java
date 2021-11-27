package br.ufscar.trabalho.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// Diretiva para definir a tabela dessa classe no banco de dados.
@Table(name = "usuario", indexes = {
        // Diretiva que define a coluna de email unica, para evitar duas contas com o mesmo email
        @Index(name = "usuario_email_uindex", columnList = "email", unique = true)
})
// Diretiva para definir essa classes como uma entidade
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password",nullable = false, length = 64)
    private String password;

    @Column(name = "role", nullable = false, length = 30)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}