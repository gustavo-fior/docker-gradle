package co.sban.missaojavacomgradle.model.output;

import co.sban.missaojavacomgradle.model.Usuario;

import java.time.LocalDate;

public class UsuarioDTO {

    private String nome;
    private String sobreNome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.sobreNome = usuario.getSobreNome();
        this.dataNascimento = usuario.getDataNascimento();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
