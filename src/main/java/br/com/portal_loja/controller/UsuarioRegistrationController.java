package br.com.portal_loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.portal_loja.controller.dto.UsuarioRegistrationDto;
import br.com.portal_loja.service.UsuarioService;

@Controller
@RequestMapping("/registration")
public class UsuarioRegistrationController {
	
	private UsuarioService usuarioService;
	
	public UsuarioRegistrationController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistrationDto usuarioRegistrationDto() {
		return new UsuarioRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
	//	model.addAttribute("usuario", new UsuarioRegistrationDto());
		return "registration";
	}

	@PostMapping
	public String registrationUsuario(@ModelAttribute("usuario") UsuarioRegistrationDto registrationDto) {
		this.usuarioService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
