package Model.BancoDeDados;

import Model.Entidade.BalanceteEntidade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceteBD extends RazoneteBD {

    private ConexaoBD conexao = new ConexaoBD();
    private List<BalanceteEntidade> balanceteArray = new ArrayList<>();

    public List<BalanceteEntidade> SELECTBalancete() {
        try {

            conexao.AbrirConexao();
            String sql = "SELECT * FROM RAZONETE";
            Statement stmt = conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int numColumns = metaData.getColumnCount();

            // Mapas para manter saldo de crédito e débito por elemento
            Map<String, Integer> saldoCredito = new HashMap<>();
            Map<String, Integer> saldoDebito = new HashMap<>();

            // Iterar sobre os resultados
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    String coluna = metaData.getColumnName(i);
                    int valor = rs.getInt(coluna);

                    if (!coluna.toUpperCase().endsWith("_CREDITO") && !coluna.toUpperCase().endsWith("_DEBITO")) {
                        continue; // Pular colunas que não são do tipo esperado
                    }

                    String elemento = coluna.substring(0, coluna.lastIndexOf("_"));
                    saldoCredito.putIfAbsent(elemento, 0);
                    saldoDebito.putIfAbsent(elemento, 0);

                    if (coluna.toUpperCase().endsWith("_CREDITO")) {
                        saldoCredito.put(elemento, saldoCredito.get(elemento) + valor);
                    } else if (coluna.toUpperCase().endsWith("_DEBITO")) {
                        saldoDebito.put(elemento, saldoDebito.get(elemento) + valor);
                    }
                }
            }

            // Criar objetos BalanceteEntidade e adicionar ao ArrayList
            for (String elemento : saldoCredito.keySet()) {
                int credito = saldoCredito.get(elemento);
                int debito = saldoDebito.get(elemento);
                int saldo = credito - debito;

                balanceteArray.add(new BalanceteEntidade(elemento, debito, credito, saldo));
            }

            // Calcular saldo final
            int totalCreditoFinal = saldoCredito.values().stream().mapToInt(Integer::intValue).sum();
            int totalDebitoFinal = saldoDebito.values().stream().mapToInt(Integer::intValue).sum();
            int saldoFinal = totalCreditoFinal - totalDebitoFinal;

            // Adicionar objeto de saldo final ao ArrayList
            balanceteArray.add(new BalanceteEntidade("Saldo Final", totalCreditoFinal, totalDebitoFinal, saldoFinal));

            conexao.FecharConexao();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balanceteArray;
    }
}
