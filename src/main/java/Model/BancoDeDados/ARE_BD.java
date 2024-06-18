package Model.BancoDeDados;

import Model.Entidade.ARE_Entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ARE_BD implements ARE {




    private ConexaoBD Conexao = new ConexaoBD();

    @Override
    public void Descobrir_E_Salvar_ARE() {
        boolean DeuLucro = false;
        boolean DeuPrejuizo = false;
        int ReceitasServico = 0;
        int ReceitasJuros = 0;
        int Despesas = 0;
        int Resultado_ARE = 0;
        int Receitas = 0;
        int Lucro = 0;
        int Prejuizo = 0;
        int Custos = 0;
        String SQL2 = "";

        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT RECEITA_CREDITO, RECEITA_JUROS_CREDITO, DESPESAS, CUSTOS_CREDITO FROM RAZONETE";
            ResultSet DescobreARE = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(DescobreARE.next()) {

                ReceitasServico += Integer.parseInt(DescobreARE.getString("RECEITA_CREDITO"));
                ReceitasJuros += Integer.parseInt(DescobreARE.getString("RECEITA_JUROS_CREDITO"));
                Despesas += Integer.parseInt(DescobreARE.getString("DESPESAS"));
                Custos += Integer.parseInt(DescobreARE.getString("CUSTOS_CREDITO"));

            }

           Receitas = ReceitasJuros + ReceitasServico;

            if(Receitas > Despesas) {
                Resultado_ARE = Receitas - (Despesas + Custos);
                Lucro = Resultado_ARE;
                DeuLucro = true;
            }

            else {
                Resultado_ARE = Receitas - (Despesas + Custos);
                Prejuizo = Resultado_ARE;
                DeuPrejuizo = true;
            }




        }



        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Conexao.AbrirConexao();

            if(DeuLucro == true) {
                SQL2 = String.format("INSERT INTO RAZONETE(ARE_ZERADO, ARE_DESPESA, ARE_RECEITA, ARE_CUSTOS, Lucros_CREDITO) VALUES('%d', '%d', '%d', '%d', '%d')", Resultado_ARE, Despesas, Receitas, Custos, Lucro);
            }

            if(DeuPrejuizo == true) {
                SQL2 = String.format("INSERT INTO RAZONETE(ARE_ZERADO, ARE_DESPESA, ARE_RECEITA, ARE_CUSTOS, Lucros_DEBITO) VALUES('%d', '%d', '%d', '%d', '%d')", Resultado_ARE, Despesas, Receitas, Custos, Prejuizo);
            }


            int MandarAre = Conexao.getConexao().createStatement().executeUpdate(SQL2);

        }


        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     *
     */




    @Override
    public void Zerar_ARE() {
        try {
            Conexao.AbrirConexao();
            String SQL = "UPDATE RAZONETE SET DESPESAS = 0, RECEITA_JUROS_CREDITO = 0, RECEITA_CREDITO = 0, CUSTOS_CREDITO = 0";
            int Zerar_Tudo = Conexao.getConexao().createStatement().executeUpdate(SQL);
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    @Override
    public List<ARE_Entidade> Exibir_ARE() {
        List<ARE_Entidade> ARE = new ArrayList<>();
        int Receita = 0;
        int Lucro = 0;
        int Prejuizo = 0; //Lucro (Credito)
        int Despesas = 0;
        int Custos = 0;


        try {
            Conexao.AbrirConexao();
            String SQL = "SELECT ARE_RECEITA, ARE_DESPESA, ARE_CUSTOS, Lucros_Credito, Lucros_Debito FROM RAZONETE";
            ResultSet DescobreARE = Conexao.getConexao().createStatement().executeQuery(SQL);
            while(DescobreARE.next()) {

                Receita += Integer.parseInt(DescobreARE.getString("ARE_RECEITA"));
                Lucro += Integer.parseInt(DescobreARE.getString("Lucros_Debito"));
                Prejuizo += Integer.parseInt(DescobreARE.getString("Lucros_CREDITO"));
                Despesas += Integer.parseInt(DescobreARE.getString("ARE_DESPESA"));
                Custos += Integer.parseInt(DescobreARE.getString("ARE_CUSTOS"));

            }

            if(Lucro > Prejuizo) {
                ARE.add(new ARE_Entidade(Receita, Despesas, Lucro, "Lucro"));
            }

            else if(Lucro == Prejuizo) {
                ARE.add(new ARE_Entidade(Receita, Despesas, Lucro, "Neutro"));
            }

            else {
                ARE.add(new ARE_Entidade(Receita, Despesas, Lucro, "Lucro"));
            }





        }



        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ARE;
    }


}
