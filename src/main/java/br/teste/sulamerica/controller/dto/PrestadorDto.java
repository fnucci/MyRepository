package br.teste.sulamerica.controller.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import br.teste.sulamerica.model.Prestador;

public class PrestadorDto {
	
	private String nome;
	private String endereco;
	private Double latitude;
	private Double longitude;
	private Double distanciaEmKm;
	
	public PrestadorDto() {}

	
	
	public PrestadorDto(String nome, String endereco, String latitude, String longitude) {
		this.nome = nome;
		this.endereco = endereco;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		Random distancia = new Random();
		this.distanciaEmKm = distancia.nextDouble() * 40;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEndereco() {
		return endereco;
	}



	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	public Double getDistanciaEmKm() {
		return distanciaEmKm;
	}



	public void setDistanciaEmKm(Double distanciaEmKm) {
		this.distanciaEmKm = distanciaEmKm;
	}

	public static List<PrestadorDto> converter (List<Prestador> prestadores){
		List<PrestadorDto> lista = new ArrayList<PrestadorDto>();
		prestadores.forEach(e ->{
			PrestadorDto dto = new PrestadorDto(e.getNome(), e.getEndereco().getEnderecoCompleto(), e.getEndereco().getLatitude(), e.getEndereco().getLongitude());
			lista.add(dto);
		});
		
		lista.sort(Comparator.comparingDouble(PrestadorDto::getDistanciaEmKm));

		return lista;
	}
}
