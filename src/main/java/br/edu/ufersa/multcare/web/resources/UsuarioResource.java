package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value="/api/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO)  {
        String resposta = usuarioService.cadastrarUsuario(usuarioDTO);
        return ResponseEntity.ok(resposta);
    }

}