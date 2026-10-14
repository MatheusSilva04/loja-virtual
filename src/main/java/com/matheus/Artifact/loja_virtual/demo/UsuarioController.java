package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return usuarioService.criar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizar (@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
