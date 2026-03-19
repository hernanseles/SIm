package poo;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public void criaConta(ContaBancaria conta) {
        String sql = "INSERT INTO contas (titular, saldo) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getTitular());
            stmt.setDouble(2, conta.getSaldo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao criar conta: " + e.getMessage());
        }
    }

    public List<ContaBancaria> listarContas() {
        List<ContaBancaria> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titular = rs.getString("titular");
                double saldo = rs.getDouble("saldo");

                contas.add(new ContaBancaria(id, titular, saldo));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: " + e.getMessage());
        }

        return contas;
    }


    public void atualizarSaldo(ContaBancaria conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }


    public void deletarConta(int id) {
        String sql = "DELETE FROM contas WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar conta: " + e.getMessage());
        }
    }

    public boolean transferir(int idOrigem, int idDestino, double valor) {
        String sqlSaque = "UPDATE contas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
        String sqlDeposito = "UPDATE contas SET saldo = saldo + ? WHERE id = ?";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtSaque = conn.prepareStatement(sqlSaque);
                 PreparedStatement stmtDeposito = conn.prepareStatement(sqlDeposito)) {

                stmtSaque.setDouble(1, valor);
                stmtSaque.setInt(2, idOrigem);
                stmtSaque.setDouble(3, valor);
                int linhasSaque = stmtSaque.executeUpdate();

                if (linhasSaque == 0) {
                    conn.rollback();
                    return false;
                }

                stmtDeposito.setDouble(1, valor);
                stmtDeposito.setInt(2, idDestino);
                int linhasDeposito = stmtDeposito.executeUpdate();

                if (linhasDeposito == 0) {
                    conn.rollback();
                    return false;
                }

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Erro SQL na transação: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro de conexão na transferência: " + e.getMessage());
            return false;
        }
    }

    public boolean temContas() {
        String sql = "SELECT COUNT(*) FROM contas"; // Conta quantas linhas tem na tabela

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // pedido pro DB

            if (rs.next()) {//passa a ler o resultado do pedido
                int quantidade = rs.getInt(1); //pega esse resultado da primeira linha
                return quantidade > 0;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao checar se existem contas.");
        }
        return false; // Se der erro ou tiver zero, retorna false
    }
}