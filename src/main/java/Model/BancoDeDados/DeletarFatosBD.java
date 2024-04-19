package Model.BancoDeDados;

import java.sql.SQLException;

public class DeletarFatosBD {
        private ConexaoBD Conexao = new ConexaoBD();

        public void DeletarFatosTABELAFATOSGERAL(int CodigoID) {
            try {
                Conexao.AbrirConexao();
                String sql = String.format("DELETE FROM FATOSGERAL WHERE ID = '%d'", CodigoID);
                int UpdateLinha = Conexao.getConexao().createStatement().executeUpdate(sql);
                System.out.println("Deu");
            }

            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void DeletarFatosTABELACAIXA(int CodigoID) {
            try {
                Conexao.AbrirConexao();
                String sql = String.format("DELETE FROM CAIXA WHERE ID = '%d'", CodigoID);
                int UpdateLinha = Conexao.getConexao().createStatement().executeUpdate(sql);
                System.out.println("Deu");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void DeletarFatosTABELAFINANCIAMENTO(int CodigoID) {
            try {
                Conexao.AbrirConexao();
                String sql = String.format("DELETE FROM FINANCIAMENTO WHERE ID = '%d'", CodigoID);
                int UpdateLinha = Conexao.getConexao().createStatement().executeUpdate(sql);
                System.out.println("Deu");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void DeletarFatosTabelaInvestimento(int CodigoID) {
            try {
                Conexao.AbrirConexao();
                String sql = String.format("DELETE FROM INVESTIMENTO WHERE ID = '%d'", CodigoID);
                int UpdateLinha = Conexao.getConexao().createStatement().executeUpdate(sql);
                System.out.println("Deu");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
