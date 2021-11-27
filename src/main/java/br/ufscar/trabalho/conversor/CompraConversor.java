package br.ufscar.trabalho.conversor;


import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.service.spec.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class CompraConversor implements Converter<String, Compra>{

    @Autowired
    private ICompraService service;

    @Override
    public Compra convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        int id = Integer.parseInt(text);
        return service.buscarPorId(id);
    }
}
