package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;
import org.apache.logging.log4j.message.ReusableMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RazoneteBD {

    private ConexaoBD Conexao = new ConexaoBD();



    public List<RazoneteEntidade> SELECTRAZONETE() throws SQLException {



        List<RazoneteEntidade> DadosRazonete = new ArrayList<>();

        int Caixa_Debito = 0;
        int Caixa_Credito = 0;
        int Investimento_Debito = 0;
        int Investimento_Credito = 0;
        int Ativo_Debito = 0;
        int Ativo_Credito = 0;
        int Financiamento_Debito = 0;
        int Financiamento_Credito = 0;
        int Banco_Debito = 0;
        int Banco_Credito = 0;
        int Fornecedor_Debito = 0;
        int Fornecedor_Credito = 0;
        int FornecedorPago = 0;
        int CaixaZerado = 0;
        int FinanciamentoPago = 0;

        int somaCaixa_Debito = 0;
        int somaCaixa_Credito = 0;
        int somaInvestimento_Debito = 0;
        int somaInvestimento_Credito = 0;
        int somaAtivo_Debito = 0;
        int somaAtivo_Credito = 0;
        int somaFinanciamento_Debito = 0;
        int somaFinanciamento_Credito = 0;
        int somaBanco_Debito = 0;
        int somaBanco_Credito = 0;
        int somaFornecedor_Debito = 0;
        int somaFornecedor_Credito = 0;
        int somaFornecedorPago = 0;
        int somaCaixaZerado = 0;
        int somaFinanciamentoPago = 0;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT CAIXA_DEBITO, CAIXA_CREDITO, INVESTIMENTO_DEBITO, INVESTIMENTO_CREDITO, ATIVO_DEBITO, ATIVO_CREDITO, FINANCIAMENTO_DEBITO, FINANCIAMENTO_CREDITO, BANCO_DEBITO, BANCO_CREDITO, FORNECEDOR_DEBITO, FORNECEDOR_CREDITO, FORNECEDORPAGO, CAIXAZERADO, FINANCIAMENTOPAGO FROM RAZONETE";
            ResultSet Razonete  = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Razonete.next()) {
                Caixa_Debito = Integer.parseInt(Razonete.getString("CAIXA_DEBITO"));
                Caixa_Credito = Integer.parseInt(Razonete.getString("CAIXA_CREDITO"));
                Investimento_Debito = Integer.parseInt(Razonete.getString("INVESTIMENTO_DEBITO"));
                Investimento_Credito = Integer.parseInt(Razonete.getString("INVESTIMENTO_CREDITO"));
                Ativo_Debito = Integer.parseInt(Razonete.getString("ATIVO_DEBITO"));
                Ativo_Credito = Integer.parseInt(Razonete.getString("ATIVO_CREDITO"));
                Financiamento_Debito = Integer.parseInt(Razonete.getString("FINANCIAMENTO_DEBITO"));
                Financiamento_Credito = Integer.parseInt(Razonete.getString("FINANCIAMENTO_CREDITO"));
                Banco_Debito = Integer.parseInt(Razonete.getString("BANCO_DEBITO"));
                Banco_Credito = Integer.parseInt(Razonete.getString("BANCO_CREDITO"));
                Fornecedor_Debito = Integer.parseInt(Razonete.getString("FORNECEDOR_DEBITO"));
                Fornecedor_Credito = Integer.parseInt(Razonete.getString("FORNECEDOR_CREDITO"));
                FornecedorPago = Integer.parseInt(Razonete.getString("FORNECEDORPAGO"));
                CaixaZerado = Integer.parseInt(Razonete.getString("CAIXAZERADO"));
                FinanciamentoPago = Integer.parseInt(Razonete.getString("FINANCIAMENTOPAGO"));

                // Calculando soma de algumas colunas
                somaCaixa_Debito += Caixa_Debito;
                somaCaixa_Credito += Caixa_Credito;
                somaInvestimento_Debito += Investimento_Debito;
                somaInvestimento_Credito += Investimento_Credito;
                somaAtivo_Debito += Ativo_Debito;
                somaAtivo_Credito += Ativo_Credito;
                somaFinanciamento_Debito += Financiamento_Debito;
                somaFinanciamento_Credito += Financiamento_Credito;
                somaBanco_Debito += Banco_Debito;
                somaBanco_Credito += Banco_Credito;
                somaFornecedor_Debito += Fornecedor_Debito;
                somaFornecedor_Credito += Fornecedor_Credito;
                somaFornecedorPago += FornecedorPago;
                somaCaixaZerado += CaixaZerado;
                somaFinanciamentoPago += FinanciamentoPago;

                
                
            }

            DadosRazonete.add(new RazoneteEntidade("Caixa", somaCaixa_Debito, somaCaixa_Credito));
            DadosRazonete.add(new RazoneteEntidade("Investimento", somaInvestimento_Debito, somaInvestimento_Credito));
            DadosRazonete.add(new RazoneteEntidade("Ativo", somaAtivo_Debito, somaAtivo_Credito));
            DadosRazonete.add(new RazoneteEntidade("Financiamento", somaFinanciamento_Debito, somaFinanciamento_Credito));
            DadosRazonete.add(new RazoneteEntidade("Banco", somaBanco_Debito, somaBanco_Credito));
            DadosRazonete.add(new RazoneteEntidade("Fornecedor", somaFornecedor_Debito, somaFornecedor_Credito));
            DadosRazonete.add(new RazoneteEntidade("FornecedorPago", somaFornecedorPago, somaFornecedorPago));
            DadosRazonete.add(new RazoneteEntidade("CaixaZerado", somaCaixaZerado, somaCaixaZerado));
            DadosRazonete.add(new RazoneteEntidade("FinanciamentoPago", somaFinanciamentoPago, somaFinanciamentoPago));


            //while(S.next()) quer dizer que ele vai percorrer as colunas do banco enquanto não for null


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

