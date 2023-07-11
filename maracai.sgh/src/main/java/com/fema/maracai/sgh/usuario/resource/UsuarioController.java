package com.fema.maracai.sgh.usuario.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fema.maracai.sgh.usuario.form.UsuarioForm;
import com.fema.maracai.sgh.usuario.form.UsuarioFormSemSenha;
import com.fema.maracai.sgh.usuario.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	public ResponseEntity<?> buscarTodosUsuarios(){
		return new ResponseEntity<>(this.usuarioService.buscarTodos(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioForm formUsuario){
		this.usuarioService.inserir(formUsuario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioFormSemSenha formUsuario){
		this.usuarioService.atualizar(id, formUsuario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
		this.usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
