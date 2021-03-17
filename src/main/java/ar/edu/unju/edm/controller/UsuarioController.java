package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	Usuario unUsuario;
	@Autowired
	private IUsuarioService iUsuarioService;
	@GetMapping("/Client")
	public String InterfazClient() {
		return "InterfazCliente";
	}
	@GetMapping("/Admin")
	public String Admin(Model model) {
		return "InterfazAdmin";
	}
	
	@GetMapping("/listausuarios")
	public String listarusuarios(Model model) {
		List<Usuario>listaUsuarios = iUsuarioService.listarUsu();
		model.addAttribute("listausuarios",listaUsuarios);
		return "usuarios";
	}
	@GetMapping("/editarusuario/{id}")
	public String editarusu(Model model, @PathVariable Long id) {
	Usuario usuario= iUsuarioService.FindById(id);
	model.addAttribute("usuarionuevo",usuario);
	return "edituser";
	}
	@GetMapping("/altausuario")
	public String agregarusu(Model model) {
		model.addAttribute("usuarionuevo",new Usuario());
		return "agregarUsuario";
	}
	@PostMapping("/guardarusuario")
	public String guardarUsu(Model model, Usuario usuarionuevo) {
		Usuario usuario = iUsuarioService.FindById(usuarionuevo.getId()!=null?usuarionuevo.getId():0);
		if(usuario!=null) {
			
			usuarionuevo.setPassword(usuario.getPassword());
			iUsuarioService.modUsuario(usuarionuevo);
		}else {
			iUsuarioService.guardarUsu(usuarionuevo);
			iUsuarioService.crear(usuarionuevo);
		}
		return "redirect:/listausuarios";
	}
	@GetMapping("/eliminar/{id}")
	public String eliminarusu(Model model, @PathVariable Long id) {
		iUsuarioService.eliminarUsuario(id);
		return "redirect:/listausuarios";
	}

}
