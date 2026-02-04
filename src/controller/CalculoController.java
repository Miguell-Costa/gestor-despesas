package controller;

import dao.DespesaDAO;
import dao.PessoaDAO;
import model.Despesa;
import model.DivisaoDespesas;
import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class CalculoController {
    PessoaDAO pessoaDAO = new PessoaDAO();
    DespesaDAO despesaDAO = new DespesaDAO();

    // calcula quanto cada pessoa tem que pagar
    public List<DivisaoDespesas> calcularDivisao(){
        List<Pessoa> pessoas = pessoaDAO.listar();
        List<Despesa> despesas = despesaDAO.listar();

        double totalSalarios = 0.0;
        double totalDespesas = 0.0;

        for (Pessoa p : pessoas) {
            totalSalarios += p.getSalario();
        }

        for(Despesa d: despesas){
            totalDespesas += d.getValor();
        }

        List<DivisaoDespesas> resultados = new ArrayList<>();

        for(Pessoa p: pessoas){
            double percentagem = p.getSalario() / totalSalarios;
            double valor = percentagem * totalDespesas;

            resultados.add(new DivisaoDespesas(
                    p.getNome(),
                    p.getSalario(),
                    percentagem * 100,
                    valor
            ));
        }
        return resultados;
    }

    // verifica se existe data na bd
    public boolean existeMesAno(int mes, int ano) {
        return despesaDAO.existeMesAno(mes, ano);
    }

    // calcula quanto cada pessoa tem que pagar num mes especifico
    public List<DivisaoDespesas> calcularDivisaoPorMes(int mes, int ano){

        List<Pessoa> pessoas = pessoaDAO.listar();
        List<Despesa> despesas = despesaDAO.listarPorMesAno(mes, ano);

        double totalSalarios = 0.0;
        double totalDespesas = 0.0;

        // calcular total salarios
        for (Pessoa p : pessoas) {
            totalSalarios += p.getSalario();
        }

        // calcular total despesas
        for(Despesa d: despesas){
            totalDespesas += d.getValor();
        }

        List<DivisaoDespesas> resultados = new ArrayList<>();

        // calcula quanto cada pessoa vai ter que pagar
        for(Pessoa p: pessoas){
            double percentagem = p.getSalario() / totalSalarios;
            double valor = percentagem * totalDespesas;

            resultados.add(new DivisaoDespesas(
                    p.getNome(),
                    p.getSalario(),
                    percentagem * 100,
                    valor
            ));
        }
        return resultados;
    }

}
