package controller;

import dao.DespesaDAO;
import model.Despesa;

import java.util.List;

public class DespesaController {
    DespesaDAO despesaDAO = new DespesaDAO();

    public List<Despesa> listarDespesas(){
        List<Despesa> despesas;

        despesas = despesaDAO.listar();
        return despesas;
    }

    public void adicionarDespesa(String nome, Double valor, int mes, int ano){
        Despesa despesa = new Despesa(nome, valor, mes, ano);

        despesaDAO.adicionar(despesa);
    }

    public void removerDespesa(int id){
        despesaDAO.eleminar(id);
    }

    public void editarDespesa(int id, String nome, double valor, int mes, int ano){
        Despesa despesa = new Despesa(nome, valor, mes, ano);
        despesaDAO.editar(despesa, id);
    }
}
