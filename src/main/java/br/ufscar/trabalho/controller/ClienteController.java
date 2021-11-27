package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.impl.ClienteService;
import br.ufscar.trabalho.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente) {

        return "clientes/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("clientes",service.buscarTodos());
        return "clientes/listar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "clientes/cadastro";
        }

        service.salvar(cliente);
        attr.addFlashAttribute("sucess", "cliente.create.sucess");
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("cliente", service.buscarPorId(id));
        return "clientes/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        service.salvar(cliente);
        attr.addFlashAttribute("sucess", "cliente.edit.sucess");
        return "redirect:/clientes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id, ModelMap model) {
        if (!service.existe(id)) {
            model.addAttribute("fail", "cliente.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "cliente.delete.sucess");
        }
        return listar(model);
    }
    @ModelAttribute("usuarios")
    public List<Usuario> listaUsuarios() {
        return usuarioService.buscarTodos();
    }

}
