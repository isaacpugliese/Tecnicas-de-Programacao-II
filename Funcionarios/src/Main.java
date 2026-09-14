import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario("Ana Souza", "TI", 4500.0, 12),
                new Funcionario("Carlos Silva", "TI", 2800.0, 3),
                new Funcionario("Beatriz Lima", "RH", 3200.0, 8),
                new Funcionario("Daniel Rocha", "RH", 2500.0, 2),
                new Funcionario("Elena Santos", "Financeiro", 6000.0, 15),
                new Funcionario("Fabio Costa", "Financeiro", 4100.0, 11),
                new Funcionario("Gabriela Martins", "TI", 5200.0, 6),
                new Funcionario("Hugo Almeida", "RH", 3000.0, 5)
        );

        System.out.println("=== FUNCIONÁRIOS COM SALÁRIO > R$ 3000 ===");
        List<Funcionario> salarioMaiorQue3000 = funcionarios.stream()
                .filter(f -> f.getSalario() > 3000.0)
                .collect(Collectors.toList());
        salarioMaiorQue3000.forEach(System.out::println);

        System.out.println("\n=== FUNCIONÁRIOS COM > 10 ANOS DE SERVIÇO (COM 5% DE AUMENTO) ===");
        List<Funcionario> funcionariosComAumento = funcionarios.stream()
                .filter(f -> f.getAnosDeServico() > 10)
                .map(f -> new Funcionario(
                        f.getNome(),
                        f.getDepartamento(),
                        f.getSalario() * 1.05, // Aplica 5% de aumento
                        f.getAnosDeServico()
                ))
                .collect(Collectors.toList());
        funcionariosComAumento.forEach(System.out::println);

        System.out.println("\n=== FUNCIONÁRIOS ORDENADOS POR NOME (ALFABÉTICA) ===");
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
        funcionariosOrdenados.forEach(System.out::println);

        System.out.println("\n=== TOTAL GASTO COM SALÁRIOS ===");
        double totalSalarios = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .reduce(0.0, Double::sum);
        System.out.println("Total geral: " + String.format("R$ %.2f", totalSalarios));

        System.out.println("\n=== MÉDIA SALARIAL POR DEPARTAMENTO ===");
        Map<String, Double> mediaSalarioPorDepartamento = funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getDepartamento,
                        Collectors.averagingDouble(Funcionario::getSalario)
                ));

        mediaSalarioPorDepartamento.forEach((departamento, media) ->
                System.out.println("Departamento: " + departamento + " | Média: " + String.format("R$ %.2f", media))
        );
    }
}