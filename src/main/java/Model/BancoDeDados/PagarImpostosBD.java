package Model.BancoDeDados;
import Model.BancoDeDados.ConexaoBD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagarImpostosBD {
    private ConexaoBD Conexao = new ConexaoBD();



    public void PagarImpostos() {
        int ImpostosCredito = 0;
        int ImpostosDebito = 0;
        int Resultado = 0;

        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT IMPOSTOS_DEBITO, IMPOSTOS_CREDITO FROM RAZONETE";
            ResultSet Pagar = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Pagar.next()) {
                ImpostosCredito += Integer.parseInt(Pagar.getString("Impostos_Credito"));
                ImpostosDebito += Integer.parseInt(Pagar.getString("Impostos_Debito"));

            }

            Resultado = Math.abs(ImpostosCredito - ImpostosDebito);

        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO RAZONETE(Impostos_Zerado) VALUES('%d')", Resultado);
            Conexao.getConexao().createStatement().executeQuery(SQL);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Conexao.AbrirConexao();
            String SQL = "UPDATE RAZONETE SET LUCROS_DEBITO = 0, LUCROS_CREDITO = 0";
            Conexao.getConexao().createStatement().executeQuery(SQL);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
