/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testepraticoiniflex;

/**
 *
 * @author Carlos
 */
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormatSymbols SIMBOLOS_BRASIL = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));
    private static final DecimalFormat FORMATO_VALOR = new DecimalFormat("#,##0.00", SIMBOLOS_BRASIL);

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void aplicarAumento(BigDecimal percentual) {
        salario = salario.multiply(BigDecimal.ONE.add(percentual));
    }

    public String getFuncao() {
        return funcao;
    }

    public int getIdade() {
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    public String salarioFormatado() {
        return "R$ " + FORMATO_VALOR.format(salario);
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Data de nascimento: %s | Salario: %s | Funcao: %s",
                getNome(), FORMATO_DATA.format(getDataNascimento()), salarioFormatado(), funcao);
    }
}
