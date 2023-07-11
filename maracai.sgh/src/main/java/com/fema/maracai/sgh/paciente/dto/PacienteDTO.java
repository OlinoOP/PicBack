package com.fema.maracai.sgh.paciente.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.fema.maracai.sgh.paciente.model.Paciente;

import lombok.Getter;

@Getter
public class PacienteDTO {
	
	private Long id;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String sexo;
	private String endereco;
	private String cidade;
	private String estado;
	private String telefone;
	
	public PacienteDTO(Paciente paciente) {
		id = paciente.getId();
		nome = paciente.getNome();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataNascimento = paciente.getDataNascimento().format(formatter);
		cpf = paciente.getCpf();
		sexo = paciente.getSexo();
		endereco = paciente.getEndereco();
		cidade = paciente.getCidade();
		estado = paciente.getEstado();
		telefone = paciente.getTelefone();
	}
	
	public static List<PacienteDTO> converter(List<Paciente> pacientes){
		pacientes = pacientes.stream().sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome())).collect(Collectors.toList());
		return pacientes.stream().map(PacienteDTO::new).collect(Collectors.toList());
	}
}
