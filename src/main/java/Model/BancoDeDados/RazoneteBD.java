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


    public List<RazoneteEntidade> SELECTRAZONETE(String Elemento_Contabil) throws SQLException {



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

        int somaAplicacoes_Debito = 0;
        int somaAplicacoes_Credito = 0;
        int somaLucros_Debito = 0;
        int somaLucros_Credito = 0;
        int somaContas_Debito = 0;
        int somaContas_Credito = 0;
        int somaClientes_Debito = 0;
        int somaClientes_Credito = 0;
        int somaImpostos_Debito = 0;
        int somaImpostos_Credito = 0;
        int somaReceita_Juros_Debito = 0;
        int somaReceita_Juros_Credito = 0;
        int somaReceita_Debito = 0;
        int somaReceita_Credito = 0;
        int somaFornecedoresLongoPrazo = 0;






        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT "
                    + "CAIXA_DEBITO, CAIXA_CREDITO, "
                    + "INVESTIMENTO_DEBITO, INVESTIMENTO_CREDITO, "
                    + "ATIVO_DEBITO, ATIVO_CREDITO, "
                    + "FINANCIAMENTO_DEBITO, FINANCIAMENTO_CREDITO, "
                    + "BANCO_DEBITO, BANCO_CREDITO, "
                    + "FORNECEDOR_DEBITO, FORNECEDOR_CREDITO, "
                    + "FORNECEDORPAGO, CAIXAZERADO, "
                    + "FINANCIAMENTOPAGO, "
                    + "Aplicacoes_DEBITO, Aplicacoes_CREDITO, "
                    + "Lucros_DEBITO, Lucros_CREDITO, "
                    + "Contas_DEBITO, Contas_CREDITO, "
                    + "Clientes_DEBITO, Clientes_CREDITO, "
                    + "Impostos_DEBITO, Impostos_CREDITO, "
                    + "Receita_Juros_DEBITO, Receita_Juros_CREDITO, "
                    + "Receita_DEBITO, Receita_CREDITO, "
                    + "FornecedoresLongoPrazo "
                    + "FROM RAZONETE";

            ResultSet Razonete  = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Razonete.next()) {
                somaCaixa_Debito += Integer.parseInt(Razonete.getString("CAIXA_DEBITO"));
                somaCaixa_Credito += Integer.parseInt(Razonete.getString("CAIXA_CREDITO"));
                somaInvestimento_Debito += Integer.parseInt(Razonete.getString("INVESTIMENTO_DEBITO"));
                somaInvestimento_Credito += Integer.parseInt(Razonete.getString("INVESTIMENTO_CREDITO"));
                somaAtivo_Debito += Integer.parseInt(Razonete.getString("ATIVO_DEBITO"));
                somaAtivo_Credito += Integer.parseInt(Razonete.getString("ATIVO_CREDITO"));
                somaFinanciamento_Debito += Integer.parseInt(Razonete.getString("FINANCIAMENTO_DEBITO"));
                somaFinanciamento_Credito += Integer.parseInt(Razonete.getString("FINANCIAMENTO_CREDITO"));
                somaBanco_Debito += Integer.parseInt(Razonete.getString("BANCO_DEBITO"));
                somaBanco_Credito += Integer.parseInt(Razonete.getString("BANCO_CREDITO"));
                somaFornecedor_Debito += Integer.parseInt(Razonete.getString("FORNECEDOR_DEBITO"));
                somaFornecedor_Credito += Integer.parseInt(Razonete.getString("FORNECEDOR_CREDITO"));
                somaFornecedorPago += Integer.parseInt(Razonete.getString("FORNECEDORPAGO"));
                somaCaixaZerado += Integer.parseInt(Razonete.getString("CAIXAZERADO"));
                somaFinanciamentoPago += Integer.parseInt(Razonete.getString("FINANCIAMENTOPAGO"));




                somaAplicacoes_Debito += Integer.parseInt(Razonete.getString("Aplicacoes_DEBITO"));
                somaAplicacoes_Credito += Integer.parseInt(Razonete.getString("Aplicacoes_CREDITO"));
                somaLucros_Debito += Integer.parseInt(Razonete.getString("Lucros_DEBITO"));
                somaLucros_Credito += Integer.parseInt(Razonete.getString("Lucros_CREDITO"));
                somaContas_Debito += Integer.parseInt(Razonete.getString("Contas_DEBITO"));
                somaContas_Credito += Integer.parseInt(Razonete.getString("Contas_CREDITO"));
                somaClientes_Debito += Integer.parseInt(Razonete.getString("Clientes_DEBITO"));
                somaClientes_Credito += Integer.parseInt(Razonete.getString("Clientes_CREDITO"));
                somaImpostos_Debito += Integer.parseInt(Razonete.getString("Impostos_DEBITO"));
                somaImpostos_Credito += Integer.parseInt(Razonete.getString("Impostos_CREDITO"));
                somaReceita_Juros_Debito += Integer.parseInt(Razonete.getString("Receita_Juros_DEBITO"));
                somaReceita_Juros_Credito += Integer.parseInt(Razonete.getString("Receita_Juros_CREDITO"));
                somaReceita_Debito += Integer.parseInt(Razonete.getString("Receita_DEBITO"));
                somaReceita_Credito += Integer.parseInt(Razonete.getString("Receita_CREDITO"));
                somaFornecedoresLongoPrazo += Integer.parseInt(Razonete.getString("FornecedoresLongoPrazo"));




                
                
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


            DadosRazonete.add(new RazoneteEntidade("Aplicacoes", somaAplicacoes_Debito, somaAplicacoes_Credito));
            DadosRazonete.add(new RazoneteEntidade("Lucros", somaLucros_Debito, somaLucros_Credito));
            DadosRazonete.add(new RazoneteEntidade("Contas", somaContas_Debito, somaContas_Credito));
            DadosRazonete.add(new RazoneteEntidade("Clientes", somaClientes_Debito, somaClientes_Credito));
            DadosRazonete.add(new RazoneteEntidade("Impostos", somaImpostos_Debito, somaImpostos_Credito));
            DadosRazonete.add(new RazoneteEntidade("Receita_Juros", somaReceita_Juros_Debito, somaReceita_Juros_Credito));
            DadosRazonete.add(new RazoneteEntidade("Receita", somaReceita_Debito, somaReceita_Credito));
            DadosRazonete.add(new RazoneteEntidade("FornecedoresLongoPrazo", somaFornecedoresLongoPrazo, somaFornecedoresLongoPrazo));

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

