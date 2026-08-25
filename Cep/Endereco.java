public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade; // A API ViaCEP chama a cidade de 'localidade'
    private String uf;

    // Métodos Getters
    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return "CEP: " + cep +
                " | Logradouro: " + (logradouro != null && !logradouro.isEmpty() ? logradouro : "N/A") +
                " | Bairro: " + (bairro != null && !bairro.isEmpty() ? bairro : "N/A") +
                " | Cidade/UF: " + localidade + "/" + uf;
    }
}