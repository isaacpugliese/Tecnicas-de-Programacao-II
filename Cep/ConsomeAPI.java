import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeAPI {

    public static String buscaEndereco(String cep) throws IOException, InterruptedException {
        // Limpa o CEP mantendo apenas os números
        String cepLimpo = cep.replaceAll("\\D", "");

        String enderecoUrl = "https://viacep.com.br/ws/" + cepLimpo + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoUrl))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}