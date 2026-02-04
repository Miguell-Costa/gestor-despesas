package model;

// classe Despesa que define uma despesa

public class Despesa {
    private int id;
    private String nome;
    private double valor;
    private int mes;
    private int ano;

    // construtor com id
    public Despesa(int id, String nome, double valor, int mes, int ano) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
    }

    // contrutor sem id
    public Despesa(String nome, double valor, int mes, int ano) {
        this.nome = nome;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", mes=" + mes +
                ", ano=" + ano +
                '}';
    }
}
