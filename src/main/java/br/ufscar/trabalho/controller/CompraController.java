package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.security.UsuarioDetails;
import br.ufscar.trabalho.service.impl.ClienteService;
import br.ufscar.trabalho.service.impl.CompraService;
import br.ufscar.trabalho.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService service;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/listar/cliente")
    public String listar_do_cliente(ModelMap model,  @AuthenticationPrincipal UsuarioDetails user_details) {
        Cliente cliente = clienteService.buscarPorUsuario(user_details.getUser());
        model.addAttribute("compras",service.buscarPorCliente(cliente));
        return "compras/listar";
    }

}
