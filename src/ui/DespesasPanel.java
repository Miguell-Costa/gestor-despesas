package ui;

import controller.DespesaController;
import controller.PessoaController;
import model.Despesa;
import model.Pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DespesasPanel extends JFrame {
    private JButton btnAdicionarDespesa;
    private JButton btnEditarDespesa;
    private JButton btnEliminarDespesa;
    private JTable tabelaDespesa;
    private DefaultTableModel tabela;
    private DespesaController despesaController;

    public DespesasPanel(){
        setTitle("Gestão Pessoas");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        despesaController = new DespesaController();

        initComponents();
    }

    public void initComponents(){
        // criar botões
        btnAdicionarDespesa = new JButton("Adicionar Despesa");
        btnEditarDespesa = new JButton("Editar Despesa");
        btnEliminarDespesa = new JButton("Eliminar Despesa");

        // criar tabela
        String[] colunas = {"Id", "Nome","Valor","Mes","Ano"};
        tabela = new DefaultTableModel(colunas, 0);
        tabelaDespesa = new JTable(tabela);
        tabelaDespesa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // só permite selecionar 1 linha
        JScrollPane scrollPane = new JScrollPane(tabelaDespesa);

        // criar painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotoes.add(btnAdicionarDespesa);
        panelBotoes.add(btnEditarDespesa);
        panelBotoes.add(btnEliminarDespesa);

        // organizar layout
        setLayout(new BorderLayout());
        add(panelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // adicionar ações aos botões
        btnAdicionarDespesa.addActionListener(e -> adicionarDespesa());
        btnEditarDespesa.addActionListener(e -> editarDespesa());
        btnEliminarDespesa.addActionListener(e -> eliminarDespesa());

        //atualizar tabela inicialmente
        atualizarTabela();
    }

    private void adicionarDespesa(){
        // verifica se o campo está preenchido
        String nome = JOptionPane.showInputDialog(this, "Nome da Despesa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o nome", "Nome inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // verifica se o campo está preenchido
        String valorStr = JOptionPane.showInputDialog(this, "Valor da despesa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o salário", "Salário inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double valor;

        // verifica se é uma valor valido
        try{
            valor = Double.parseDouble(valorStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido!");
            return;
        }

        // verifica se o campo está preenchido
        String mesStr = JOptionPane.showInputDialog(this, "Mes da Despesa:");
        if(mesStr == null || mesStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o mes", "Mes inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int mes;

        // verifica se é uma valor valido
        try{
            mes = Integer.parseInt(mesStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Mes inválido!");
            return;
        }

        // verifica se o mes é valido
        if(mes < 1 || mes > 12){
            JOptionPane.showMessageDialog(this, "Colcoque um mes valido", "Mes inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // verifica se o campo está preenchido
        String anoStr = JOptionPane.showInputDialog(this, "Ano da Despesa:");
        if(anoStr == null || anoStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o ano", "Ano inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int ano;

        // verifica se é uma valor valido
        try{
            ano = Integer.parseInt(anoStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ano inválido!");
            return;
        }

        despesaController.adicionarDespesa(nome, valor, mes, ano);
        JOptionPane.showMessageDialog(this, "Despesa adicionada com sucesso!");
        atualizarTabela();
    }

    private void editarDespesa(){
        // seleciona a despesa selecionando a linha da tabela
        int linhaSelecionada =tabelaDespesa.getSelectedRow();
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // guardar id da despesa selecionada
        int id = Integer.parseInt(tabelaDespesa.getValueAt(linhaSelecionada, 0).toString());

        // verifica se o campo está preenchido
        String nome = JOptionPane.showInputDialog(this, "Nome da Despesa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o nome", "Nome inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // verifica se o campo está preenchido
        String valorStr = JOptionPane.showInputDialog(this, "Valor da despesa:");
        if(nome == null || nome.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o salário", "Salário inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double valor;

        // verifica se é uma valor valido
        try{
            valor = Double.parseDouble(valorStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Valor inválido!");
            return;
        }

        // verifica se o campo está preenchido
        String mesStr = JOptionPane.showInputDialog(this, "Mes da Despesa:");
        if(mesStr == null || mesStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o mes", "Mes inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int mes;

        // verifica se é uma valor valido
        try{
            mes = Integer.parseInt(mesStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Mes inválido!");
            return;
        }

        // verifica se o mes é valido
        if(mes < 1 || mes > 12){
            JOptionPane.showMessageDialog(this, "Colcoque um mes valido", "Mes inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // verifica se o campo está preenchido
        String anoStr = JOptionPane.showInputDialog(this, "Ano da Despesa:");
        if(anoStr == null || anoStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha o ano", "Ano inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int ano;

        // verifica se é uma valor valido
        try{
            ano = Integer.parseInt(anoStr);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ano inválido!");
            return;
        }

        despesaController.editarDespesa(id, nome, valor, mes, ano);
        JOptionPane.showMessageDialog(this, "Pessoa editada com sucesso!");
        atualizarTabela();
    }

    public void eliminarDespesa(){
        // seleciona a despesa selecionando a linha da tabela
        int linhaSelecionada =tabelaDespesa.getSelectedRow();
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela primeiro", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // guardar id da despesa selecionada
        int id = Integer.parseInt(tabelaDespesa.getValueAt(linhaSelecionada, 0).toString());

        // remover despesa
        despesaController.removerDespesa(id);
        JOptionPane.showMessageDialog(this, "Despesa removida com sucesso!");
        atualizarTabela();
    }

    private void atualizarTabela(){
        tabela.setRowCount(0);

        List<Despesa> despesas = despesaController.listarDespesas();

        for (Despesa d: despesas){
            Object[] linha = {d.getId(), d.getNome(), d.getValor(), d.getMes(), d.getAno()};

            tabela.addRow(linha);
        }
    }
}
