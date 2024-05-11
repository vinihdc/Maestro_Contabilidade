package com.example.MaestroContabilidade;

import Model.BancoDeDados.BalanceteBD;
import Model.Entidade.BalanceteEntidade;
import Model.Entidade.FatosEntidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Controller
public class FatoController {




    @RequestMapping("/Fato")
    public String Fato(@RequestParam String CodFato, @RequestParam int FatoValor, @RequestParam String DetalhesFato, @RequestParam String DataFato) throws SQLException {
        Locale.setDefault(Locale.US);
        FatosEntidade Fatos = new FatosEntidade(CodFato, FatoValor, DetalhesFato, DataFato);
        Fatos.DefinirCaminho();

        return "Fatos";
    }



}
