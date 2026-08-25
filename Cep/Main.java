import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        ArrayList<Endereco> historico = new ArrayList<>();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====== MENU VIACEP ======");
            System.out.println("1 - Consultar CEP");
            System.out.println("2 - Ver Consultados");
            System.out.println("3 - Limpar Histórico de Consulta");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o CEP (apenas números ou com hífen): ");
                    String cepDigitado = scanner.nextLine();

                    try {
                        String jsonResposta = ConsomeAPI.buscaEndereco(cepDigitado);

                        // Trata o erro caso a API retorne a flag {"erro": "true"}
                        if (jsonResposta.contains("\"erro\":")) {
                            System.out.println("❌ CEP não encontrado na base de dados do ViaCEP.");
                        } else {
                            Endereco endereco = gson.fromJson(jsonResposta, Endereco.class);
                            historico.add(endereco);

                            System.out.println("\n✅ Endereço Encontrado:");
                            System.out.println(endereco);
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println("❌ Erro ao conectar com a API: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- HISTÓRICO DE CONSULTAS ---");
                    if (historico.isEmpty()) {
                        System.out.println("Nenhum endereço foi consultado ainda.");
                    } else {
                        for (int i = 0; i < historico.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + historico.get(i));
                        }
                    }
                    System.out.println("------------------------------");
                    break;

                case 3:
                    if (historico.isEmpty()) {
                        System.out.println("\nO histórico já está vazio.");
                    } else {
                        historico.clear();
                        System.out.println("\n🧹 Histórico de consultas limpado com sucesso!");
                    }
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema... Até mais!");
                    break;

                default:
                    System.out.println("\n⚠️ Opção inválida! Escolha um número do menu.");
                    break;
            }
        }

        scanner.close();
    }
}