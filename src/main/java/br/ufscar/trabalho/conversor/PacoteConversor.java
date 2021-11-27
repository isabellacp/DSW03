package br.ufscar.trabalho.conversor;


import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.service.spec.IPacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class PacoteConversor implements Converter<String, Pacote>{

    @Autowired
    private IPacoteService service;

    @Override
    public Pacote convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        int id = Integer.parseInt(text);
        return service.buscarPorId(id);
    }
}
