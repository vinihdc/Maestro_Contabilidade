package com.example.MaestroContabilidade;

import Model.BancoDeDados.DiarioBD;
import Model.BancoDeDados.LivroDiarioBD;
import Model.Entidade.DiarioEntidade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerDiario {
    LivroDiarioBD PegarDados = new LivroDiarioBD();

    List<DiarioEntidade> LivroDiario = new ArrayList<>();

    @GetMapping("/DadosDiario")
    public String ImprimirLivroDiario(Model model) throws SQLException {
        LivroDiario.clear();
        LivroDiario = PegarDados.SELECTDIARIO();
        model.addAttribute("LivroDiario", LivroDiario);;
        return "Diario";












    }
}
