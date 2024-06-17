package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceteBD extends RazoneteBD {
    private ConexaoBD Conexao = new ConexaoBD();
    public List<BalanceteEntidade> BalanceteArray = new ArrayList<>();


    public List<BalanceteEntidade> SELECTBalancete() throws SQLException {
        int Aplicacoes_Credito = 0;
        int Aplicacoes_Debito = 0;
        int ARE_DESPESA = 0;
        int ARE_RECEITA = 0;
        int ARE_ZERADO = 0;
        int Ativo_Credito = 0;
        int Ativo_Debito = 0;
        int Banco_Credito = 0;
        int Banco_Debito = 0;
        int Caixa_Credito = 0;
        int Caixa_Debito = 0;
        int Caixa_Zerado = 0;
        int Clientes_Debito = 0;
        int Clientes_Credito = 0;
        int Contas_Debito = 0;
        int Contas_Credito = 0;


        // Add more variables as needed to match the SQL columns
        int DESPESAS = 0;
        int DESPESA_ZERADO = 0;
        int FINANCIAMENTO_CREDITO = 0;
        int FINANCIAMENTO_DEBITO = 0;
        int FINANCIAMENTOPAGO = 0;
        int FORNECEDOR_CREDITO = 0;
        int FORNECEDOR_DEBITO = 0;
        int FORNECEDORPAGO = 0;
        int FornecedoresLongoPrazo = 0;
        int Impostos_CREDITO = 0;
        int Impostos_DEBITO = 0;
        int Impostos_Zerado = 0;
        int INVESTIMENTO_CREDITO = 0;
        int INVESTIMENTO_DEBITO = 0;
        int Lucros_CREDITO = 0;
        int Lucros_DEBITO = 0;
        int Receita_CREDITO = 0;
        int Receita_DEBITO = 0;
        int Receita_Juros_CREDITO = 0;
        int Receita_Juros_DEBITO = 0;
        int Receita_Zerado = 0;



        // Variáveis de saldo para cada coluna
        int Saldo_Aplicacoes_Credito = 0;
        int Saldo_Aplicacoes_Debito = 0;
        int Saldo_ARE_DESPESA = 0;
        int Saldo_ARE_RECEITA = 0;
        int Saldo_ARE_ZERADO = 0;
        int Saldo_Ativo_Credito = 0;
        int Saldo_Ativo_Debito = 0;
        int Saldo_Banco_Credito = 0;
        int Saldo_Banco_Debito = 0;
        int Saldo_Caixa_Credito = 0;
        int Saldo_Caixa_Debito = 0;
        int Saldo_Caixa_Zerado = 0;
        int Saldo_Clientes_Credito = 0;
        int Saldo_Clientes_Debito = 0;
        int Saldo_Contas_Credito = 0;
        int Saldo_Contas_Debito = 0;
        int Saldo_DESPESAS = 0;
        int Saldo_DESPESA_ZERADO = 0;
        int Saldo_FINANCIAMENTO_CREDITO = 0;
        int Saldo_FINANCIAMENTO_DEBITO = 0;
        int Saldo_FINANCIAMENTOPAGO = 0;
        int Saldo_FORNECEDOR_CREDITO = 0;
        int Saldo_FORNECEDOR_DEBITO = 0;
        int Saldo_FORNECEDORPAGO = 0;
        int Saldo_FornecedoresLongoPrazo = 0;
        int Saldo_Impostos_CREDITO = 0;
        int Saldo_Impostos_DEBITO = 0;
        int Saldo_Impostos_Zerado = 0;
        int Saldo_INVESTIMENTO_CREDITO = 0;
        int Saldo_INVESTIMENTO_DEBITO = 0;
        int Saldo_Lucros_CREDITO = 0;
        int Saldo_Lucros_DEBITO = 0;
        int Saldo_Receita_CREDITO = 0;
        int Saldo_Receita_DEBITO = 0;
        int Saldo_Receita_Juros_CREDITO = 0;
        int Saldo_Receita_Juros_DEBITO = 0;
        int Saldo_Receita_Zerado = 0;




        int totalFinalCredito = 0;
        int totalFinalDebito = 0;




        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT * FROM RAZONETE";
            ResultSet Balancete = Conexao.getConexao().createStatement().executeQuery(SQL);


            while (Balancete.next()) {
                Aplicacoes_Credito += Integer.parseInt(Balancete.getString("Aplicacoes_CREDITO"));
                Aplicacoes_Debito += Integer.parseInt(Balancete.getString("Aplicacoes_DEBITO"));
                ARE_DESPESA += Integer.parseInt(Balancete.getString("ARE_DESPESA"));
                ARE_RECEITA += Integer.parseInt(Balancete.getString("ARE_RECEITA"));
                ARE_ZERADO += Integer.parseInt(Balancete.getString("ARE_ZERADO"));
                Ativo_Credito += Integer.parseInt(Balancete.getString("ATIVO_CREDITO"));
                Ativo_Debito += Integer.parseInt(Balancete.getString("ATIVO_DEBITO"));
                Banco_Credito += Integer.parseInt(Balancete.getString("BANCO_CREDITO"));
                Banco_Debito += Integer.parseInt(Balancete.getString("BANCO_DEBITO"));
                Caixa_Credito += Integer.parseInt(Balancete.getString("CAIXA_CREDITO"));
                Caixa_Debito += Integer.parseInt(Balancete.getString("CAIXA_DEBITO"));
                Caixa_Zerado += Integer.parseInt(Balancete.getString("CAIXA_ZERADO"));
                Clientes_Credito += Integer.parseInt(Balancete.getString("Clientes_CREDITO"));
                Clientes_Debito += Integer.parseInt(Balancete.getString("Clientes_DEBITO"));
                Contas_Credito += Integer.parseInt(Balancete.getString("Contas_CREDITO"));
                Contas_Debito += Integer.parseInt(Balancete.getString("Contas_DEBITO"));
                DESPESAS += Integer.parseInt(Balancete.getString("DESPESAS"));
                DESPESA_ZERADO += Integer.parseInt(Balancete.getString("DESPESA_ZERADO"));
                FINANCIAMENTO_CREDITO += Integer.parseInt(Balancete.getString("FINANCIAMENTO_CREDITO"));
                FINANCIAMENTO_DEBITO += Integer.parseInt(Balancete.getString("FINANCIAMENTO_DEBITO"));
                FINANCIAMENTOPAGO += Integer.parseInt(Balancete.getString("FINANCIAMENTOPAGO"));
                FORNECEDOR_CREDITO += Integer.parseInt(Balancete.getString("FORNECEDOR_CREDITO"));
                FORNECEDOR_DEBITO += Integer.parseInt(Balancete.getString("FORNECEDOR_DEBITO"));
                FORNECEDORPAGO += Integer.parseInt(Balancete.getString("FORNECEDORPAGO"));
                FornecedoresLongoPrazo += Integer.parseInt(Balancete.getString("FornecedoresLongoPrazo"));
                Impostos_CREDITO += Integer.parseInt(Balancete.getString("Impostos_CREDITO"));
                Impostos_DEBITO += Integer.parseInt(Balancete.getString("Impostos_DEBITO"));
                Impostos_Zerado += Integer.parseInt(Balancete.getString("Impostos_Zerado"));
                INVESTIMENTO_CREDITO += Integer.parseInt(Balancete.getString("INVESTIMENTO_CREDITO"));
                INVESTIMENTO_DEBITO += Integer.parseInt(Balancete.getString("INVESTIMENTO_DEBITO"));
                Lucros_CREDITO += Integer.parseInt(Balancete.getString("Lucros_CREDITO"));
                Lucros_DEBITO += Integer.parseInt(Balancete.getString("Lucros_DEBITO"));
                Receita_CREDITO += Integer.parseInt(Balancete.getString("Receita_CREDITO"));
                Receita_DEBITO += Integer.parseInt(Balancete.getString("Receita_DEBITO"));
                Receita_Juros_CREDITO += Integer.parseInt(Balancete.getString("Receita_Juros_CREDITO"));
                Receita_Juros_DEBITO += Integer.parseInt(Balancete.getString("Receita_Juros_DEBITO"));
                Receita_Zerado += Integer.parseInt(Balancete.getString("Receita_Zerado"));
            }


            // Aplicacoes
            if (Aplicacoes_Credito > Aplicacoes_Debito) {
                Saldo_Aplicacoes_Credito = Aplicacoes_Credito - Aplicacoes_Debito;
                totalFinalCredito += Saldo_Aplicacoes_Credito;
                BalanceteArray.add(new BalanceteEntidade("Aplicacoes", Aplicacoes_Credito, Aplicacoes_Debito, Saldo_Aplicacoes_Credito));
            }

            else {
                Saldo_Aplicacoes_Debito = Aplicacoes_Debito - Aplicacoes_Credito;
                totalFinalDebito += Saldo_Aplicacoes_Debito;
                BalanceteArray.add(new BalanceteEntidade("Aplicacoes", Aplicacoes_Credito, Aplicacoes_Debito, -Saldo_Aplicacoes_Debito));
            }

            // ARE_DESPESA
            if (ARE_DESPESA > 0) {
                BalanceteArray.add(new BalanceteEntidade("Despesas", ARE_DESPESA, ARE_DESPESA, 0));
            }

            else {
                BalanceteArray.add(new BalanceteEntidade("Despesas", DESPESA_ZERADO, DESPESA_ZERADO, 0));
            }

            // ARE_RECEITA
            if (ARE_RECEITA > 0) {
                BalanceteArray.add(new BalanceteEntidade("Receita", ARE_RECEITA, ARE_RECEITA, ARE_RECEITA));
            }

            else {
                BalanceteArray.add(new BalanceteEntidade("Receita", Receita_Zerado, Receita_Zerado, 0));
            }

            // Ativo
            if (Ativo_Credito > Ativo_Debito) {
                Saldo_Ativo_Credito = Ativo_Credito - Ativo_Debito;
                BalanceteArray.add(new BalanceteEntidade("Ativo", Ativo_Credito, Ativo_Debito, Saldo_Ativo_Credito));
            } else {
                Saldo_Ativo_Debito = Ativo_Debito - Ativo_Credito;
                BalanceteArray.add(new BalanceteEntidade("Ativo", Ativo_Credito, Ativo_Debito, Saldo_Ativo_Debito));
            }

                // Banco
            if (Banco_Credito > Banco_Debito) {
                Saldo_Banco_Credito = Banco_Credito - Banco_Debito;
                BalanceteArray.add(new BalanceteEntidade("Banco", Banco_Credito, Banco_Debito, Saldo_Banco_Credito));
            }

            else {
                Saldo_Banco_Debito = Banco_Debito - Banco_Credito;
                BalanceteArray.add(new BalanceteEntidade("Banco", Banco_Credito, Banco_Debito, -Saldo_Banco_Debito));
            }

            // Caixa
            if (Caixa_Credito > Caixa_Debito) {
                Saldo_Caixa_Credito = Caixa_Credito - Caixa_Debito;
                BalanceteArray.add(new BalanceteEntidade("Caixa", Caixa_Credito, Caixa_Debito, Saldo_Caixa_Credito));
            }

            else if(Caixa_Credito == 0 && Caixa_Debito == 0) {
                BalanceteArray.add(new BalanceteEntidade("Caixa", Caixa_Zerado, Caixa_Zerado, 0));
            }


            else {
                Saldo_Caixa_Debito = Caixa_Debito - Caixa_Credito;
                BalanceteArray.add(new BalanceteEntidade("Caixa", Caixa_Credito, Caixa_Debito, -Saldo_Caixa_Debito));
            }

            // Clientes
            if (Clientes_Credito > Clientes_Debito) {
                Saldo_Clientes_Credito = Clientes_Credito - Clientes_Debito;
                BalanceteArray.add(new BalanceteEntidade("Clientes", Clientes_Credito, Clientes_Debito, Saldo_Clientes_Credito));
            }

            else {
                Saldo_Clientes_Debito = Clientes_Debito - Clientes_Credito;
                BalanceteArray.add(new BalanceteEntidade("Clientes", Clientes_Credito, Clientes_Debito, -Saldo_Clientes_Debito));
            }

            // Contas
            if (Contas_Credito > Contas_Debito) {
                Saldo_Contas_Credito = Contas_Credito - Contas_Debito;
                BalanceteArray.add(new BalanceteEntidade("Contas", Contas_Credito, Contas_Debito, Saldo_Contas_Credito));
            }

            else {
                Saldo_Contas_Debito = Contas_Debito - Contas_Credito;
                BalanceteArray.add(new BalanceteEntidade("Contas", Contas_Credito, Contas_Debito, -Saldo_Contas_Debito));
            }



            // FINANCIAMENTO
            if (FINANCIAMENTO_CREDITO > FINANCIAMENTO_DEBITO) {
                Saldo_FINANCIAMENTO_CREDITO = FINANCIAMENTO_CREDITO - FINANCIAMENTO_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("FINANCIAMENTO", FINANCIAMENTO_CREDITO, FINANCIAMENTO_DEBITO, Saldo_FINANCIAMENTO_CREDITO));
            }

            else if(FINANCIAMENTO_CREDITO == 0 && FINANCIAMENTO_DEBITO == 0) {
                BalanceteArray.add(new BalanceteEntidade("FINANCIAMENTO", FINANCIAMENTOPAGO, FINANCIAMENTOPAGO, 0));
            }


            else {
                Saldo_FINANCIAMENTO_DEBITO = FINANCIAMENTO_DEBITO - FINANCIAMENTO_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("FINANCIAMENTO", FINANCIAMENTO_CREDITO, FINANCIAMENTO_DEBITO, -Saldo_FINANCIAMENTO_DEBITO));
            }

            // FORNECEDOR
            if (FORNECEDOR_CREDITO > FORNECEDOR_DEBITO) {
                Saldo_FORNECEDOR_CREDITO = FORNECEDOR_CREDITO - FORNECEDOR_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("FORNECEDOR", FORNECEDOR_CREDITO, FORNECEDOR_DEBITO, Saldo_FORNECEDOR_CREDITO));
            }

            else if(FORNECEDOR_CREDITO == 0 && FORNECEDOR_DEBITO == 0) {
                BalanceteArray.add(new BalanceteEntidade("FORNECEDOR", FORNECEDORPAGO, FORNECEDORPAGO, 0));
            }


            else {
                Saldo_FORNECEDOR_DEBITO = FORNECEDOR_DEBITO - FORNECEDOR_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("FORNECEDOR", FORNECEDOR_CREDITO, FORNECEDOR_DEBITO, -Saldo_FORNECEDOR_DEBITO));
            }

            // FornecedoresLongoPrazo
            if (FornecedoresLongoPrazo > 0) {
                BalanceteArray.add(new BalanceteEntidade("FornecedoresLongoPrazo", FornecedoresLongoPrazo, 0, FornecedoresLongoPrazo));
            } else {
                BalanceteArray.add(new BalanceteEntidade("FornecedoresLongoPrazo", 0, FornecedoresLongoPrazo, -FornecedoresLongoPrazo));
            }

            // Impostos
            if (Impostos_CREDITO > Impostos_DEBITO) {
                Saldo_Impostos_CREDITO = Impostos_CREDITO - Impostos_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("Impostos", Impostos_CREDITO, Impostos_DEBITO, Saldo_Impostos_CREDITO));
            }

            else if(Impostos_CREDITO == 0 && Impostos_DEBITO == 0) {
                
            }

            else {
                Saldo_Impostos_DEBITO = Impostos_DEBITO - Impostos_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("Impostos", Impostos_CREDITO, Impostos_DEBITO, -Saldo_Impostos_DEBITO));
            }

            // INVESTIMENTO
            if (INVESTIMENTO_CREDITO > INVESTIMENTO_DEBITO) {
                Saldo_INVESTIMENTO_CREDITO = INVESTIMENTO_CREDITO - INVESTIMENTO_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("INVESTIMENTO", INVESTIMENTO_CREDITO, INVESTIMENTO_DEBITO, Saldo_INVESTIMENTO_CREDITO));
            } else {
                Saldo_INVESTIMENTO_DEBITO = INVESTIMENTO_DEBITO - INVESTIMENTO_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("INVESTIMENTO", INVESTIMENTO_CREDITO, INVESTIMENTO_DEBITO, -Saldo_INVESTIMENTO_DEBITO));
            }

            // Lucros
            if (Lucros_CREDITO > Lucros_DEBITO) {
                Saldo_Lucros_CREDITO = Lucros_CREDITO - Lucros_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("Lucros", Lucros_CREDITO, Lucros_DEBITO, Saldo_Lucros_CREDITO));
            } else {
                Saldo_Lucros_DEBITO = Lucros_DEBITO - Lucros_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("Lucros", Lucros_CREDITO, Lucros_DEBITO, -Saldo_Lucros_DEBITO));
            }

            //Receita
            if (Receita_CREDITO > Receita_DEBITO) {
                Saldo_Receita_CREDITO = Receita_CREDITO - Receita_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("Receita", Receita_CREDITO, Receita_DEBITO, Saldo_Receita_CREDITO));
            }

            else {
                Saldo_Receita_DEBITO = Receita_DEBITO - Receita_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("Receita", Receita_CREDITO, Receita_DEBITO, -Saldo_Receita_DEBITO));
            }

            // Receita_Juros
            if (Receita_Juros_CREDITO > Receita_Juros_DEBITO) {
                Saldo_Receita_Juros_CREDITO = Receita_Juros_CREDITO - Receita_Juros_DEBITO;
                BalanceteArray.add(new BalanceteEntidade("Receita_Juros", Receita_Juros_CREDITO, Receita_Juros_DEBITO, Saldo_Receita_Juros_CREDITO));
            } else {
                Saldo_Receita_Juros_DEBITO = Receita_Juros_DEBITO - Receita_Juros_CREDITO;
                BalanceteArray.add(new BalanceteEntidade("Receita_Juros", Receita_Juros_CREDITO, Receita_Juros_DEBITO, -Saldo_Receita_Juros_DEBITO));
            }



        // Impostos_Zerado
            if (Impostos_Zerado > 0) {
                BalanceteArray.add(new BalanceteEntidade("Impostos_Zerado", 0, Impostos_Zerado, -Impostos_Zerado));
            } else {
                BalanceteArray.add(new BalanceteEntidade("Impostos_Zerado", Impostos_Zerado, 0, Impostos_Zerado));
            }

            // Receita_Zerado
            if (Receita_Zerado > 0) {
                BalanceteArray.add(new BalanceteEntidade("Receita_Zerado", 0, Receita_Zerado, -Receita_Zerado));
            } else {
                BalanceteArray.add(new BalanceteEntidade("Receita_Zerado", Receita_Zerado, 0, Receita_Zerado));
            }









        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return BalanceteArray;
    }
}







