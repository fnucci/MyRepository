package br.teste.sulamerica.model;

import java.util.ArrayList;
import java.util.List;


public class Prestador {

	private Long id;
	private String nome;
	private Endereco endereco;
	private List<Especialidade> especialidades;
	
	public Prestador() {}
	
	public Prestador(Long id, String nome, Endereco endereco, List<Especialidade> especialidade) {		
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.especialidades = new ArrayList<Especialidade>();
		this.especialidades = especialidade;		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
}
