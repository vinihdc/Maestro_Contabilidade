package com.example.MaestroContabilidade;


import Model.BancoDeDados.IA_BD;
import Model.Entidade.IA_Entidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller

public class ControllerIA {

    IA_BD ObjetoIA = new IA_BD();

    @GetMapping("/Gepeto")
    public String GerarRespostas(@RequestParam String PerguntaIA, Model model) throws SQLException {
        boolean ExisteResposta;
        ExisteResposta = ObjetoIA.ExisteRespostaIA(PerguntaIA);


        if(ExisteResposta == true) {
            List<IA_Entidade> RespostasIA = ObjetoIA.GerarRespostaIA(PerguntaIA);
            model.addAttribute("IA", RespostasIA);;
            return "IA";

        }

        else {
            return "PaginaErroNoSistema";
        }


    }

}
