package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Usuario usuario) {
        return "usuario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("usuario",service.buscarTodos());
        return "usuario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "usuario/cadastro";
        }

        service.salvar(usuario);
        attr.addFlashAttribute("sucess", "usuario.create.sucess");
        return "redirect:/usuario/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("usuario", service.buscarPorId(id));
        return "usuario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        service.salvar(usuario);
        attr.addFlashAttribute("sucess", "usuario.edit.sucess");
        return "redirect:/usuario/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id, ModelMap model) {
        if (!service.existe(id)) {
            model.addAttribute("fail", "usuario.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "usuario.delete.sucess");
        }
        return listar(model);
    }
}
