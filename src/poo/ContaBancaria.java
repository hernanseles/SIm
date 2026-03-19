package poo;

public class ContaBancaria {
    private int id;
    private double saldo;
    private String titular;

    public ContaBancaria(int id, String titular, double saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
            return;
        }
        saldo -= valor;
        System.out.println("Saque realizado");
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }
        saldo += valor;
        System.out.println("Depósito realizado");
    }
}
