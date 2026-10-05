package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar() throws SQLException {
        String url = obrigatoria("DB_URL");
        String usuario = obrigatoria("DB_USER");
        String senha = obrigatoria("DB_PASSWORD");

        return DriverManager.getConnection(url, usuario, senha);
    }

    private static String obrigatoria(String nome) throws SQLException {
        String valor = System.getenv(nome);
        if (valor == null || valor.trim().isEmpty()) {
            throw new SQLException("Variável de ambiente obrigatória não configurada: " + nome);
        }
        return valor;
    }
}
