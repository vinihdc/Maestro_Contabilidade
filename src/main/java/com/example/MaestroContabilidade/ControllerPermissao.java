package com.example.MaestroContabilidade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerPermissao {
    @GetMapping("/Permitir")
    public String Permitir() {
        return "";
    }
}
