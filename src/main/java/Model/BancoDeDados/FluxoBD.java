package Model.BancoDeDados;

import org.apache.coyote.BadRequestException;

import java.sql.SQLException;

public class FluxoBD {
    private ConexaoBD Conexao = new ConexaoBD();

    private DepositoNoBancoBD ZerarCaixa = new  DepositoNoBancoBD();

    private FinanciamentoPagoBD PagarFinanciamento = new FinanciamentoPagoBD();

    private FornecedorPagoBD PagarFornecedor = new FornecedorPagoBD();

    private DiarioBD Diario = new DiarioBD();

    public void Fluxo(String CodFato, String Data, String Detalhes, int Valor) throws SQLException {
        String SQL = "1";
        String SQL2 = "2";
        String SQL3 = "3";

        //tudo que é Ativo -> Veiculo, Imovel, bens no geral está Detalhes, Detalhes no Diario desse jeito:  Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Caixa", Valor);


        try {
            Conexao.AbrirConexao();
            switch(CodFato) {
                //Ativo Compra
                case "001":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Caixa", Valor);
                    break;
                //Ativo Compra a prazo
                case "002":
                    SQL = String.format("INSERT INTO RAZONETE(FINANCIAMENTO_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Financiamento", Valor);
                    break;

                //Ativo 50% 50%
                case "003":
                    int Metade = Valor / 2;
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO) VALUES('%d')", Metade);
                    SQL2 = String.format("INSERT INTO RAZONETE(FINANCIAMENTO_CREDITO) VALUES('%d')", Metade);
                    SQL3 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Financiamento e Caixa", Valor);
                    break;

                //Investimento
                case "004":
                    SQL = String.format("INSERT INTO RAZONETE(INVESTIMENTO_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(CAIXA_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Caixa", "Investimento", Valor);
                    break;


                 //Emprestimo
                case "005":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_DEBITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(ATIVO_CREDITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Caixa", Detalhes, Valor);
                    break;


                //Pagamento de divida
                case "006":
                    SQL = String.format("INSERT INTO RAZONETE(CAIXA_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(DEBITO_FINANCIAMENTO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, "Financiamento", "Caixa", Valor);
                    break;

                // Pagamento com o cartão Banco 80 - Banco - 20% - Fornecedor
                case "007":
                    int PorcentagemBanco = (int) (Valor * 0.8);
                    int PorcentagemFornecedor =  (int) (Valor * 0.2);
                    SQL = String.format("INSERT INTO RAZONETE(BANCO_CREDITO) VALUES('%d')", PorcentagemBanco);
                    SQL2 = String.format("INSERT INTO RAZONETE(FORNECEDOR_CREDITO) VALUES('%d')", PorcentagemFornecedor);
                    SQL3 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Banco, Fornecedor", Valor);
                    break;

                //Compra a prazo fornecedor
                case "008":
                    SQL = String.format("INSERT INTO RAZONETE(FORNECEDOR_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Fornecedor", Valor);
                    break;

                //Deposito no banco
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

                //Pagamento Cartão do Banco
                case "012":
                    SQL = String.format("INSERT INTO RAZONETE(BANCO_CREDITO) VALUES('%d')", Valor);
                    SQL2 = String.format("INSERT INTO RAZONETE(ATIVO_DEBITO) VALUES('%d')", Valor);
                    Diario.InserirDadosDiario(CodFato, Data, Detalhes, Detalhes, "Banco", Valor);
                    break;





























            }

            if(SQL != "" && SQL2 != "") {
                int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
                int Executar2 = Conexao.getConexao().createStatement().executeUpdate(SQL2);
                int Executar3 = Conexao.getConexao().createStatement().executeUpdate(SQL3);
            }


        }


        finally {
            Conexao.FecharConexao();
        }



    }

}
