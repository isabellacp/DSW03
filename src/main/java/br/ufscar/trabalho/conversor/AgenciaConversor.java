package br.ufscar.trabalho.conversor;


import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.service.spec.IAgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AgenciaConversor implements Converter<String, Agencia>{

    @Autowired
    private IAgenciaService service;

    @Override
    public Agencia convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        int id = Integer.parseInt(text);
        return service.buscarPorId(id);
    }
}
