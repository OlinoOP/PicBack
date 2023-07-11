package com.fema.maracai.sgh.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fema.maracai.sgh.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
