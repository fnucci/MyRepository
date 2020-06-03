package br.teste.sulamerica.model;

public class Especialidade {

	private Long id;
	private String nomeEspecialidade;

	public Especialidade() {}
	
	public Especialidade(Long id, String nomeEspecialidade) {
		this.id = id;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
}
