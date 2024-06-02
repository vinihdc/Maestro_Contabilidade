package Model.BancoDeDados;

import Model.Entidade.PedidosEntidade;
import Model.Verificação.VerificarCodigos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosBD implements Pedidos_BD {

    ConexaoBD Conexao = new ConexaoBD();


    /**
     *
     */
    @Override
    public void EnviarPedidosN1() {

        try {
            Conexao.AbrirConexao();
            String SQL = String.format("INSERT INTO REQS(Info) VALUES('%s')", "Pedido N1");
            int InserirStatus = Conexao.getConexao().createStatement().executeUpdate(SQL);
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
    public String ExistenciaDePedidosN2N3(String Nivel) throws SQLException {
        String Info = "";
        String Status = "";




        switch (Nivel) {
            case "N2":
                try {
                    Conexao.AbrirConexao();
                    String SQL = "SELECT Info FROM REQS ORDER BY id DESC LIMIT 1";
                    ResultSet StatusDosPedidos = Conexao.getConexao().createStatement().executeQuery(SQL);
                    while(StatusDosPedidos.next()) {
                        Info = StatusDosPedidos.getString("Info");
                    }

                    if(Info.equals("Pedido N1")) {
                        Status = "Pedido feito pelo N1";
                    }

                    else if(Info.equals("Verificado N2")) {
                        Status = "Voce já verificou o pedido esperando o N3";
                    }

                    else if(Info.equals("Verificado N3")) {
                        Status = "O N3 Verificou o pedido";
                    }

                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

            case "N3":
                Conexao.AbrirConexao();
                String SQL = "SELECT Info FROM REQS ORDER BY id DESC LIMIT 1";
                ResultSet StatusDosPedidos = Conexao.getConexao().createStatement().executeQuery(SQL);
                while(StatusDosPedidos.next()) {
                    Info = StatusDosPedidos.getString("Info");
                }


               if(Info.equals("Verificado N2")) {
                    Status = "N2 já verificou, verificar por gentileza";
                }

                else if(Info.equals("Verificado N3")) {
                    Status = "Voce já verificou";
                }

                else {
                    Status = "Sem pedidos por enquanto";
               }

                break;





        }


        return Status;
    }

    /**
     * @return
     */
    @Override
    public String StatusPedidosN1() {
        String Status = "";
        String Info = "";


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT Info FROM REQS ORDER BY id DESC LIMIT 1";
            ResultSet StatusDosPedidos = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(StatusDosPedidos.next()) {
                Info = StatusDosPedidos.getString("Info");
            }

            if(Info.equals("Pedido N1")) {
                Status = "Voce já fez o pedido, esperando N2";
            }

            else if(Info.equals("Verificado N2")) {
                Status = "O N2 já verificou e esperando o N3";
            }

            else if(Info.equals("Verificado N3")) {
                Status =  "Pedido já verificado pelo N3";
            }


        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Status;
    }

    /**
     * @param Nivel
     * @param ID
     */
    @Override
    public void RecusarPedidos(String Nivel, int ID) throws SQLException {


        String Situacao = "Recusado";
        int ID_Fatos = 0;
        String CodFato = "";
        String DataFato = "";
        String Detalhes = "";
        String Debito = "";
        String Credito = "";
        int Valor = 0;



        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT * FROM DIARIO WHERE ID = %d", ID);
            ResultSet PedidosRecusados = Conexao.getConexao().createStatement().executeQuery(SQL);
            while (PedidosRecusados.next()) {
                CodFato = PedidosRecusados.getString("COD_FATO");
                DataFato = PedidosRecusados.getString("DATA_FATO");
                Detalhes = PedidosRecusados.getString("DETALHES");
                Debito = PedidosRecusados.getString("DEBITO");
                Credito = PedidosRecusados.getString("CREDITO");
                Valor = Integer.parseInt(PedidosRecusados.getString("VALOR_FATO"));
            }


        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL2 = String.format("INSERT INTO STATUS_PEDIDO(ID_Diario, Nivel, Situacao, COD_FATO, DATA_FATO, DETALHES, DEBITO, CREDITO, VALOR_FATO) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", ID, Nivel,  Situacao, CodFato, DataFato, Detalhes, Debito, Credito, Valor);
            int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL2);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }



    }

    /**
     * @param Nivel
     * @param ID
     */
    @Override
    public void AprovarPedidos(String Nivel, int ID) throws SQLException {


        String Situacao = "Aprovado";
        String CodFato = "";
        String DataFato = "";
        String Detalhes = "";
        String Debito = "";
        String Credito = "";
        int Valor = 0;


        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT * FROM DIARIO WHERE ID = %d", ID);
            ResultSet PedidosRecusados = Conexao.getConexao().createStatement().executeQuery(SQL);
            while (PedidosRecusados.next()) {
                CodFato = PedidosRecusados.getString("COD_FATO");
                DataFato = PedidosRecusados.getString("DATA_FATO");
                Detalhes = PedidosRecusados.getString("DETALHES");
                Debito = PedidosRecusados.getString("DEBITO");
                Credito = PedidosRecusados.getString("CREDITO");
                Valor = Integer.parseInt(PedidosRecusados.getString("VALOR_FATO"));
            }


        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();
            String SQL2 = String.format("INSERT INTO STATUS_PEDIDO(ID_Diario, Nivel, Situacao, COD_FATO, DATA_FATO, DETALHES, CREDITO, DEBITO, VALOR_FATO) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", ID, Nivel, Situacao, CodFato, DataFato, Detalhes, Debito, Credito, Valor);
            int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL2);
            VerificarPedidosN2N3(Nivel);
        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            Conexao.FecharConexao();
        }

    }

    /**
     * @param Nivel
     * @return
     */
    @Override
    public void VerificarPedidosN2N3(String Nivel) {

        switch (Nivel) {
            case "N2":
                try {
                    Conexao.AbrirConexao();
                    String SQL = String.format("INSERT INTO REQS(Info) VALUES('%s')", "Verificado N2");
                    int Executar = Conexao.getConexao().createStatement().executeUpdate(SQL);
                }

                catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

            case "N3":
                try {
                Conexao.AbrirConexao();
                String SQL = String.format("INSERT INTO REQS(Info) VALUES('%s')", "Verificado N3");
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
    public List<PedidosEntidade> RelatorioGeralPedidos() throws SQLException {

        List<PedidosEntidade> Relatorio = new ArrayList<>();

        int CodigoUnico = 0;
        String Situacao = "";
        String Nivel = "";
        String CodFato = "";
        String DataFato = "";
        String Detalhes = "";
        String Debito = "";
        String Credito = "";
        String Info = "";
        int Valor = 0;


        try {
            Conexao.AbrirConexao();
            String SQL = String.format("SELECT * FROM STATUS_PEDIDO");
            ResultSet Pedidos = Conexao.getConexao().createStatement().executeQuery(SQL);
            while (Pedidos.next()) {
                CodigoUnico = Integer.parseInt(Pedidos.getString("ID_Diario"));
                Situacao = Pedidos.getString("Situacao");
                Nivel = Pedidos.getString("Nivel");
                CodFato = Pedidos.getString("COD_FATO");
                DataFato = Pedidos.getString("DATA_FATO");
                Detalhes = Pedidos.getString("DETALHES");
                Debito = Pedidos.getString("DEBITO");
                Credito = Pedidos.getString("CREDITO");
                Valor = Integer.parseInt(Pedidos.getString("VALOR_FATO"));
                Relatorio.add(new PedidosEntidade(CodigoUnico, Nivel, Situacao, CodFato, DataFato, Detalhes, Debito, Credito, Valor));


            }






        }



        finally {
            Conexao.FecharConexao();
        }

        return Relatorio;
    }


}
