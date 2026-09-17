/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testepraticoiniflex;

/**
 *
 * @author Carlos
 */
import java.time.LocalDate;
import java.time.Month;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import java.util.stream.Collectors;

public class TestePraticoIniflex {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormatSymbols SIMBOLOS_BRASIL = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));
    private static final DecimalFormat FORMATO_VALOR = new DecimalFormat("#,##0.00", SIMBOLOS_BRASIL);
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        
        //item 3.1
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
                funcionario("Maria", "18/10/2000", "2009.44", "Operador"),
                funcionario("Joao", "12/05/1990", "2284.38", "Operador"),
                funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"),
                funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"),
                funcionario("Alice", "05/01/1995", "2234.68", "Recepcionista"),
                funcionario("Heitor", "19/11/1999", "1582.72", "Operador"),
                funcionario("Arthur", "31/03/1993", "4071.84", "Contador"),
                funcionario("Laura", "08/07/1994", "3017.45", "Gerente"),
                funcionario("Heloisa", "24/05/2003", "1606.85", "Eletricista"),
                funcionario("Helena", "02/09/1996", "2799.93", "Gerente")
        ));
        
        //item 3.2
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("Joao"));

        //item 3.3
        imprimirTitulo("Lista de Funcionarios");
        funcionarios.forEach(System.out::println);

        //item3.4
        funcionarios.forEach(funcionario -> funcionario.aplicarAumento(new BigDecimal("0.10")));

        //item 3.5
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, TreeMap::new, Collectors.toList()));

        //item 3.6
        imprimirTitulo("Funcionarios agrupados por funcao");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcao: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario));
        });

        
        //item3.8
        imprimirTitulo("Funcionarios aniversariantes dos meses 10 e 12");
        funcionarios.stream()
                .filter(funcionario -> {
                    Month mes = funcionario.getDataNascimento().getMonth();
                    return mes == Month.OCTOBER || mes == Month.DECEMBER;
                })
                .forEach(System.out::println);

        //item 3.9
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();
        imprimirTitulo("Funcionario com maior idade");
        System.out.printf("Nome: %s | Idade: %d anos%n", funcionarioMaisVelho.getNome(), funcionarioMaisVelho.getIdade());

        
        //item 3.10
        imprimirTitulo("Funcionarios em ordem alfabetica");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .forEach(System.out::println);

        
        //item 3.11
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        imprimirTitulo("Total dos salarios");
        System.out.println("Total: R$ " + FORMATO_VALOR.format(totalSalarios));

        //item 3.12
        imprimirTitulo("Quantidade de salarios mínimos por funcionario");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
                .forEach(funcionario -> {
                    BigDecimal quantidade = funcionario.getSalario()
                            .divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
                    System.out.printf("%s: %s salarios minimos%n",
                            funcionario.getNome(), FORMATO_VALOR.format(quantidade));
                });
    }

    private static Funcionario funcionario(String nome, String dataNascimento, String salario, String funcao) {
        return new Funcionario(nome, LocalDate.parse(dataNascimento, FORMATO_DATA), new BigDecimal(salario), funcao);
    }

    private static void imprimirTitulo(String titulo) {
        System.out.println("\n=== " + titulo + " ===");
    }
}
