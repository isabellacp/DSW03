package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.security.UsuarioDetails;
import br.ufscar.trabalho.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/pacotes")
public class PacoteController {
    @Autowired
    private PacoteService service;
    @Autowired
    private AgenciaService agenciaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuario_service;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/cadastro")
    public String cadastrar(Pacote pacote) {
        return "pacotes/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("pacotes",service.buscarTodos());
        return "pacotes/listar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "pacotes/cadastro";
        }

        service.salvar(pacote);
        attr.addFlashAttribute("sucess", "pacote.create.sucess");
        return "redirect:/pacotes/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("pacote", service.buscarPorId(id));
        return "pacotes/cadastro";
    }
    @GetMapping("/comprar/{id}")
    public String comprar(@PathVariable("id") int id, @AuthenticationPrincipal UsuarioDetails user_details, ModelMap model) {
        Compra compra = new Compra();
        compra.setPacote(service.buscarPorId(id));
        compra.setCliente(clienteService.buscarPorUsuario(user_details.getUser()));
        compra.setData(java.time.LocalDate.now().toString());
        compraService.salvar(compra);
        return "redirect:/pacotes/listar";
    }

    @PostMapping("/editar")
    public String editar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {
        service.salvar(pacote);
        attr.addFlashAttribute("sucess", "pacote.edit.sucess");
        return "redirect:/pacotes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id, ModelMap model) {
        if (!service.existe(id)) {
            model.addAttribute("fail", "pacote.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "pacote.delete.sucess");
        }
        return listar(model);
    }
    @ModelAttribute("agencias")
    public List<Agencia> listaAgencias() {
        return agenciaService.buscarTodos();
    }
}
