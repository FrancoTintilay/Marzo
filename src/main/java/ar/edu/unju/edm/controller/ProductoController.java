package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.modelo.Producto;

import ar.edu.unju.edm.service.IProductoService;


@Controller
public class ProductoController {
	@Autowired
	Producto unProducto;
	@Autowired
	private IProductoService iProductoService;
	@GetMapping("/listaproductos")
	public String listaproductos(Model model) {
		List<Producto>listaProductos=iProductoService.listarprod();
		model.addAttribute("listaproductos",listaProductos);
		return "productos";
	}
	@GetMapping("/productos")
	public String listaproductos1(Model model) {
		List<Producto>listaProductos=iProductoService.listarprod();
		model.addAttribute("listaproductos1",listaProductos);
		return "usuarioproducto";
	}
	@GetMapping("/editarproducto/{id}")
	public String editarproducto(Model model,@PathVariable Long id) {
		Producto unProducto=iProductoService.FindById(id);
		model.addAttribute("productonuevo",unProducto);
		return "editproducto";
	}
	@GetMapping("/altaproducto")
	public String agregarprod(Model model) {
		model.addAttribute("productonuevo",new Producto());
		return "agregarProducto";
	}
	@PostMapping("/guardarproducto")
	public String guardarProd(Model model, Producto productonuevo) {
		Producto producto= iProductoService.FindById(productonuevo.getId()!=null?productonuevo.getId():0);
		if(producto!=null) {
			productonuevo.setCodigo(producto.getCodigo());
			iProductoService.modProducto(productonuevo);
		}else {
			iProductoService.guardarProd(productonuevo);
		}
		return "redirect:/listaproductos";
	}
	@GetMapping("/eliminarproducto/{id}")
	public String eliminarproducto(Model model,@PathVariable Long id) {
		iProductoService.eliminarProducto(id);
		return "redirect:/listaproductos";
	}
}
