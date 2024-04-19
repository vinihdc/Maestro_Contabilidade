package Model.BancoDeDados;

import java.sql.SQLException;

public class CaixaBD {
    private ConexaoBD Conexao = new ConexaoBD();

    public void RegistroNoCaixaDebito(int Valor, String Detalhes) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(DETALHES, CREDITO, DEBITO) VALUES('%s', '%d', '%d')", Detalhes, 0, Valor);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, debito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void RegistroNoCaixaCredito(int Valor, String Detalhes) {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO CAIXA(CREDITO, DEBITO) VALUES('%s', '%d', '%d')", Detalhes, Valor, 0);
            int ValorCaixa = Conexao.getConexao().createStatement().executeUpdate(SQL);
            System.out.println("Boa cadastrou no BD, credito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
