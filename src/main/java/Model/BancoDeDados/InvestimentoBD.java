package Model.BancoDeDados;

import org.springframework.web.util.pattern.PathPattern;

import java.sql.SQLException;

public class InvestimentoBD {
    private ConexaoBD Conexao = new ConexaoBD();
    public void RegistrarInvestimento(int Valor, String Detalhes) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO INVESTIMENTO(DETALHES, CREDITO, DEBITO) VALUES('%s', %d', '%d')", Detalhes, Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, credito-investimento");
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
