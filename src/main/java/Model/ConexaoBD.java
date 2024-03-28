package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public Connection getConexao() {
        return Conexao;
    }

    public void setConexao(Connection conexao) {
        Conexao = conexao;
    }

    private Connection Conexao;

    public void AbrirConexao() {
        try {
            Conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MAESTROCONTABILIDADE", "root", "Lorenzo05*");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
