package Model.BancoDeDados;

import Model.Entidade.DiarioEntidade;
import Model.Entidade.PedidosEntidade;

import java.sql.SQLException;
import java.util.List;

public interface Pedidos_BD {


        public void EnviarPedidosN1();

        public String ExistenciaDePedidosN2N3(String Nivel) throws SQLException;

        public String StatusPedidosN1();

        public void RecusarPedidos(String Nivel, int ID) throws SQLException;

        public void AprovarPedidos(String Nivel, int ID) throws SQLException;

        public void VerificarPedidosN2N3(String Nivel);

        public List<PedidosEntidade> RelatorioGeralPedidos() throws SQLException;


}
