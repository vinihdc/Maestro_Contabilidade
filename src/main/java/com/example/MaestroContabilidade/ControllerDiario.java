package com.example.MaestroContabilidade;

import Model.BancoDeDados.DiarioBD;
import Model.Entidade.DiarioEntidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ControllerDiario {
    DiarioBD PegarDados = new DiarioBD();


    @GetMapping("/DadosDiario")
    public String ImprimirLivroDiario(Model model) throws SQLException {
        //Fatos.add(new FatosGeraisEntidade(id, Detalhes, valor, Data, TipoOp)); quando ele faz isso ele chama o construtor da classe classe FatosGeraisEntidade adicionando dentro do ArrayList um objeto que dá acesso a classe
        //List<DiarioEntidade> LivroDiario = PegarDados.SELECTDIARIO();
        //model.addAttribute("LivroDiario", LivroDiario);;
        return "Diario";
    }
}
