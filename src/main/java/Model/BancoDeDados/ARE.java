package Model.BancoDeDados;

import Model.Entidade.ARE_Entidade;

import java.util.List;

public interface ARE {
    public ConexaoBD Conexao = new ConexaoBD();



    public void Descobrir_E_Salvar_ARE(); //Select e Insert ResultadoAre

    public void Zerar_ARE(); //Update

    public List<ARE_Entidade> Exibir_ARE();

    /*
    SELECT RECEITA, DESPESA

    int ARE = Receita - Despesa




     */



}
