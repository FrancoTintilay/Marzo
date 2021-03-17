package ar.edu.unju.edm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AplicacionController {

	@GetMapping({"/","/login","/home","/login?error=true"})
	public String IniciarAplicacion(Model model) {
		return "index";
	}
}
