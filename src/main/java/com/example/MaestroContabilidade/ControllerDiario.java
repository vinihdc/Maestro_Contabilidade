package com.example.MaestroContabilidade;

import Model.BancoDeDados.ImprimirDadosDiarioBD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import Model.Entidade.FatosEntidade;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ControllerDiario {
    ImprimirDadosDiarioBD PegarDados = new ImprimirDadosDiarioBD();


    @GetMapping("/DadosDiario")
    public String ImprimirLivroDiario(Model model) throws SQLException {
        //Basicamente ele coloca o retorno do ArrayList declaro localmente dentro de outro arrayList, associando o retorno a outra Lista, ou seja é semelheante a fazer isso
        //if(x == CompararNumeros())
        //Fatos.add(new FatosGeraisEntidade(id, Detalhes, valor, Data, TipoOp)); quando ele faz isso ele chama o construtor da classe classe FatosGeraisEntidade adicionando dentro do ArrayList um objeto que dá acesso a classe
        List<FatosEntidade> Fato = PegarDados.SELECTDIARIO();
        model.addAttribute("Fatos", Fato);;
        return "Diario";
    }
}
