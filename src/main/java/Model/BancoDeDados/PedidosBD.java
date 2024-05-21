package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PedidosBD implements Pedido_BD {

    ConexaoBD Conexao = new ConexaoBD();
    /**
     * @return
     */
    @Override
    public boolean InformarPedido(String Nivel) throws SQLException {
        int Dados = 0;
        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT * FROM DIARIO";
            ResultSet PercorreDiario = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(PercorreDiario.next()) {
                 Dados = Integer.parseInt(PercorreDiario.getString("Valor_Fato"));
            }



            if(Dados != 0) {
                if(Objects.equals(Nivel, "N3")) {
                    SQL = String.format("INSERT INTO STATUS_PEDIDO(N3) VALUES('%s')", "Analise");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
                }

                else {
                    SQL = String.format("INSERT INTO STATUS_PEDIDO(N2) VALUES('%s')", "Analise");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
                }

                return true;
            }

            else {
                return false;
            }
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public void Inserir_Status_Pedido(String Nivel) throws SQLException {
        if(Nivel.equals("N3")) {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO STATUS_PEDIDO(N3) VALUES('%s')", "Aprovado");
            int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
        }

        else {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO STATUS_PEDIDO(N2) VALUES('%s')", "Aprovado");
            int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
        }

    }

    /**
     * @param Nivel
     * @return
     */
    @Override
    public boolean Consultar_Status_Pedido(String Nivel) {
        String Status_Pedido = "";


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT N3 FROM STATUS_PEDIDO ORDER BY id DESC LIMIT 1;";
            ResultSet Status = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(Status.next()) {
                Status_Pedido = Status.getString("N3");
            }

            if(Objects.equals(Status_Pedido, "Aprovado")) {
                return true;
            }

            else {
                return false;
            }
        }


        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     *
     */
    @Override
    public void RecusarPedido() {
        try {
            Conexao.AbrirConexao();
            String SQL = "TRUNCATE DIARIO";
            String SQL2 = "TRUNCATE RAZONETE";
            int Recusar = Conexao.getConexao().createStatement().executeUpdate(SQL);
            int Recusar2 = Conexao.getConexao().createStatement().executeUpdate(SQL2);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
