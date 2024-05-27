package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;
import Model.Entidade.RazoneteEntidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceteBD extends RazoneteBD{
    private ConexaoBD Conexao = new ConexaoBD();
    public List<BalanceteEntidade> Balancete = new ArrayList<>();







    public List<BalanceteEntidade> SELECTBalancete() throws SQLException {

       List <BalanceteEntidade> BalanceteArray = new ArrayList<>();

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
        int Lucros_Credito = 0;
        int Clientes_Debito = 0;
        int Impostos_Credito = 0;
        int Impostos_Debito = 0;
        int Contas_Credito = 0;
        int Aplicacoes_Debito = 0;
        int FornecedoresLongoPrazo = 0;
        int Receita = 0;
        int Despesas = 0;

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
        int somaLucros_Credito = 0;
        int somaClientes_Debito = 0;
        int somaImpostos_Credito = 0;
        int somaImpostos_Debito = 0;
        int somaContas_Credito = 0;
        int somaAplicacoes_Debito = 0;
        int somaFornecedoresLongoPrazo = 0;
        int somaReceita = 0;
        int somaDespesas = 0;
        int ImpostoZerado = 0;




        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT * FROM RAZONETE";

            ResultSet Balancete = Conexao.getConexao().createStatement().executeQuery(SQL);


            while(Balancete.next()) {

                /*
                Caixa_Debito = Integer.parseInt(Balancete.getString("CAIXA_DEBITO"));
                Caixa_Credito = Integer.parseInt(Balancete.getString("CAIXA_CREDITO"));
                Investimento_Debito = Integer.parseInt(Balancete.getString("INVESTIMENTO_DEBITO"));
                Investimento_Credito = Integer.parseInt(Balancete.getString("INVESTIMENTO_CREDITO"));
                Ativo_Debito = Integer.parseInt(Balancete.getString("ATIVO_DEBITO"));
                Ativo_Credito = Integer.parseInt(Balancete.getString("ATIVO_CREDITO"));
                Financiamento_Debito = Integer.parseInt(Balancete.getString("FINANCIAMENTO_DEBITO"));
                Financiamento_Credito = Integer.parseInt(Balancete.getString("FINANCIAMENTO_CREDITO"));
                Banco_Debito = Integer.parseInt(Balancete.getString("BANCO_DEBITO"));
                Banco_Credito = Integer.parseInt(Balancete.getString("BANCO_CREDITO"));
                Fornecedor_Debito = Integer.parseInt(Balancete.getString("FORNECEDOR_DEBITO"));
                Fornecedor_Credito = Integer.parseInt(Balancete.getString("FORNECEDOR_CREDITO"));
                FornecedorPago = Integer.parseInt(Balancete.getString("FORNECEDORPAGO"));
                CaixaZerado = Integer.parseInt(Balancete.getString("CAIXAZERADO"));
                FinanciamentoPago = Integer.parseInt(Balancete.getString("FINANCIAMENTOPAGO"));


                somaLucros_Credito += Integer.parseInt(Balancete.getString("Lucros_CREDITO"));
                somaClientes_Debito += Integer.parseInt(Balancete.getString("Clientes_DEBITO"));
                somaImpostos_Credito += Integer.parseInt(Balancete.getString("Impostos_CREDITO"));
                somaImpostos_Debito += Integer.parseInt(Balancete.getString("Impostos_DEBITO"));
                somaContas_Credito += Integer.parseInt(Balancete.getString("Contas_CREDITO"));
                somaAplicacoes_Debito += Integer.parseInt(Balancete.getString("Aplicacoes_DEBITO"));
                somaFornecedoresLongoPrazo += Integer.parseInt(Balancete.getString("FornecedoresLongoPrazo"));
                somaReceita += Integer.parseInt(Balancete.getString("Receita_Zerado"));
                somaDespesas += Integer.parseInt(Balancete.getString("Despesa_Zerado"));
                ImpostoZerado +=  Integer.parseInt(Balancete.getString("Impostos_Zerado"));



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


                 */



                for(int i = 1; i < 34; i++) {
                    int Conteudo = Balancete.getInt(i);
                    int Conteudo2 = Balancete.getInt(i + 1);
                    int Saldo = Conteudo2 - Conteudo;
                    i += 2;
                    if(i == 5) {
                        i++;
                    }

                    /*
                    i = 1 || 1, 2
                    i = 3 || 3, 4
                    i = 5 || 6, 7
                    i = 8 || 8, 9,



                     */
                }
            }





            int Saldo_Caixa_Credito = 0;
            int Saldo_Caixa_Debito = 0;
            int Saldo_Investimento = 0;
            int Saldo_Ativos_Credito = 0;
            int Saldo_Ativos_Debito = 0;
            int Saldo_Financiamento_Credito = 0;
            int Saldo_Financiamento_Debito = 0;
            int Saldo_Banco_Debito = 0;
            int Saldo_Banco_Credito = 0;
            int Saldo_Fornecedor_Credito = 0;
            int Saldo_Fornecedor_Debito = 0;
            int saldoLucros_Credito = 0;
            int saldoClientes_Debito = 0;
            int saldoImpostos_Credito = 0;
            int saldoImpostos_Debito = 0;
            int saldoContas_Credito = 0;
            int saldoAplicacoes_Debito = 0;
            int saldoFornecedoresLongoPrazo = 0;
            int saldoReceita = 0;
            int saldoDespesas = 0;
            int saldoImpostoZerado = 0;


            //A subtracao Credito - Debito só é valida para Caixa, Banco e Financiamento





            //Confirmando se o caixa está zerado ou não
            if(somaCaixa_Credito == 0 && somaCaixa_Debito == 0) {
                BalanceteArray.add(new BalanceteEntidade("CaixaZerado", somaCaixaZerado, somaCaixaZerado, 0));
            }

            else {

                if(somaCaixa_Credito > somaCaixa_Debito) {
                    Saldo_Caixa_Credito = somaCaixa_Credito - somaCaixa_Debito;
                    BalanceteArray.add(new BalanceteEntidade("Caixa", somaCaixa_Debito, somaCaixa_Credito, Saldo_Caixa_Credito));
                }


                else {
                    Saldo_Caixa_Debito = somaCaixa_Debito - somaCaixa_Credito;
                    BalanceteArray.add(new BalanceteEntidade("Caixa", somaCaixa_Debito, somaCaixa_Credito, Saldo_Ativos_Debito));
                }



            }




            //Confirmando se o financiamento foi pago ou não
            if(somaFinanciamento_Credito == 0 && somaFinanciamento_Debito == 0) {
                BalanceteArray.add(new BalanceteEntidade("FinanciamentoPago", somaFinanciamentoPago, somaFinanciamentoPago, 0));
            }

            else {

                if(somaFinanciamento_Credito > somaFinanciamento_Debito) {
                    Saldo_Financiamento_Credito = somaFinanciamento_Credito - somaFinanciamento_Debito;
                    BalanceteArray.add(new BalanceteEntidade("FinanciamentoPago", somaFinanciamento_Debito, somaFinanciamento_Credito, Saldo_Financiamento_Credito));
                }

                else {
                    Saldo_Financiamento_Debito = somaFinanciamento_Debito - somaFinanciamento_Credito;
                    BalanceteArray.add(new BalanceteEntidade("FinanciamentoPago", somaFinanciamento_Debito, somaFinanciamento_Credito, Saldo_Financiamento_Debito));
                }

            }



            BalanceteArray.add(new BalanceteEntidade("Investimento", somaInvestimento_Debito, somaInvestimento_Credito, Saldo_Investimento));


           Saldo_Ativos_Debito = somaAtivo_Debito;
           Saldo_Ativos_Credito = somaAtivo_Credito;
           int Saldo_Ativo = somaAtivo_Debito - somaAtivo_Credito;
           BalanceteArray.add(new BalanceteEntidade("Ativo", somaAtivo_Debito, somaAtivo_Credito, Saldo_Ativo));





            if(somaFornecedor_Credito == 0 && somaFornecedor_Debito == 0) {
                BalanceteArray.add(new BalanceteEntidade("FornecedorPago", somaFornecedorPago, somaFornecedorPago, 0));
            }

            else {
                if(somaFornecedor_Credito > somaFornecedor_Debito) {
                    Saldo_Fornecedor_Credito = somaFornecedor_Credito - somaFornecedor_Debito;
                    BalanceteArray.add(new BalanceteEntidade("Fornecedor", somaFornecedor_Debito, somaFornecedor_Credito, Saldo_Ativos_Credito));
                }

                else {
                    Saldo_Fornecedor_Debito = somaFornecedor_Debito- somaFornecedor_Credito;
                    BalanceteArray.add(new BalanceteEntidade("Fornecedor", somaFornecedor_Debito, somaFornecedor_Credito, Saldo_Fornecedor_Debito));
                }

            }







            if(somaBanco_Credito > somaBanco_Debito) {
                Saldo_Banco_Credito = somaBanco_Credito - somaBanco_Debito;
                BalanceteArray.add(new BalanceteEntidade("Banco", somaBanco_Debito, somaBanco_Credito, Saldo_Banco_Credito));

            }

            else {
                Saldo_Banco_Debito = somaBanco_Debito - somaBanco_Credito;
                BalanceteArray.add(new BalanceteEntidade("Banco", somaBanco_Debito, somaBanco_Credito, Saldo_Banco_Debito));
            }



            //Puxar Lucros, coisas Zeradas - Receita, Despesa, Fornecedores a Longo Prazo, Clientes, Impostos, Contas, Aplicacoes


            if(somaImpostos_Credito == 0 && somaAplicacoes_Debito == 0) {
                BalanceteArray.add(new BalanceteEntidade("Impostos", ImpostoZerado, ImpostoZerado, 0));

            }

            else {
                if(somaImpostos_Credito > somaAplicacoes_Debito) {
                    saldoImpostos_Credito = somaImpostos_Credito - somaImpostos_Debito;
                    BalanceteArray.add(new BalanceteEntidade("Impostos", somaImpostos_Debito, somaImpostos_Credito, saldoImpostos_Credito));
                }

                else {
                    saldoImpostos_Debito = somaImpostos_Debito - somaImpostos_Credito;
                    BalanceteArray.add(new BalanceteEntidade("Impostos", somaImpostos_Debito, somaImpostos_Credito, saldoAplicacoes_Debito));
                }

            }


            BalanceteArray.add(new BalanceteEntidade("Clientes", somaClientes_Debito, 0, somaClientes_Debito));
            BalanceteArray.add(new BalanceteEntidade("Aplicacoes", somaAplicacoes_Debito, 0, somaAplicacoes_Debito));
            BalanceteArray.add(new BalanceteEntidade("Contas", 0, somaContas_Credito, somaContas_Credito));
            BalanceteArray.add(new BalanceteEntidade("Receita", somaReceita, 0, somaAplicacoes_Debito));









            int SaldoCredito = Saldo_Ativos_Credito + Saldo_Banco_Credito + Saldo_Financiamento_Credito + Saldo_Caixa_Credito + somaFornecedor_Credito + somaInvestimento_Credito;
            int SaldoDebito = Saldo_Caixa_Debito + Saldo_Banco_Debito + Saldo_Financiamento_Debito + Saldo_Ativos_Debito;
            int Saldo_Final = Math.abs(SaldoCredito - SaldoDebito);


            BalanceteArray.add(new BalanceteEntidade("Saldo Final", SaldoDebito, SaldoCredito, Saldo_Final));







        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

        return BalanceteArray;
    }


    }



    /*







     */

