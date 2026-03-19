package poo;

import java.util.List;

public class Menu {
    Utilitarios s = new Utilitarios();
    private ContaService service = new ContaService();


    public void iniciar() {

        boolean rodando = true;

        while (rodando) {
            exibirMenu();
            int opcao = s.lerInt();
            switch (opcao) {

                case 1:
                    criarConta();
                    break;
                case 2:
                    sacar();
                    break;
                case 3:
                    deposito();
                    break;
                case 4:
                    transferencia();
                    break;
                case 5:
                    lista();
                    break;
                case 6:
                    delete();
                    break;
                case 7://Parar
                    rodando = false;
                    break;
                default://ERRO
                    System.out.println("Escolha um numero entre 1 e 6");
                    System.out.println("pressione ENTER para voltar");
                    s.lerString();
                    Utilitarios.limparConsole();
            }
        }
    }

    public void exibirMenu() {
        System.out.println();
        System.out.println("------Selecione uma opção------");
        System.out.println("\n1 Criar uma conta");
        System.out.println("2 Sacar");
        System.out.println("3 Depositar");
        System.out.println("4 Transferir");
        System.out.println("5 Listar contas");
        System.out.println("6 Deletar Conta");
        System.out.println("7 sair");
    }

    public void criarConta() {
        System.out.println("Nome do titular:");
        boolean criada = service.criarConta(s.lerString());

        if (criada) {
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Nome inválido.");
        }
        pausaS();
    }

    public void sacar() {
        List<ContaBancaria> contas = service.listarContas();

        if (contas.isEmpty()) {
            System.out.println("Crie uma conta primeiro!");
            pausa();
            return;
        }

        exibirContas(contas);

        System.out.println("Qual conta quer acessar?");
        int indice = s.lerInt();
        if (indice < 0 || indice >= contas.size()) {
            System.out.println("Conta inexistente.");
            return;
        }

        System.out.println("Quanto deseja sacar?");
        double valor = s.lerDouble();

        boolean sucesso = service.sacar(indice, valor);

        if (!sucesso) {
            System.out.println("Não foi possível realizar o saque.");
        }

        pausa();
    }


    public void deposito() {
        List<ContaBancaria> contas = service.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Crie uma conta primeiro!");
            pausa();
            return;
        }
        exibirContas(contas);

        System.out.println("Qual conta quer acessar?");
        int indice = s.lerInt();
        if (indice < 0 || indice >= contas.size()) {
            System.out.println("Conta inexistente.");
            return;
        }

        System.out.println("Quanto deseja depositar?");
        double valor = s.lerDouble();

        boolean sucesso = service.depositar(indice, valor);
        if (!sucesso) {
            System.out.println("Não foi possível realizar o deposito.");
        }
        pausa();
    }

    public void transferencia() {
        List<ContaBancaria> contas = service.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Crie sua conta primerio!");
            pausa();
            return;
        }
        exibirContas(contas);
        System.out.println("De onde sera feita a transferencia?");
        int origemIndice = s.lerInt();

        System.out.println("Para onde sera feita a transferencia?");

        int destinoIndice = s.lerInt();

        System.out.println("Digite o valor da transferencia:");
        double valor = s.lerDouble();

        boolean sucesso = service.transferir(origemIndice, destinoIndice, valor);

        if (sucesso) {
            System.out.println("Transferencia realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a transferencia.");
        }
        pausa();
    }

    public void lista() {
        List<ContaBancaria> contas = service.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Crie sua conta primerio!");
            pausa();
            return;
        }
        exibirContas(contas);
        pausa();
    }

    public void delete() {
        List<ContaBancaria> contas = service.listarContas();

        if (contas.isEmpty()) {
            System.out.println("Crie uma conta primeiro!");
            pausa();
            return;
        }

        exibirContas(contas);

        System.out.println("Qual conta deseja deletar?");
        int indice = s.lerInt();
        if (indice < 0 || indice >= contas.size()) {
            System.out.println("Conta inexistente.");
            return;
        }
        ContaBancaria conta = contas.get(indice);

        System.out.println("Tem certeza que deseja deletar a conta de " + conta.getTitular() + "?");
        System.out.println("Digite 1 para SIM e 2 para NÃO");
        String confirmacao = s.lerString();

        if (!confirmacao.equalsIgnoreCase("1")) {
            System.out.println("Operação cancelada.");
            return;
        }

        boolean sucesso = service.deletarConta(indice);

        if (sucesso) {
            System.out.println("Conta deletada com sucesso!");
        } else {
            System.out.println("Não foi possível deletar a conta.");
        }
    }

    public void pausa() {
        System.out.println("pressione ENTER para voltar");
        s.lerString();
        Utilitarios.limparConsole();
    }

    public void pausaS() {
        System.out.println("pressione ENTER para voltar");
        s.lerstring();
        Utilitarios.limparConsole();
    }

    private void exibirContas(List<ContaBancaria> contas) {
        for (int i = 0; i < contas.size(); i++) {
            ContaBancaria conta = contas.get(i);
            System.out.println(i + " - " + conta.getTitular() + " | Saldo: R$" + conta.getSaldo());
        }
    }
}



