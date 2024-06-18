package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoPassivoCirculanteBD {

    //PassivoCirculante = Fornecedores, Impostos e Contas

    private ConexaoBD Conexao = new ConexaoBD();

    public void PagarPassivoCirculante() {
        int Fornecedores_Credito = 0;
        int Impostos_Credito = 0;
        int Impostos_Debito = 0;
        int Contas_Credito = 0;
        int TotalPassivoCirculante = 0;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT FORNECEDORES_CREDITO, IMPOSTOS_CREDITO, IMPOSTOS_DEBITO, CONTAS_CREDITO, CONTAS_DEBITO";
            ResultSet PassivoCirculante = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(PassivoCirculante.next()) {
                Fornecedores_Credito += Integer.parseInt(PassivoCirculante.getString("Fornecedores_Credito"));
                Impostos_Credito += Integer.parseInt(PassivoCirculante.getString("Impostos_Credito"));
                Contas_Credito += Integer.parseInt(PassivoCirculante.getString("Contas_Credito"));


            }

            TotalPassivoCirculante = (Fornecedores_Credito + Impostos_Credito + Contas_Credito);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO(CAIXA_CREDITO) VALUES('%d')", TotalPassivoCirculante);
            int Pagar = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //try {
            Conexao.AbrirConexao();

        //}



    }
}
