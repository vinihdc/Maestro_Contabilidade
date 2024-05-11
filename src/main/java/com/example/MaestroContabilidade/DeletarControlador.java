package com.example.MaestroContabilidade;


import Model.BancoDeDados.DeletarFatosBD;
import Model.Entidade.DeletarFatosEntidade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.pattern.PathPattern;

import java.sql.SQLException;

@Controller
public class DeletarControlador {
    private DeletarFatosBD DeletarNoBD = new DeletarFatosBD();


    /*
    public String DeletarFato(@RequestParam int CodigoID, @RequestParam String LugarFato) {
        DeletarFatosEntidade Del = new DeletarFatosEntidade(CodigoID); //mesmo processo já visto até aqui
        DeletarNoBD.DeletarFatosTABELAFATOSGERAL(Del.getFatoID());
        if(LugarFato.equals("Caixa")) {
            DeletarNoBD.DeletarFatosTABELACAIXA(Del.getFatoID());
        }

        else if(LugarFato.equals("Financiamento")) {
            DeletarNoBD.DeletarFatosTABELAFINANCIAMENTO(Del.getFatoID());
        }

        else {
            DeletarNoBD.DeletarFatosTabelaInvestimento(Del.getFatoID());
        }

        return "DeletarFato";


    }


     */



}
