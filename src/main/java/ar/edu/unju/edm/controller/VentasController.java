package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.repository.ICompraDAO;
import ar.edu.unju.edm.service.ICompraService;
@Controller
public class VentasController {
	@Autowired
    ICompraService iCompraService;
	@Autowired
	ICompraDAO iCompraDAO;

    @GetMapping("/ventas")
    public String mostrarVentas(Model model) {
        model.addAttribute("ventas", iCompraDAO.findAll());  
        return "ventas";
    }
}
