package controller;

import dao.PessoaDAO;
import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {
    PessoaDAO pessoaDAO = new PessoaDAO();

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas;

        pessoas = pessoaDAO.listar();
        return pessoas;
    }

    public void adicionarPessoa(String nome, double salario){

        Pessoa pessoa = new Pessoa(nome, salario);
        pessoaDAO.adicionar(pessoa);
    }

    public void removerPessoa(int id){
        pessoaDAO.eliminar(id);
    }

    public void editarPessoa(int id, String nome, double salario){
        Pessoa pessoa = new Pessoa(nome, salario);
        pessoaDAO.atualizar(pessoa, id);
    }
}
