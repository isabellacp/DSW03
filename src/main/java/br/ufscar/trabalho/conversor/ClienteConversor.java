package br.ufscar.trabalho.conversor;


import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.service.spec.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ClienteConversor implements Converter<String, Cliente>{

    @Autowired
    private IClienteService service;

    @Override
    public Cliente convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        int id = Integer.parseInt(text);
        return service.buscarPorId(id);
    }
}
