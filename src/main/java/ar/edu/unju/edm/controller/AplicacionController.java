package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.modelo.Producto;
import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.service.IProductoService;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class AplicacionController {
	@Autowired
	IUsuarioService iUsuarioService;
	@Autowired
	IProductoService iProductoService;
	@GetMapping({"/","/login","/home","/login?error=true"})
	public String IniciarAplicacion(Model model) {
		
		// Agregar usuarios y productos de ejemplo
		iProductoService.guardarProd(new Producto((long)1,"Fideos", "1",Float.parseFloat("100.0"),Float.parseFloat("200.5")));
		iProductoService.guardarProd(new Producto((long)2,"Pizza", "2",Float.parseFloat("200.0"),Float.parseFloat("1500.5")));
		iProductoService.guardarProd(new Producto((long)3,"Pan", "3",Float.parseFloat("90.0"),Float.parseFloat("1000.5")));
		
		
		Usuario bas=new Usuario((long)1,"userAdmin","1234","43036712","userAdmin","userAdmin","Admin");
		iUsuarioService.crear(bas);
		bas=new Usuario((long)2,"userClient","1234","11111111","userClient","userClient","Client");
		iUsuarioService.crear(bas);
		//*/
		return "index";
	}
}
