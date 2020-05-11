package br.edu.ufersa.multcare.shared.builder;

import br.edu.ufersa.multcare.persistence.entities.Usuario;

public class UsuarioBuilder {

    private Usuario usuario;

    public UsuarioBuilder() {
        this.usuario = new Usuario();
    }

    public static UsuarioBuilder of(){
        return new UsuarioBuilder();
    }

    public UsuarioBuilder comNome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comLogin(String login) {
        this.usuario.setLogin(login);
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.usuario.setSenha(senha);
        return this;
    }

    public UsuarioBuilder comIdade(Integer idade) {
        this.usuario.setIdade(idade);
        return this;
    }

    public UsuarioBuilder comSexo(Character sexo) {
        this.usuario.setSexo(sexo);
        return this;
    }

    public UsuarioBuilder comPeso(Float peso) {
        this.usuario.setPeso(peso);
        return this;
    }

    public UsuarioBuilder comHipertenso(Character hipertenso) {
        this.usuario.setHipertenso(hipertenso);
        return this;
    }

    public UsuarioBuilder comDiabetico(Character diabetico) {
        this.usuario.setDiabetico(diabetico);
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }
}
