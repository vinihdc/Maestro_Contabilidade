package Model.BancoDeDados;

import Model.Entidade.DiarioEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiarioBD {

    private ConexaoBD Conexao = new ConexaoBD();





    /*public List<DiarioEntidade> SELECTDIARIO() throws SQLException {


        List<DiarioEntidade> LivroDiario = new ArrayList<>();

       try {
           Conexao.AbrirConexao();
           // dá o select no database
           String sql1 = "SELECT ID, OPERACAO_FATO, VALOR_FATO, DETALHES_FATO, FORMADEPAGAMENTO_FATO FROM FATOSGERAL";
           String sql2 = "SELECT DETALHES, DEBITO, CREDITO FROM CAIXA";
           String sql3 = "SELECT DETALHES, DEBITO, CREDITO FROM FINANCIAMENTO";
           String sql4 = "SELECT DETALHES, DEBITO, CREDITO FROM INVESTIMENTO";
           String sql5 = "SELECT DETALHES, DEBITO, CREDITO FROM BANCO";
           String sql6 = "SELECT DETALHES, DEBITO, CREDITO FROM FORNECEDOR";
           String sql7 = "SELECT DETALHES, DEBITO, CREDITO FROM CAIXAZERADO";
           String sql8 = "SELECT DETALHES, DEBITO, CREDITO FROM FINANCIAMENTOPAGO";
           String sql9 = "SELECT DETALHES, DEBITO, CREDITO FROM FORNECEDORPAGO";



           ResultSet S = Conexao.getConexao().createStatement().executeQuery(sql1);
           ResultSet Caixa = Conexao.getConexao().createStatement().executeQuery(sql2);
           ResultSet Financiamento = Conexao.getConexao().createStatement().executeQuery(sql3);
           ResultSet Investimento = Conexao.getConexao().createStatement().executeQuery(sql4);
           ResultSet Banco = Conexao.getConexao().createStatement().executeQuery(sql5);
           ResultSet Fornecedor = Conexao.getConexao().createStatement().executeQuery(sql6);
           ResultSet CaixaZerado =  Conexao.getConexao().createStatement().executeQuery(sql7);
           ResultSet FinanciamentoPago =  Conexao.getConexao().createStatement().executeQuery(sql8);
           ResultSet FornecedorPago = Conexao.getConexao().createStatement().executeQuery(sql9);


               while(S.next()) {
                   String Detalhes = S.getString("DETALHES_FATO");
                   int valor = Integer.parseInt(S.getString("VALOR_FATO"));
                   String FormaDePagamento = S.getString("FORMADEPAGAMENTO_FATO");
                   LivroDiario.add(new DiarioEntidade("Ativos", Detalhes, valor, FormaDePagamento));

               }

               while(Caixa.next()) {
                   String Detalhes = Caixa.getString("DETALHES");
                   int ValorDebito = Integer.parseInt(Caixa.getString("DEBITO"));
                   int ValorCredito = Integer.parseInt(Caixa.getString("CREDITO"));
                   if(ValorDebito > ValorCredito) {
                       LivroDiario.add(new DiarioEntidade("Caixa", Detalhes, ValorDebito, "Debito"));
                   }

                   else {
                       LivroDiario.add(new DiarioEntidade("Caixa", Detalhes, ValorCredito, "Credito"));
                   }
               }


               while(Financiamento.next()) {
                   String Detalhes = Financiamento.getString("DETALHES");
                   int ValorDebito = Integer.parseInt(Financiamento.getString("DEBITO"));
                   int ValorCredito = Integer.parseInt(Financiamento.getString("CREDITO"));
                   if(ValorDebito > ValorCredito) {
                       LivroDiario.add(new DiarioEntidade("Financiamento", Detalhes, ValorDebito, "Debito"));
                   }

                   else {
                       LivroDiario.add(new DiarioEntidade("Financiamento", Detalhes, ValorCredito, "Credito"));
                   }
               }

           while(Investimento.next()) {
               String Detalhes = Investimento.getString("DETALHES");
               int ValorDebito = Integer.parseInt(Investimento.getString("DEBITO"));
               int ValorCredito = Integer.parseInt(Investimento.getString("CREDITO"));
               if (ValorDebito > ValorCredito) {
                   LivroDiario.add(new DiarioEntidade("Investimento", Detalhes, ValorDebito, "Debito"));
               } else {
                   LivroDiario.add(new DiarioEntidade("Investimento", Detalhes, ValorCredito, "Credito"));
               }
           }

               while(Banco.next()) {
                   String Detalhes = Banco.getString("DETALHES");
                   int ValorDebito = Integer.parseInt(Banco.getString("DEBITO"));
                   int ValorCredito = Integer.parseInt(Banco.getString("CREDITO"));
                   if(ValorDebito > ValorCredito) {
                       LivroDiario.add(new DiarioEntidade("Banco", Detalhes, ValorDebito, "Debito"));
                   }

                   else {
                       LivroDiario.add(new DiarioEntidade("Banco", Detalhes, ValorCredito, "Credito"));
                   }


           }

           while(Fornecedor.next()) {
               String Detalhes = Fornecedor.getString("DETALHES");
               int ValorDebito = Integer.parseInt(Fornecedor.getString("DEBITO"));
               int ValorCredito = Integer.parseInt(Fornecedor.getString("CREDITO"));
               if (ValorDebito > ValorCredito) {
                   LivroDiario.add(new DiarioEntidade("Fornecedor", Detalhes, ValorDebito, "Debito"));
               } else {
                   LivroDiario.add(new DiarioEntidade("Fornecedor", Detalhes, ValorCredito, "Credito"));
               }

           }


               while(CaixaZerado.next()) {
                   String Detalhes = CaixaZerado.getString("DETALHES");
                   int ValorDebito = Integer.parseInt(CaixaZerado.getString("DEBITO"));
                   int ValorCredito = Integer.parseInt(CaixaZerado.getString("CREDITO"));
                   if (ValorDebito > ValorCredito) {
                       LivroDiario.add(new DiarioEntidade("Deposito no Banco", Detalhes, ValorDebito, "Debito"));
                   }

                   else {
                       LivroDiario.add(new DiarioEntidade("Deposito no Banco", Detalhes, ValorCredito, "Credito"));
                   }

               }

           while(FinanciamentoPago.next()) {
               String Detalhes = FinanciamentoPago.getString("DETALHES");
               int ValorDebito = Integer.parseInt(FinanciamentoPago.getString("DEBITO"));
               int ValorCredito = Integer.parseInt(FinanciamentoPago.getString("CREDITO"));
               if (ValorDebito > ValorCredito) {
                   LivroDiario.add(new DiarioEntidade("Pagamento Financiamento", Detalhes, ValorDebito, "Debito"));
               }

               else {
                   LivroDiario.add(new DiarioEntidade("Pagamento Financiamento", Detalhes, ValorCredito, "Credito"));
               }

           }

           while(FornecedorPago.next()) {
               String Detalhes = FornecedorPago.getString("DETALHES");
               int ValorDebito = Integer.parseInt(FornecedorPago.getString("DEBITO"));
               int ValorCredito = Integer.parseInt(FornecedorPago.getString("CREDITO"));
               if (ValorDebito > ValorCredito) {
                   LivroDiario.add(new DiarioEntidade("Pagamento Fornecedor", Detalhes, ValorDebito, "Debito"));
               }

               else {
                   LivroDiario.add(new DiarioEntidade("Pagamento Fornecedor", Detalhes, ValorCredito, "Credito"));
               }

           }
































       }

       catch (SQLException e) {
           throw new RuntimeException(e);
       }

       finally {
           Conexao.FecharConexao();
       }

       return LivroDiario;


    }

         */
}
