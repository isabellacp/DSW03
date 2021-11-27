package br.ufscar.trabalho.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
// Diretiva para definir a tabela dessa classe no banco de dados.
@Table(name = "cliente")
// Diretiva para definir essa classes como uma entidade
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "cpf", nullable = false, length = 30)
    private String cpf;

    @Column(name = "telefone", length = 10)
    private String telefone;

    @Column(name = "sexo", length = 10)
    private String sexo;

    @Column(name = "nascimento")
    private String nascimento;

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}