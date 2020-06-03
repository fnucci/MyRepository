package br.teste.sulamerica.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teste.sulamerica.config.AplicationConfigParameters;
import br.teste.sulamerica.controller.dto.DadosDto;
import br.teste.sulamerica.controller.dto.PrestadorDto;
import br.teste.sulamerica.model.Endereco;
import br.teste.sulamerica.model.Especialidade;
import br.teste.sulamerica.model.Prestador;

@RestController
@RequestMapping("/obterPrestadoresSaude")
public class SulamericaController {

	@GetMapping
	public ResponseEntity<?> obterPrestadoresSaude(@RequestBody DadosDto dados) {

		List<Prestador> prestadores = getListaPrestadores();
		List<Prestador> filtrada = new ArrayList<Prestador>();
		
		//Filtra a lista pela especialidade
		prestadores.forEach(prest -> {
			List<Especialidade> especialidades = prest.getEspecialidades();
			especialidades.forEach(espec -> {
				if (espec.getNomeEspecialidade().equals(dados.getNomeEspecialidade())) {
					filtrada.add(prest);
				}
			});
		});

		//Verifica a distancia entre a origem e destino pela  API do google para cada prestador da lista filtrada
		filtrada.forEach(prestador -> {
			String googleMaps = String.format("https://maps.googleapis.com/maps/api/directions/json?origin=%s,%s&destination=%s,%s&key=%s", dados.getLatitude(),dados.getLongitude(),prestador.getEndereco().getLatitude(),prestador.getEndereco().getLongitude(),AplicationConfigParameters.API_KEY);			
			try {
				
				//URL de testes com a lat e long da minha casa
//				URL url = new URL(
//						"https://maps.googleapis.com/maps/api/directions/json?origin=-23.735110,-46.698068&destination=-23.605456,-46.675720&key=AIzaSyD0joldMOkI3eSG2c6iEvfOM7l9LFlQYZc");
				
				URL url = new URL(googleMaps);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();

				if (con.getResponseCode() != 200) {
					throw new RuntimeException("HTTP error code : " + con.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
				StringBuilder strBuilder = new StringBuilder();
				String linha = "";
				while ((linha = br.readLine()) != null) {
					strBuilder.append(linha);
				}
				
				//Por algum motivo o JSON de retorno está com problema de ENCODING que nao resolvi, entao simulei a distancia
				
				con.disconnect();
			} catch (Exception e) {
				e.printStackTrace();

			}
		});
		

		List<PrestadorDto> prestadorDto = PrestadorDto.converter(filtrada);
		return ResponseEntity.ok(prestadorDto);
	}

	private List<Prestador> getListaPrestadores() {

		List<Prestador> prestadores = new ArrayList<Prestador>();

		Endereco endOrto = new Endereco(1L, "Espaço Fisio, Ortopedia e Fraturas", "Rua Olívia Guedes Penteado", 152, "",
				"Socorro", "São Paulo", "SP", "Brasil", "04766-000", "-23.668768", "-46.712419");
		Endereco endZen = new Endereco(2L, "Espaço Zen Saúde Mental", "Rua Julião Ferreira da Silva", 220, "",
				"Vila Nova Cachoeirinha", "São Paulo", "SP", "Brasil", "02882-000", "-23.472344", "-46.667260");
		Endereco endCli = new Endereco(3L, "Clinica faça seu checkup", "Rua Heitor Penteado", 1881, "Térreo",
				"Vila Madalena", "São Paulo", "SP", "Brasil", "05437-002", "-23.542544", "-46.695221");
		Endereco endHospital = new Endereco(4L, "Hospital Santa Maria", "Rua Dona Veridiana", 311, "", "Higienópolis",
				"São Paulo", "SP", "Brasil", "01238-010", "-23.542701", "-46.650046");
		Endereco endEspec = new Endereco(5L, "Clinica Odontolagica Sorria ", "Rua Itinguçú", 1404, "", "Vila Ré",
				"São Paulo", "SP", "Brasil", "03658-000", "-23.527057", "-46.501393");

		Especialidade esp1 = new Especialidade(1l, "Clinico Geral");
		Especialidade esp2 = new Especialidade(2l, "Ginecologia");
		Especialidade esp3 = new Especialidade(3l, "Urologia");
		Especialidade esp4 = new Especialidade(4l, "Ortopedia");
		Especialidade esp5 = new Especialidade(5l, "Fisioterapia");
		Especialidade esp6 = new Especialidade(6l, "Odontologia");
		Especialidade esp7 = new Especialidade(7l, "Ortodontia");
		Especialidade esp8 = new Especialidade(8l, "Cardiologia");
		Especialidade esp9 = new Especialidade(9l, "Psicologia");
		Especialidade esp10 = new Especialidade(10l, "Neurologia");

		List<Especialidade> listOdonto = new ArrayList<Especialidade>();
		listOdonto.add(esp7);
		listOdonto.add(esp6);

		List<Especialidade> listOrto = new ArrayList<Especialidade>();
		listOrto.add(esp4);
		listOrto.add(esp1);
		listOrto.add(esp5);

		List<Especialidade> listCli = new ArrayList<Especialidade>();
		listCli.add(esp1);

		List<Especialidade> listCardio = new ArrayList<Especialidade>();
		listCardio.add(esp8);
		listCardio.add(esp1);

		List<Especialidade> listGineco = new ArrayList<Especialidade>();
		listGineco.add(esp2);

		List<Especialidade> listUro = new ArrayList<Especialidade>();
		listUro.add(esp3);

		List<Especialidade> listHosp = new ArrayList<Especialidade>();
		listHosp.add(esp1);
		listHosp.add(esp2);
		listHosp.add(esp3);
		listHosp.add(esp4);
		listHosp.add(esp8);
		listHosp.add(esp10);

		List<Especialidade> listZen = new ArrayList<Especialidade>();
		listZen.add(esp10);
		listZen.add(esp9);

		Prestador prestador1 = new Prestador(1L, "Jose", endOrto, listOrto);
		Prestador prestador2 = new Prestador(2L, "Joao", endOrto, listOrto);
		Prestador prestador3 = new Prestador(3L, "Fulano", endHospital, listCli);
		Prestador prestador4 = new Prestador(4L, "Derpina", endHospital, listCli);
		Prestador prestador5 = new Prestador(5L, "Antonia", endHospital, listCli);
		Prestador prestador6 = new Prestador(6L, "Adriana", endHospital, listHosp);
		Prestador prestador7 = new Prestador(7L, "Didi", endHospital, listHosp);
		Prestador prestador8 = new Prestador(8L, "Muçun", endHospital, listCardio);
		Prestador prestador9 = new Prestador(9L, "Mané", endHospital, listGineco);
		Prestador prestador10 = new Prestador(10L, "Alex", endHospital, listUro);
		Prestador prestador11 = new Prestador(11L, "Bruna", endHospital, listUro);
		Prestador prestador12 = new Prestador(12L, "Cassio", endHospital, listGineco);
		Prestador prestador13 = new Prestador(13L, "Eduardo", endCli, listCli);
		Prestador prestador14 = new Prestador(14L, "Fernando", endCli, listGineco);
		Prestador prestador15 = new Prestador(15L, "Iraci", endCli, listCardio);
		Prestador prestador16 = new Prestador(16L, "Helena", endZen, listZen);
		Prestador prestador17 = new Prestador(17L, "Maria", endZen, listZen);
		Prestador prestador18 = new Prestador(18L, "Nando", endZen, listZen);
		Prestador prestador19 = new Prestador(19L, "Ofelia", endEspec, listOdonto);
		Prestador prestador20 = new Prestador(20L, "Pedro", endEspec, listOdonto);

		prestadores.add(prestador1);
		prestadores.add(prestador2);
		prestadores.add(prestador3);
		prestadores.add(prestador4);
		prestadores.add(prestador5);
		prestadores.add(prestador6);
		prestadores.add(prestador7);
		prestadores.add(prestador8);
		prestadores.add(prestador9);
		prestadores.add(prestador10);
		prestadores.add(prestador11);
		prestadores.add(prestador12);
		prestadores.add(prestador13);
		prestadores.add(prestador14);
		prestadores.add(prestador15);
		prestadores.add(prestador16);
		prestadores.add(prestador17);
		prestadores.add(prestador18);
		prestadores.add(prestador19);
		prestadores.add(prestador20);

		return prestadores;
	}

}
