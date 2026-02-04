package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame{

    private JButton btnPessoas;
    private JButton btnDespesas;
    private JButton btnRelatorios;

    public JanelaPrincipal(){
        setTitle("Gestão Financeira Doméstica");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents(){
        btnPessoas = new JButton("Pessoas");
        btnDespesas = new JButton("Despesas");
        btnRelatorios = new JButton("Relatórios");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        panel.add(btnPessoas);
        panel.add(btnDespesas);
        panel.add(btnRelatorios);
        add(panel);

        // adicionar ações aos botões
        btnPessoas.addActionListener(e -> abrirPessoas());
        btnDespesas.addActionListener(e -> abrirDespesas());
        btnRelatorios.addActionListener(e -> abrirRelatorios());
    }

    private void abrirPessoas() {
        PessoasPanel pessoasPanel = new PessoasPanel();
        pessoasPanel.setVisible(true);
    }

    private void abrirDespesas() {
        DespesasPanel despesasPanel = new DespesasPanel();
        despesasPanel.setVisible(true);
    }

    private void abrirRelatorios() {
        RelatoriosPanel relatoriosPanel = new RelatoriosPanel();
        relatoriosPanel.setVisible(true);
    }
}
