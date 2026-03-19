package poo;
import java.util.ArrayList;
public class Banco {

    private ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public void listarContas() {

        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + " - "
                    + contas.get(i).getTitular()
                    + " | Saldo: R$"
                    + contas.get(i).getSaldo());
        }
    }

    public ContaBancaria pegarConta(int indice) {

        if (indice < 0 || indice >= contas.size()) {
            return null;
        }

        return contas.get(indice);
    }

    public boolean temContas() {
        return !contas.isEmpty();
    }
}