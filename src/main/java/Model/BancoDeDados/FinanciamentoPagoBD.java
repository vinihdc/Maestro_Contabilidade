package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanciamentoPagoBD {

    private ConexaoBD Conexao = new ConexaoBD();

    CaixaBD RegistrarNoCaixa = new CaixaBD();

    public void FinanciamentoPago(String CodFato, String Detalhes, String Data) throws SQLException {

        //Faz a operação para pagar o Financiamento
        int Debito = 0;
        int Credito = 0;
        int SomaCredito = 0;
        int SomaDebito = 0;
        int Subtracao = 0;
        int SaldoFinal = 0;
        try {
            Conexao.AbrirConexao();
            String SqlFinanciamento = "SELECT * FROM FINANCIAMENTO";
            ResultSet FinanciamentoPago = Conexao.getConexao().createStatement().executeQuery(SqlFinanciamento);
            while(FinanciamentoPago.next()) {
                Credito = Integer.parseInt(FinanciamentoPago.getString("CREDITO"));
                Debito = Integer.parseInt(FinanciamentoPago.getString("DEBITO"));
                SomaDebito += Debito;
                SomaCredito += Credito;

            }

            if(SomaDebito > SomaCredito) {
                SaldoFinal = SomaDebito - SomaCredito;


                /*


                Credito Debito
                156.500  1000

                Subtracao = 156.500 - 1000 = 155.500
                SaldoFinal = 155.500 + 1000 = 156.500




                 */






            }

            else {
                SaldoFinal = SomaCredito - SomaDebito;

            }



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Manda os dados da financiamento pago, que por exemplo caixa no saldo janeiro: 27.000 - 14.000 = 13.000 - Credito, 13.000 - Debito para outra tabela

        try {
            Conexao.AbrirConexao();
            String sql = String.format("INSERT INTO FINANCIAMENTOPAGO(CREDITO,DEBITO, DATA) VALUES('%d', '%d', '%s')", SaldoFinal, SaldoFinal, Data);
            int ExecutarComando = Conexao.getConexao().createStatement().executeUpdate(sql);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }




        //Vai mandar para o credito do caixa
        RegistrarNoCaixa.RegistroNoCaixaCredito(CodFato, SaldoFinal, Detalhes, Data);




        //Deleta os dados da financiamento

        try {
            Conexao.AbrirConexao();
            String sql = "TRUNCATE FINANCIAMENTO";
            int TruncarFinanciamento = Conexao.getConexao().createStatement().executeUpdate(sql);


        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        finally {
            Conexao.FecharConexao();
        }
    }


}
