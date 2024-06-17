package Model.Entidade;

import Model.BancoDeDados.*;
import lombok.Getter;

import java.sql.SQLException;

public class FatosEntidade {



        private FluxoBD DefinirFluxo = new FluxoBD();

        private DiarioBD CadastrarDadosDiario = new DiarioBD();




        private String CodFato;

        private int ValorFato;

        private String DetalhesFato;

        private String DataFato;



    public FatosEntidade(String codFato, int valorFato, String detalhesFato, String dataFato) {
        CodFato = codFato;
        ValorFato = valorFato;
        DetalhesFato = detalhesFato;
        DataFato = dataFato;
    }






    public void DefinirCaminho() throws SQLException {


        DefinirFluxo.Fluxo(CodFato, DataFato, DetalhesFato, ValorFato);




        //INSERÇÃO DE COISAS MANUAIS


        /*

        //013 - Colocar dinheiro no Debito do Caixa
        if(CodFato.equals("013")) {
           CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Caixa", "-", ValorFato);
        }


        //014 - Colocar dinheiro no debito do banco
        if(CodFato.equals("014")) {
            CadastroBanco.BancoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Banco", "-", ValorFato);

        }

        //015 - Aplicacoes - Colocar no Debito

        if(CodFato.equals("015")) {
            CadastrarAplicacoes.RegistroAplicacoesDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Aplicacoes", "-", ValorFato);
        }



        //016 - Clientes
        if(CodFato.equals("016")) {
            CadastrarDinheiroClientes.RegistroClientesDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Clientes", "-", ValorFato);
        }


        //017



        if(CodFato.equals("017")) {
            CadastrarDinheiroClientes.RegistroClientesDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Clientes", "-", ValorFato);
        }






        //016 - Inserir de forma manual Fornecedores
        if(CodFato.equals("017")) {
            CadastroFornecedor.FornecedorCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Fornecedores", ValorFato);

        }


        //020 - Fornecedores a longo prazo
        if(CodFato.equals("018")) {
           FornecedorLG.RegistroAplicacoesCredito(CodFato, ValorFato, DetalhesFato, DataFato);
           CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Fornecedores", ValorFato);

        }



        //inserção impostos
        if(CodFato.equals("019")) {
           CadastroImpostos.RegistroImpostosCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Impostos", ValorFato);


        }

        //insercao de contas
        if(CodFato.equals("020")) {
            CadastroAtivo.AtivoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Contas", ValorFato);


        }


        //Lucro
        if(CodFato.equals("021")) {
            CadastroLucros.RegistroLucroCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "-", "Lucro", ValorFato);



        }




























        //Janeiro Ex

        //Em 01/01/2022 - Efetuar pagamento em cheque (BANCO) das obrigações com fornecedores
        //no valor de R$ 5,000,00

        if(CodFato.equals("022")) {
            CadastroBanco.BancoCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroFornecedor.FornecedorDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Fornecedores", "Banco", ValorFato);
        }


        //Em 08/01/2022 - Receita com prestação de serviços, a vista (deposito no banco), no valor de R$ 55.000,00

        if(CodFato.equals("023")) {
            CadastrarReceita.ReceitaCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroBanco.BancoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Banco", "Receita de prestação de serviços", ValorFato);

        }

        //Em 22/01/2022 - Receita de prestação de serviços no valor de R$ 14.000,00, recebido em dinheiro

        if(CodFato.equals("024")) {
            CadastroCaixa.RegistroNoCaixaDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarReceita.ReceitaCredito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Caixa", "Receita de prestação de serviços", ValorFato);


        }


        Em 27/01/2022 - Pagamentos de despesas, em cheque, como segue:

        1 - Agua - R$ 105,00

         2 - Luz - R$ 120,00

        3 - Despesas de viagem - R$ 2.000,00



        if(CodFato.equals("025")) {
            CadastrarDespesas.RegistroDespesasDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastroBanco.BancoDebito(CodFato, ValorFato, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Despesas", "Banco", ValorFato);


        }

        //Em 27/01/2022 - Contabilizar juros ref. Aplicações financeiras 5% am

        if(CodFato.equals("026")) {
            int Valor = (int) (ValorFato * 0.05);
            CadastrarAplicacoes.RegistroAplicacoesDebito(CodFato, Valor, DetalhesFato, DataFato);
            CadastrarDadosDiario.InserirDadosDiario(CodFato, DataFato, DetalhesFato, "Debito", "-", ValorFato);

        }






  */

































    }







}
