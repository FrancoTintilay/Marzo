package ar.edu.unju.edm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.modelo.Compra;
import ar.edu.unju.edm.modelo.Producto;
import ar.edu.unju.edm.modelo.ProductoParaVender;
import ar.edu.unju.edm.modelo.ProductoVendido;
import ar.edu.unju.edm.repository.ICompraDAO;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.repository.IProductoVendido;
import ar.edu.unju.edm.service.ICompraService;
import ar.edu.unju.edm.service.IProductoService;


@Controller
public class CompraController {
	@Autowired
	private IProductoService iProductoService;
	@Autowired
	private ICompraDAO iCompraDAO;
	@Autowired
	private IProductoDAO iProductoDAO;
	@Autowired
	private IProductoVendido iProductoVendido;

	@PostMapping(value = "/quitar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}
		return "redirect:/vender/";
	}

	private void limpiarCarrito(HttpServletRequest request) {
		this.guardarCarrito(new ArrayList<>(), request);
	}

	@GetMapping("/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarCarrito(request);
		redirectAttrs.addFlashAttribute("mensaje", "Venta cancelada").addFlashAttribute("clase", "info");
		return "redirect:/vender/";
	}

	@PostMapping("/terminar")
	public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	
		if (carrito == null || carrito.size() <= 0) {
			return "redirect:/vender/";
		}
		Compra v = iCompraDAO.save(new Compra());
		
		for (ProductoParaVender productoParaVender : carrito) {
			
			Producto p = iProductoService.FindById(productoParaVender.getId());
			
			if (p == null)
				continue; 
			
			p.restarExistencia(productoParaVender.getCantidad());

			
			iProductoDAO.save(p);
			
			ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(),
					productoParaVender.getPrecio(), productoParaVender.getNombre(), productoParaVender.getCodigo(), v);
			
			iProductoVendido.save(productoVendido);
		}

		
		this.limpiarCarrito(request);
		
		redirectAttrs.addFlashAttribute("mensaje", "Venta realizada correctamente").addFlashAttribute("clase",
				"success");
		return "redirect:/vender/";
	}

	@GetMapping("/vender")
	public String interfazVender(Model model, HttpServletRequest request) {
		model.addAttribute("producto", new Producto());
		float total = 0;
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		for (ProductoParaVender p : carrito)
			total += p.getTotal();
		model.addAttribute("total", total);
		return "vender";
	}

	private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		return carrito;
	}

	private void guardarCarrito(List<ProductoParaVender>carrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", carrito);
	}
	@PostMapping("/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		List<ProductoParaVender> carrito = this.obtenerCarrito(request);
		Producto productoBuscadoPorCodigo = iProductoDAO.findFirstByCodigo(producto.getCodigo());
		if (productoBuscadoPorCodigo == null) {
			redirectAttrs
					.addFlashAttribute("mensaje", "El producto con el cÃ³digo " + producto.getCodigo() + " no existe")
					.addFlashAttribute("clase", "warning");
			return "redirect:/vender/";
		}
		
		if (productoBuscadoPorCodigo.sinExistencia()) {
			redirectAttrs.addFlashAttribute("mensaje", "El producto estÃ¡ agotado").addFlashAttribute("clase",
					"warning");
			return "redirect:/vender/";
		}
		boolean encontrado = false;
		for (ProductoParaVender productoParaVenderActual : carrito) {
			if (productoParaVenderActual.getCodigo().equals(productoBuscadoPorCodigo.getCodigo())) {
				productoParaVenderActual.aumentarCantidad();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getNombre(),
					productoBuscadoPorCodigo.getCodigo(), productoBuscadoPorCodigo.getPrecio(),
					productoBuscadoPorCodigo.getDisponibilidad(), productoBuscadoPorCodigo.getId(), 1f));
		}
		this.guardarCarrito(carrito, request);
		return "redirect:/vender/";
	}
}
