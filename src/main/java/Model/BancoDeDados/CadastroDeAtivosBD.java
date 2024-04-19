package Model.BancoDeDados;

import java.sql.SQLException;

public class CadastroDeAtivosBD {

    public boolean isCaixaZerado() {
        return CaixaZerado;
    }

    private boolean CaixaZerado;
    Model.BancoDeDados.ConexaoBD Conexao = new ConexaoBD();
    public void AtivoDebito(String OperacaoFato, int FatoValor, String DetalhesFato, String DataFato) throws SQLException {



        try {
            Conexao.AbrirConexao();
            String ComandoSQL = String.format("INSERT INTO FATOSGERAL(Operacao_FATO, VALOR_FATO, DETALHES_FATO, FORMADEPAGAMENTO, DATA_FATO) VALUES('%s', '%d', '%s', 'Debito', '%s')", OperacaoFato, FatoValor, DetalhesFato, DataFato);
            int NumDeLinhas = Conexao.getConexao().createStatement().executeUpdate(ComandoSQL);
            System.out.println("Informacoes cadastradas com sucesso!");

        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public void AtivoCredito(String OperacaoFato, int FatoValor, String DetalhesFato, String DataFato) throws SQLException {



        try {
            Conexao.AbrirConexao();
            String ComandoSQL = String.format("INSERT INTO FATOSGERAL(Operacao_FATO, VALOR_FATO, DETALHES_FATO, FORMADEPAGAMENTO, DATA_FATO) VALUES('%s', '%d', '%s', 'Credito', '%s')", OperacaoFato, FatoValor, DetalhesFato, DataFato);
            int NumDeLinhas = Conexao.getConexao().createStatement().executeUpdate(ComandoSQL);
            System.out.println("Informacoes cadastradas com sucesso!");

        }

        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
