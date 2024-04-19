package com.example.MaestroContabilidade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class FatoController {


    @RequestMapping("/Fato")
    public String Fato(@RequestParam String OperacaoFato, @RequestParam int FatoValor, @RequestParam String DetalhesFato, @RequestParam String DataFato) throws SQLException {






        return "Fatos";
    }

}
