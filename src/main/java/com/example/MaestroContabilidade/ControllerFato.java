package com.example.MaestroContabilidade;

import Model.Entidade.FatosEntidade;
import Model.Verificação.VerificarCodigos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.Locale;

@Controller
public class ControllerFato {

    private VerificarCodigos ObjetoVerificarCodigos = new VerificarCodigos();


    @RequestMapping("/Fato")
    public String Fato(@RequestParam String CodFato, @RequestParam int FatoValor, @RequestParam String DetalhesFato, @RequestParam String DataFato) throws SQLException {
        boolean Validar;
        Validar = ObjetoVerificarCodigos.VerificarCodigos(CodFato);
        if(Validar == true) {
            FatosEntidade Fatos = new FatosEntidade(CodFato, FatoValor, DetalhesFato, DataFato);
            Fatos.DefinirCaminho();
            return "Fatos";
        }

        else {
            return "InfoIncorreta";
        }

    }



}
