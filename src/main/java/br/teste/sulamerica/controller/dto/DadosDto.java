package br.teste.sulamerica.controller.dto;

public class DadosDto {
	
	private String latitude;
	private String longitude;
	private String nomeEspecialidade;
	
	public DadosDto () {}

	public DadosDto(String latitude, String longitude, String nomeEspecialidade) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}
	
}
