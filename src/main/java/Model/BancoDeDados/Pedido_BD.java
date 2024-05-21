package Model.BancoDeDados;

import java.sql.SQLException;

public interface Pedido_BD {

    public boolean InformarPedido(String Nivel) throws SQLException;

    public void Inserir_Status_Pedido(String Nivel) throws SQLException;

    public boolean Consultar_Status_Pedido(String Nivel);

    public void RecusarPedido();

}
