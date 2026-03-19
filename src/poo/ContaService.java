package poo;

import java.util.List;

public class ContaService {
    private ContaDAO dao = new ContaDAO();

    public boolean criarConta(String titular) {
        if (titular == null || titular.trim().isEmpty()) {
            return false;
        }

        ContaBancaria conta = new ContaBancaria(titular.trim(), 0);
        dao.criaConta(conta);
        return true;
    }

    public List<ContaBancaria> listarContas() {
        return dao.listarContas();
    }

    public boolean sacar(int indice, double valor) {
        List<ContaBancaria> contas = dao.listarContas();

        if (contas.isEmpty()) {
            return false;
        }

        if (indice < 0 || indice >= contas.size()) {
            return false;
        }

        ContaBancaria conta = contas.get(indice);
        double saldoAntes = conta.getSaldo();

        conta.sacar(valor);

        if (conta.getSaldo() == saldoAntes) {
            return false;
        }

        dao.atualizarSaldo(conta);
        return true;
    }

    public boolean depositar(int indice, double valor) {
        List<ContaBancaria> contas = dao.listarContas();

        if (contas.isEmpty()) {
            return false;
        }

        if (indice < 0 || indice >= contas.size()) {
            return false;
        }

        ContaBancaria conta = contas.get(indice);
        double saldoAntes = conta.getSaldo();

        conta.depositar(valor);

        if (conta.getSaldo() == saldoAntes) {
            return false;
        }

        dao.atualizarSaldo(conta);
        return true;
    }

    public boolean transferir(int indiceOrigem, int indiceDestino, double valor) {
        List<ContaBancaria> contas = dao.listarContas();

        if (contas.isEmpty()) {
            return false;
        }

        if (indiceOrigem < 0 || indiceOrigem >= contas.size()) {
            return false;
        }

        if (indiceDestino < 0 || indiceDestino >= contas.size()) {
            return false;
        }

        if (indiceOrigem == indiceDestino) {
            return false;
        }

        if (valor <= 0) {
            return false;
        }

        ContaBancaria origem = contas.get(indiceOrigem);
        ContaBancaria destino = contas.get(indiceDestino);

        return dao.transferir(origem.getId(), destino.getId(), valor);
    }
    public boolean deletarConta(int indice) {
        List<ContaBancaria> contas = dao.listarContas();

        if (contas.isEmpty()) {
            return false;
        }

        if (indice < 0 || indice >= contas.size()) {
            return false;
        }

        ContaBancaria conta = contas.get(indice);
        dao.deletarConta(conta.getId());
        return true;
    }

}
