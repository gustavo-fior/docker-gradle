package co.sban.missaojavacomgradle.model.input;

import co.sban.missaojavacomgradle.model.Usuario;

import java.time.LocalDate;

public class UsuarioForm {

    private String nome;
    private String sobreNome;
    private String dataNascimento;
    private String cpf;
    private String email;

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

    public Usuario toUsuario() {

        return new Usuario(this.nome, this.sobreNome, LocalDate.parse(this.dataNascimento), this.cpf, this.email);
    }
}
