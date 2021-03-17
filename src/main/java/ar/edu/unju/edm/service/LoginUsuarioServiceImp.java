package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ar.edu.unju.edm.modelo.Usuario;
import ar.edu.unju.edm.repository.IUsuarioDAO;

public class LoginUsuarioServiceImp implements UserDetailsService{
	@Autowired
	IUsuarioDAO iUsuario;
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuarioEncontrado = iUsuario.findByNombreUsuario(nombreUsuario).orElseThrow(()-> new UsernameNotFoundException("Login Invalido"));
		List<GrantedAuthority> tipos= new ArrayList<>();
		GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(usuarioEncontrado.getTipo());
		tipos.add(grantedAuthority);
		UserDetails user=(UserDetails) new User(nombreUsuario, usuarioEncontrado.getPassword(),tipos);
		System.out.println("USUARIO:  "+usuarioEncontrado.getNombreusuario()+"CONTRASEÑA:  "+usuarioEncontrado.getPassword()+"  TIPO:  "+usuarioEncontrado.getTipo());
		return user;
	}

}
