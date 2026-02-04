package ui;

import controller.PessoaController;
import model.Pessoa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PessoasPanel extends JFrame {

    private JButton btnAdicionarPessoa;
    private JButton btnEditarPessoa;
    private JButton btnEliminarPessoa;
    private JTable tabelaPessoas;
    private DefaultTableModel tabela;
    private PessoaController pessoaController;

    public PessoasPanel(){
        setTitle("Gestão Pessoas");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pessoaController = new PessoaController();

        initComponents();
    }

    public void initComponents(){
        // criar botões
        btnAdicionarPessoa = new JButton("Adicionar Pessoa");
        btnEditarPessoa = new JButton("Editar Pessoa");
        btnEliminarPessoa = new JButton("Eliminar Pessoa");

        // criar tabela
        String[] colunas = {"Id", "Nome","Salario"};
        tabela = new DefaultTableModel(colunas, 0);
        tabelaPessoas = new JTable(tabela);
        tabelaPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // só permite selecionar 1 linha
        JScrollPane scrollPane = new JScrollPane(tabelaPessoas);

        // criar painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotoes.add(btnAdicionarPessoa);
        panelBotoes.add(btnEditarPessoa);
        panelBotoes.add(btnEliminarPessoa);

        // organizar layout
        setLayout(new BorderLayout());
        add(panelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // adicionar ações aos botões
        btnAdicionarPessoa.addActionListener(e -> adicionarPessoa());
        btnEditarPessoa.addActionListener(e -> editarPessoa());
        btnEliminarPessoa.addActionListener(e -> eliminarPessoa());

        //atualizar tabela inicialmente
        atualizarTabela();
    }

    private void adicionarPessoa(){
        String nome = JOptionPane.showInputDialog(this, "Nome da Pessoa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o nome", "Nome inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String salarioStr = JOptionPane.showInputDialog(this, "Salário da Pessoa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o salário", "Salário inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double salario;

        try{
            salario = Double.parseDouble(salarioStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Salário inválido!");
            return;
        }

        pessoaController.adicionarPessoa(nome, salario);
        JOptionPane.showMessageDialog(this, "Pessoa adicionada com sucesso!");
        atualizarTabela();
    }

    private void editarPessoa(){
        // guardar linha selecionada
        int linhaSelecionada =tabelaPessoas.getSelectedRow();
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tabelaPessoas.getValueAt(linhaSelecionada, 0).toString());

        String nome = JOptionPane.showInputDialog(this, "Nome da Pessoa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o nome", "Nome inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String salarioStr = JOptionPane.showInputDialog(this, "Salário da Pessoa:");
        if(salarioStr == null || salarioStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o salário", "Salário inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double salario;

        try{
            salario = Double.parseDouble(salarioStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Salário inválido!");
            return;
        }

        pessoaController.editarPessoa(id, nome, salario);
        JOptionPane.showMessageDialog(this, "Pessoa editada com sucesso!");
        atualizarTabela();
    }

    private void eliminarPessoa(){
        // guardar linha selecionada
        int linhaSelecionada =tabelaPessoas.getSelectedRow();
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(tabelaPessoas.getValueAt(linhaSelecionada, 0).toString());

        pessoaController.removerPessoa(id);
        JOptionPane.showMessageDialog(this, "Pessoa removida com sucesso!");
        atualizarTabela();
    }

    private void atualizarTabela(){
        tabela.setRowCount(0);

        List<Pessoa> pessoas = pessoaController.listarPessoas();

        for (Pessoa p: pessoas){
            Object[] linha = {p.getId(), p.getNome(), p.getSalario()};

            tabela.addRow(linha);
        }
    }

}
