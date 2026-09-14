import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", "Eletronicos", 3500.0),
                new Produto("Celular", "Eletronicos", 2000.0),
                new Produto("Fone de Ouvido", "Eletronicos", 300.0),
                new Produto("Java para Iniciantes", "Livros", 150.0),
                new Produto("Clean Code", "Livros", 200.0),
                new Produto("Camiseta", "Roupas", 80.0),
                new Produto("Calça Jeans", "Roupas", 200.0),
                new Produto("Jaqueta", "Roupas", 250.0)
        );

        System.out.println("=== PRODUTOS ELETRÔNICOS ===");
        produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("Eletronicos"))
                .forEach(System.out::println);

        System.out.println("\n=== ELETRÔNICOS COM 10% DE DESCONTO ===");
        produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("Eletronicos"))
                .map(p -> new Produto(p.getNome(), p.getCategoria(), p.getPreco() * 0.9))
                .sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
                .forEach(System.out::println);

        System.out.println("\n=== TOTAL EM ROUPAS ===");
        double totalRoupas = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("Roupas"))
                .mapToDouble(Produto::getPreco)
                .reduce(0.0, Double::sum);
        System.out.println("Total: R$ " + String.format("%.2f", totalRoupas));

        System.out.println("\n=== MÉDIA DE PREÇO POR CATEGORIA ===");
        Map<String, Double> mediaPrecoPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        Produto::getCategoria,
                        Collectors.averagingDouble(Produto::getPreco)
                ));

        mediaPrecoPorCategoria.forEach((categoria, media) ->
                System.out.println(categoria + ": R$ " + String.format("%.2f", media))
        );
    }
}