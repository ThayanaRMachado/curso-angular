package com.crudusuario.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crudusuario.dtos.UsuarioDTO;
import com.crudusuario.entities.Usuario;
import com.crudusuario.repositories.UsuarioRepository;
import com.crudusuario.services.exceptions.DatabaseException;
import com.crudusuario.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		try {
			return repository.findAll().stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Users not found");
		}
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entidade = new Usuario();
		copyDtoToEntity(dto, entidade);
		entidade = repository.save(entidade);
		return new UsuarioDTO(entidade);
	}

	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome());
		entity.setUsuario(dto.getUsuario());
		entity.setSenha(dto.getSenha());
	}

}
