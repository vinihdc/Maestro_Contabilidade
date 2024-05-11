package Model.BancoDeDados;

import Model.Entidade.RazoneteEntidade;
import org.apache.logging.log4j.message.ReusableMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RazoneteBD {

    private ConexaoBD Conexao = new ConexaoBD();


    public int getSaldoGeral() {
        return SaldoGeral;
    }

    public void setSaldoGeral(int saldoGeral) {
        SaldoGeral = saldoGeral;
    }

    private int SaldoGeral;

    private String Operacao;



    public List<RazoneteEntidade> SELECTRAZONETE(boolean CaixaZerado) throws SQLException {



        List<RazoneteEntidade> DadosRazonete = new ArrayList<>();

        try {
            Conexao.AbrirConexao();
            // dá o select no database
            String sql1 = "SELECT DETALHES_FATO, VALOR_FATO, FORMADEPAGAMENTO_FATO FROM FATOSGERAL";
            String sql2 = "SELECT * FROM CAIXA"; //SELECT CREDITO, DEBITO FROM CAIXA -> Caixa
            String sql3 = "SELECT CREDITO, DEBITO FROM FINANCIAMENTO";
            String sql4 = "SELECT * FROM CAIXAZERADO";
            ResultSet Fatos = Conexao.getConexao().createStatement().executeQuery(sql1);
            ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql2);
            ResultSet Financiamento = Conexao.getConexao().createStatement().executeQuery(sql3);
            ResultSet CaixaZeradoResult = Conexao.getConexao().createStatement().executeQuery(sql4);
            //while(S.next()) quer dizer que ele vai percorrer as colunas do banco enquanto não for null
            int SomaDebitoFatos = 0;
            int SomaCreditoFatos = 0;
            while(Fatos.next()) {
                String Detalhes = Fatos.getString("DETALHES_FATO");
                int valor = Integer.parseInt(Fatos.getString("VALOR_FATO"));
                String TipoOp = Fatos.getString("FORMADEPAGAMENTO_FATO");

                if(TipoOp.equals("Credito")) { //se a operação for credito
                    DadosRazonete.add(new RazoneteEntidade(Detalhes, valor, 0));
                    SomaCreditoFatos += valor;

                }

                else {  //se a operação for debito
                    DadosRazonete.add(new RazoneteEntidade(Detalhes, 0, valor));
                    SomaDebitoFatos += valor;
                }


            }

           int SomaDebitoCaixa = 0;
           int SomaCreditoCaixa = 0;
           int SomaCreditoFinanciamento = 0;
           int SomaDebitoFinanciamento = 0;
           int DebitoCaixaZerado = 0;
           int CreditoCaixaZerado = 0;

           if(CaixaZerado == true) {

               while(CaixaZeradoResult.next()) {
                   DebitoCaixaZerado = Integer.parseInt(Caixa.getString("DEBITO"));
                   CreditoCaixaZerado = Integer.parseInt(Caixa.getString("CREDITO"));
               }

               DadosRazonete.add(new RazoneteEntidade("CaixaZerado", DebitoCaixaZerado, CreditoCaixaZerado));


           }

           else {

               while (Caixa.next()) {

                   int DebitoCaixa = Integer.parseInt(Caixa.getString("DEBITO"));
                   int CreditoCaixa = Integer.parseInt(Caixa.getString("CREDITO"));


                   SomaDebitoCaixa += DebitoCaixa;
                   SomaCreditoCaixa += CreditoCaixa;


               }

               DadosRazonete.add(new RazoneteEntidade("Caixa", SomaDebitoCaixa, SomaCreditoCaixa));
           }

            while(Financiamento.next()) {
                int DebitoFinanciamento = Integer.parseInt(Financiamento.getString("DEBITO"));
                int CreditoFinanciamento = Integer.parseInt(Financiamento.getString("CREDITO"));
                DadosRazonete.add(new RazoneteEntidade("Financiamento", DebitoFinanciamento , CreditoFinanciamento));
                SomaCreditoFinanciamento += CreditoFinanciamento;
                SomaDebitoFinanciamento += DebitoFinanciamento;

            }








        }




        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        finally {
            Conexao.FecharConexao();
        }

        return DadosRazonete;
    }





}

