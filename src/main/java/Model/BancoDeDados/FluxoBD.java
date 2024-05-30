package Model.BancoDeDados;

import com.beust.ah.A;
import org.apache.coyote.BadRequestException;

import java.sql.SQLException;

public class FluxoBD {
    private ConexaoBD Conexao = new ConexaoBD();

    private DepositoNoBancoBD ZerarCaixa = new  DepositoNoBancoBD();

    private FinanciamentoPagoBD PagarFinanciamento = new FinanciamentoPagoBD();

    private FornecedorPagoBD PagarFornecedor = new FornecedorPagoBD();

    private DiarioBD Diario = new DiarioBD();

    public void Fluxo(String CodFato, String Data, String Detalhes, int Valor) throws SQLException {


        //tudo que é Ativo -> Veiculo, Imovel, bens no geral está Detalhes, Detalhes no Diario desse jeito:  Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Caixa", Valor);


        try {
            String SQL = "1";
            int ExecutarSQL;
            Conexao.AbrirConexao();
            switch (CodFato) {
                //Ativo - a vista
                case "001":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Ativo", "Caixa", Valor);
                    break;
                //Ativo a Longo prazo
                case "002":
                    SQL = String.format("INSERT INTO RAZONETE(FINANCIAMENTO_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Financiamento", Valor);
                    break;

                //Ativo - 50 50
                case "003":
                    int Metade = Valor / 2;
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO, FINANCIAMENTO_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d', '%d')", Metade, Metade, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Financiamento e Caixa", Valor);
                    break;
                 //Investimento
                case "004":
                    SQL = String.format("INSERT INTO RAZONETE(INVESTIMENTO_CREDITO, CAIXA_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Caixa", "Investimento", Valor);
                    break;

                //Emprestimo
                case "005":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_DEBITO, ATIVO_CREDITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Caixa", Detalhes, Valor);
                    break;

                //Pagamento de divida
                case "006":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO, FINANCIAMENTO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Financiamento", "Caixa", Valor);
                    break;
                //Banco - 80% e Fornecedor 20% - Ativo
                case "007":
                    int PorcentagemBanco = (int) (Valor * 0.8);
                    int PorcentagemFornecedor =  (int) (Valor * 0.2);
                    SQL = String.format("INSERT INTO RAZONETE(BANCO_CREDITO, FORNECEDOR_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d', '%d')", PorcentagemBanco, PorcentagemFornecedor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Banco, Fornecedor", Valor);
                    break;

                //Fornecedor a prazo
                case "008":
                    SQL = String.format("INSERT INTO RAZONETE(FORNECEDOR_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Fornecedor", Valor);
                    break;

                //Deposito no  banco
                case "009":
                    ZerarCaixa.RegistrarDeposito();
                    break;

                //Pagamento Financiamento
                case "010":
                    PagarFinanciamento.FinanciamentoPago();
                    break;

                //Pagamento Fornecedor
                case "011":
                    PagarFornecedor.FornecedorPago();
                    break;

                //Pagamento com o cartão do banco
                case "012":
                    SQL = String.format("INSERT INTO RAZONETE(BANCO_CREDITO, ATIVO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Banco", Valor);
                    break;



                    //Eventos Manuais


                //Colocar Dinheiro no Debito do Banco
                case "013":
                    SQL = String.format("INSERT INTO RAZONETE(BANCO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;
                //Colocar dinheiro no debito do caixa
                case "014":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

                //Colocar Lucros
                case "016":
                    SQL = String.format("INSERT INTO RAZONETE(LUCROS_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;


                //Aplicacoes no Debito
                case "017":
                    SQL = String.format("INSERT INTO RAZONETE(APLICACOES_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

                 //Fornecedores Credito
                case "018":
                    SQL = String.format("INSERT INTO RAZONETE(FORNECEDOR_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;


                //Fornecedores a Longo prazo
                case "019":
                    SQL = String.format("INSERT INTO RAZONETE(FORNECEDORESLONGOPRAZO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;


                //Clientes - Debito
                case "020":
                    SQL = String.format("INSERT INTO RAZONETE(CLIENTES_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

               //Impostos - Credito
                case "021":
                    SQL = String.format("INSERT INTO RAZONETE(IMPOSTOS_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

                //Contas
                case "022":
                    SQL = String.format("INSERT INTO RAZONETE(CONTAS_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

                 //Ativos - Debito
                case "023":
                    SQL = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;

                    //Investimento
                case "024":
                    SQL = String.format("INSERT INTO RAZONETE(INVESTIMENTO_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Saldo Anterior", "Saldo Anterior", Valor);
                    break;



                    //Eventos não manuais


                //Efetuar pagamento em cheque das obrigações com fornecedores
                case "025":
                    SQL = String.format("INSERT INTO RAZONETE(FORNECEDOR_DEBITO, BANCO_CREDITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Fornecedores", "Banco", Valor);
                    break;


                //Receita com prestação de serviços, a vista (deposito no banco), no valor
                case "026":
                    SQL = String.format("INSERT INTO RAZONETE(RECEITA_CREDITO, BANCO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Banco", "Receita", Valor);
                    break;

                    //Em 22/01/2022 - Receita de prestação de serviços no valor de R$ 14.000,00, recebido em dinheiro
                case "027":
                    SQL = String.format("INSERT INTO RAZONETE(RECEITA_CREDITO, CAIXA_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Caixa", "Receita", Valor);
                    break;

                //Pagamento de Despesas
                case "028":
                    SQL = String.format("INSERT INTO RAZONETE(DESPESAS, BANCO_CREDITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Ativos", "Despesas", Valor);
                    break;

                //Contabilizar juros ref. Aplicações financeiras 5% am
                case "029":
                    SQL = String.format("INSERT INTO RAZONETE(Receita_juros_Debito, BANCO_DEBITO) VALUES('%d', '%d')", Valor, Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Ativos", "Despesas", Valor);
                    break;

















            }

            ExecutarSQL = Conexao.getConexao().createStatement().executeUpdate(SQL);





        }


        finally {
            Conexao.FecharConexao();
        }



    }

}
