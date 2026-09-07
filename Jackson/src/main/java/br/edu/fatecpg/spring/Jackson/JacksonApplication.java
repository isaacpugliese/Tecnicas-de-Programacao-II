package br.edu.fatecpg.spring.Jackson;

import br.edu.fatecpg.spring.Jackson.model.Endereco;
import br.edu.fatecpg.spring.Jackson.service.ConsomeApi;
import br.edu.fatecpg.spring.Jackson.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

@SpringBootApplication
public class JacksonApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JacksonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner leitura = new Scanner(System.in);
		ConsomeApi consumo = new ConsomeApi();
		ConverteDados conversor = new ConverteDados();
		int opcao = -1;

		while (opcao != 3) {
			System.out.println("\n*** MENU ***");
			System.out.println("1. Consultar");
			System.out.println("2. Listar Consultas do Log");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");

			if (leitura.hasNextInt()) {
				opcao = leitura.nextInt();
				leitura.nextLine();
			} else {
				System.out.println("Opção inválida!");
				leitura.next();
				continue;
			}

			switch (opcao) {
				case 1:
					System.out.print("Digite o CEP para consulta (ex: 11783490): ");
					String cep = leitura.nextLine();
					String json = consumo.obterDados("https://viacep.com.br/ws/" + cep + "/json/");

					Endereco endereco = conversor.obterDados(json, Endereco.class);
					System.out.println("Endereço convertido: " + endereco);

					ZonedDateTime nowInBrasilia = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
					try {
						FileWriter escrita = new FileWriter("sistema.log", true);
						escrita.write("Consulta em: " + nowInBrasilia + " - JSON: " + json + "\n");
						escrita.close();
						System.out.println("Log registrado com sucesso!");
					} catch (IOException e) {
						System.out.println("Erro ao salvar o log.");
						e.printStackTrace();
					}
					break;

				case 2:
					System.out.println("\n--- HISTÓRICO DE LOGS ---");
					try {
						FileReader arquivoLeitura = new FileReader("sistema.log");
						BufferedReader bufferedReader = new BufferedReader(arquivoLeitura);
						String linha;
						while ((linha = bufferedReader.readLine()) != null) {
							System.out.println(linha);
						}
						bufferedReader.close();
						arquivoLeitura.close();
					} catch (IOException e) {
						System.out.println("Nenhum log encontrado.");
					}
					break;

				case 3:
					System.out.println("Encerrando o sistema...");
					break;

				default:
					System.out.println("Opção inválida.");
			}
		}
		leitura.close();
	}
}