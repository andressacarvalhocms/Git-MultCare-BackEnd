package br.edu.ufersa.multcare.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class JwtResponse {

    private String jwt;

    private Integer id;

    private String nome;

    private String login;
}
