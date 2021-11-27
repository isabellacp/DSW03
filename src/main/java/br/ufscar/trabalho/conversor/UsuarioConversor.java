package br.ufscar.trabalho.conversor;


import br.ufscar.trabalho.domain.Usuario;
import br.ufscar.trabalho.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UsuarioConversor implements Converter<String, Usuario>{

    @Autowired
    private IUsuarioService service;

    @Override
    public Usuario convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        int id = Integer.parseInt(text);
        return service.buscarPorId(id);
    }
}
