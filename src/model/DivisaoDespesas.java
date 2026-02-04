package model;

public class DivisaoDespesas {
    private String nome;
    private double salario;
    private double percentagem;
    private double totalPagar;

    public DivisaoDespesas(String nome, double salario, double percentagem, double totalPagar) {
        this.nome = nome;
        this.salario = salario;
        this.percentagem = percentagem;
        this.totalPagar = totalPagar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPercentagem() {
        return percentagem;
    }

    public void setPercentagem(double percentagem) {
        this.percentagem = percentagem;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    @Override
    public String toString() {
        return "DivisaoDespesas{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", percentagem=" + percentagem +
                ", totalPagar=" + totalPagar +
                '}';
    }
}
