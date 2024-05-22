package Model.BancoDeDados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PedidosBD implements Pedidos_BD {

    ConexaoBD Conexao = new ConexaoBD();


    /**
     *
     */
    @Override
    public void EnviarPedido() {
        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO STATUS_PEDIDO(N1, N2, N3) VALUES('%s', '%s', '%s')", "Pedido", "Analise", "Analise");
            int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);

        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param Nivel
     * @return
     */
    @Override
    public String ExistePedidosParaN2N3(String Nivel) {
        String Info = "";
        String Status = "";

        switch(Nivel) {
            case "N2":

                try {
                    Conexao.AbrirConexao();
                    String SQL = "SELECT N1 FROM STATUS_PEDIDO ORDER BY id DESC LIMIT 1;";
                    ResultSet Status_Pedido = Conexao.getConexao().createStatement().executeQuery(SQL);
                    while(Status_Pedido.next()) {
                        Info = Status_Pedido.getString("N2");
                    }

                    if(Info.equals("Pedido")) {
                        Status = "Pedido requerido pelo N1";
                    }



                    else {
                        Status = "Sem pedidos"; //Não existe pedidos logo foram aprovados
                    }

                }


                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;


            case "N3":
                try {
                    Conexao.AbrirConexao();
                    String SQL = "SELECT N1 FROM STATUS_PEDIDO ORDER BY id DESC LIMIT 1;";
                    ResultSet Status_Pedido = Conexao.getConexao().createStatement().executeQuery(SQL);
                    while(Status_Pedido.next()) {
                        Info = Status_Pedido.getString("N1");
                    }

                    if(Info.equals("Pedido")) {
                        Status = "Pedido requerido pelo N1";
                    }

                    else {
                        Status = "Sem pedidos"; //Não existe pedidos logo foram aprovados
                    }

                }


                catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                break;





        }


        return Status;
    }


    @Override
    public void AprovarPedidos(String Nivel) {
        switch (Nivel) {
            case "N2":
                try {
                    Conexao.AbrirConexao();
                    String SQL = String.format("INSERT INTO STATUS_PEDIDO(N2) VALUES('%s')", "Aprovado");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);

                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            case "N3":
                try {
                    Conexao.AbrirConexao();
                    String SQL = String.format("INSERT INTO STATUS_PEDIDO(NIVEL3) VALUES('%s')", "Aprovado");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);

                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    /**
     * @param Nivel
     * @return
     */
    @Override
    public void RecusarPedidos(String Nivel) {
        switch (Nivel) {
            case "N2":
                try {
                    Conexao.AbrirConexao();
                    String SQL = String.format("INSERT INTO STATUS_PEDIDO(N2) VALUES('%s')", "Recusado");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);

                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            case "N3":
                try {
                    Conexao.AbrirConexao();
                    String SQL = String.format("INSERT INTO STATUS_PEDIDO(NIVEL3) VALUES('%s')", "Recusado");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);

                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }

    }

    /**
     * @return
     */
    @Override
    public String StatusPedidosN1() {
        String Info = "";
        String Status = "";
        String Info2 = "";

                try {
                    Conexao.AbrirConexao();
                    String SQL = "SELECT N2, NIVEL3 FROM STATUS_PEDIDO ORDER BY id DESC LIMIT 1;";
                    ResultSet Status_Pedido = Conexao.getConexao().createStatement().executeQuery(SQL);
                    while(Status_Pedido.next()) {
                        Info = Status_Pedido.getString("N2");
                        Info2 = Status_Pedido.getString("NIVEL3");
                    }

                    if(Info.equals("Analise")) {
                        Status = "Pedido em Analise pelo N2";
                    }

                    else if(Info.equals("Recusado")) {
                        Status = "Pedido Recusado";
                    }


                    else if(Info.equals("NULO")) {
                        Status = "Sem Pedidos ou em analise";
                    }


                    else {
                        Status = "Aprovado pelo N2, esperando aprovação do N3";
                    }

                    if(Info2.equals("Aprovado")) {
                        Status = "Pedido Aprovado";
                    }


                    if(Info2.equals("Recusado")) {
                        Status = "Pedido Recusado";
                    }



                }


                catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                return Status;


    }
}