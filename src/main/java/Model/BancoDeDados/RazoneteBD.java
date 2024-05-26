package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.ReusableMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RazoneteBD {

    private ConexaoBD Conexao = new ConexaoBD();


    /*
    (TabelaSQL)             (Usuario)           (Nova Formatação, com JS)
    FornecedorPago -> Pagamento Fornecedor -> PagamentoFornecedor
    FinanciamentoPago -> Pagamento do Financiamento -> PagamentoFinanciamento
    CaixaZerado -> Deposito no Banco -> DepositonoBanco
    FornecedoresLongoPrazo -> Fornecedores a longo prazo -> Fornecedoresalongoprazo
    Impostos_Zerado -> Pagamento de impostos -> Pagamentodeimpostos
    Receita_juros -> Receita dos juros -> receitadosjuros




     */





    public List<RazoneteEntidade> VerificarElementosDiferentes(String ElementoParam) throws SQLException {
        String SQL = "";
        int Saldo = 0;
        int Saldo2 = 0;
        int Saldo3 = 0;
        String NomeColuna = "";
        String NomeColuna2 = "";
        String Operacao = "";
        boolean SemRazoneteExceptions = false;
        boolean ARE = false;

        List<RazoneteEntidade> Razonete = new ArrayList<>();


        try {


            Conexao.AbrirConexao();



            switch (ElementoParam) {
                case "P_Fornecedor":
                    SQL = "SELECT FORNECEDORPAGO FROM RAZONETE";
                    NomeColuna = "FORNECEDORPAGO";
                    Operacao = "Pagamento Fornecedor";
                    break;
                case "P_Financiamento":
                    SQL = "SELECT FINANCIAMENTOPAGO FROM RAZONETE";
                    NomeColuna = "FINANCIAMENTOPAGO";
                    Operacao = "Pagamento Financiamento";
                    break;


                case "D_Caixa":
                    SQL = "SELECT CAIXA_ZERADO FROM RAZONETE";
                    NomeColuna = "FINANCIAMENTOPAGO";
                    Operacao = "Dinheiro depositado do caixa para o banco";
                    break;

                case "Fornecedores_L":
                    SQL = "SELECT FORNECEDORESLONGOPRAZO FROM RAZONETE";
                    NomeColuna = "FORNECEDORESLONGOPRAZO";
                    Operacao = "Fornecedores a longo prazo";
                    break;

                case "P_Impostos":
                    SQL = "SELECT IMPOSTOS_ZERADO FROM RAZONETE";
                    NomeColuna = "IMPOSTOS_ZERADO";
                    Operacao = "Pagamento dos impostos";
                    break;
                case "Receita_J":
                    SQL = "SELECT RECEITA_Juros_Debito FROM RAZONETE";
                    NomeColuna = "RECEITA_Juros_Debito ";
                    Operacao = "Receita dos juros ";
                    break;

                case "ARE":
                    SQL = "SELECT ARE_RECEITA, ARE_DESPESA FROM RAZONETE";
                    NomeColuna = "ARE_RECEITA";
                    NomeColuna2 = "ARE_DESPESA";
                    Operacao = "Apuração do Resultado";
                    break;



                default:
                    SemRazoneteExceptions = true;



            }

            if(SemRazoneteExceptions == false) {
                ResultSet RazoneteExceptions = Conexao.getConexao().createStatement().executeQuery(SQL);


                if(ARE == true) {
                    while (RazoneteExceptions.next()) {
                        Saldo += Integer.parseInt(RazoneteExceptions.getString(NomeColuna));


                    }


                    while(RazoneteExceptions.next()) {
                        Saldo2 += Integer.parseInt(RazoneteExceptions.getString(NomeColuna2));
                    }

                    Saldo3 = Math.abs(Saldo2);
                    Razonete.add(new RazoneteEntidade(Operacao, Saldo, Saldo2, Saldo3));
                    return Razonete;
                }




                else {

                    while (RazoneteExceptions.next()) {
                        Saldo += Integer.parseInt(RazoneteExceptions.getString(NomeColuna));


                    }
                }




            }



            Razonete.add(new RazoneteEntidade(Operacao, Saldo, Saldo, 0));


            return Razonete;


        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public List<RazoneteEntidade> Razao (String Elemento) {






        List<RazoneteEntidade> Razonete = new ArrayList<>();
        int somaCredito = 0;
        int somaDebito = 0;
        int Saldo = 0;

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT %s_Debito, %s_Credito FROM RAZONETE", Elemento, Elemento);
            String CreditoSQL = String.format("%s_Credito", Elemento);
            String DebitoSQL = String.format("%s_Debito", Elemento);
            String PagamentosSQL = String.format("%s", Elemento);
            ResultSet ArmazenarRazonete = Conexao.getConexao().createStatement().executeQuery(SQL);


                while (ArmazenarRazonete.next()) {
                    somaCredito += Integer.parseInt(ArmazenarRazonete.getString(CreditoSQL));
                    somaDebito += Integer.parseInt(ArmazenarRazonete.getString(DebitoSQL));
                }

                Saldo = somaDebito - somaCredito;




        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Razonete.add(new RazoneteEntidade(Elemento, somaDebito, somaCredito, Saldo));

        return Razonete;

    }









}

