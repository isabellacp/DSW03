package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.dao.IAgenciaDAO;
import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.impl.AgenciaService;
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
@RequestMapping("/agencias")
public class AgenciaController {
    @Autowired
    private AgenciaService service;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/cadastrar")
    public String cadastrar(Agencia agencia) {

        return "agencias/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("agencias",service.buscarTodos());
        return "agencias/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attr) {
        agencia.setNome(agencia.getUsuario().getNome());
        if (result.hasErrors()) {
            return "agencias/cadastrar";
        }

        service.salvar(agencia);
        attr.addFlashAttribute("sucess", "agencia.create.sucess");
        return "redirect:/agencias/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("agencia", service.buscarPorId(id));
        return "agencias/cadastrar";
    }

    @PostMapping("/editar")
    public String editar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attr) {

        // Apenas rejeita se o problema não for com o CNPJ (CNPJ campo read-only)

        if (result.getFieldErrorCount() > 1 || result.getFieldError("cnpj") == null) {
            return "agencias/cadastrar";
        }

        service.salvar(agencia);
        attr.addFlashAttribute("sucess", "agencia.edit.sucess");
        return "redirect:/agencias/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id, ModelMap model) {
        if (!service.existe(id)) {
            model.addAttribute("fail", "agencia.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "agencia.delete.sucess");
        }
        return listar(model);
    }
    @ModelAttribute("usuarios")
    public List<Usuario> listaUsuarios() {
        return usuarioService.buscarTodos();
    }

}
