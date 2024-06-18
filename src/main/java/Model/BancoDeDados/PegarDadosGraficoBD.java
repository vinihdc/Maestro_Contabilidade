package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PegarDadosGraficoBD {
    private ConexaoBD Conexao = new ConexaoBD();


    public void PegarDadosGrafico(int Receita, int Despesa, int Lucro) {



        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT RECEITA_CREDITO, DESPESAS, LUCROS_CREDITO FROM RAZONETE";
            ResultSet DadosGrafico = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(DadosGrafico.next()) {
                Receita += Integer.parseInt(DadosGrafico.getString("RECEITA_CREDITO"));
                Despesa += Integer.parseInt(DadosGrafico.getString("DESPESAS"));
                Lucro += Integer.parseInt(DadosGrafico.getString("LUCROS_CREDITO"));

            }



        }




        catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


}
