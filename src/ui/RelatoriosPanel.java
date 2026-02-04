package ui;

import controller.CalculoController;
import model.DivisaoDespesas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RelatoriosPanel extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private CalculoController calculoController;
    private JButton btnListarPorMes;

    public RelatoriosPanel() {
        setTitle("Relatório de Despesas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        calculoController = new CalculoController();

        initComponents();
        carregarDados();
    }

    private void initComponents() {

        // criar botão
        btnListarPorMes = new JButton("Lista Por Mes");

        // criar coluna
        String[] colunas = {"Nome", "Salário", "%", "Valor a pagar"};
        model = new DefaultTableModel(colunas, 0);
        tabela = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabela);


        // criar painel para os botões
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBotoes.add(btnListarPorMes);

        // organizar layout
        setLayout(new BorderLayout());
        add(panelBotoes, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // acções botões
        btnListarPorMes.addActionListener(e -> listarDivisaoPorMes());
    }

    // lista todos os dados
    private void carregarDados() {

        model.setRowCount(0);

        List<DivisaoDespesas> dados = calculoController.calcularDivisao();

        for (DivisaoDespesas d : dados) {
            model.addRow(new Object[]{
                    d.getNome(),
                    d.getSalario(),
                    String.format("%.2f", d.getPercentagem()) + " %",
                    String.format("%.2f €", d.getTotalPagar())
            });
        }
    }

    // lista dados de um determinado mes
    private void listarDivisaoPorMes(){
        model.setRowCount(0);
        String mesStr = JOptionPane.showInputDialog(this, "Introduza o mês (1-12):");
        String anoStr = JOptionPane.showInputDialog(this, "Introduza o ano:");

        try {
            int mes = Integer.parseInt(mesStr);
            int ano = Integer.parseInt(anoStr);

            if (!calculoController.existeMesAno(mes, ano)) {
                JOptionPane.showMessageDialog(this,
                        "Não existem despesas registadas para " + mes + "/" + ano,
                        "Erro",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // se existe, calcula e mostra resultados
            List<DivisaoDespesas> dados = calculoController.calcularDivisaoPorMes(mes, ano);

            for (DivisaoDespesas d : dados) {
                model.addRow(new Object[]{
                        d.getNome(),
                        d.getSalario(),
                        String.format("%.2f", d.getPercentagem()) + " %",
                        String.format("%.2f €", d.getTotalPagar())
                });
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Mês e ano devem ser números válidos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
