package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.domain.Compra;
import br.ufscar.trabalho.domain.Pacote;
import br.ufscar.trabalho.service.spec.IAgenciaService;
import br.ufscar.trabalho.service.spec.ICompraService;
import br.ufscar.trabalho.service.spec.IPacoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@RestController
public class PacoteRestController {

    @Autowired
    private IPacoteService service;
    @Autowired
    private IAgenciaService agencia_service;
    @Autowired
    private ICompraService compra_service;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(Pacote pacote, JSONObject json) {

        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                pacote.setId((Integer) id);
            }
            if (id instanceof String) {

                pacote.setId(Integer.parseInt((String) id));
            }
        }
        Object agencia_id = json.get("agencia_id");
        if (id != null) {
            if (id instanceof Integer) {
                pacote.setAgencia(agencia_service.buscarPorId((Integer) agencia_id));
                pacote.setId((Integer) id);
            }
        }
        pacote.setDestino((String) json.get("destino"));
        pacote.setDuracao((Integer) json.get("duracao"));
        pacote.setValor((Integer) json.get("valor"));
        pacote.setPartida((String)json.get("partida"));

    }

    @GetMapping(path = "/pacotes")
    public ResponseEntity<List<Pacote>> lista() {
        List<Pacote> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/pacotes/agencias/{id}")
    public ResponseEntity<List<Pacote>> lista_da_agencia(@PathVariable("id") int id) {
        Agencia agencia = agencia_service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        }
        List<Pacote> lista = service.buscarPorAgencia(agencia);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/pacotes/cliente/{id}")
    public ResponseEntity<List<Compra>> lista_de_compras(@PathVariable("id") int id) {
        List<Compra> compras = compra_service.buscarPorCliente(id);
        if (compras == null || compras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(compras);
    }
    @GetMapping(path = "/pacotes/{id}")
    public ResponseEntity<Pacote> lista(@PathVariable("id") int id) {
        Pacote pacote = service.buscarPorId(id);
        if (pacote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pacote);
    }
    @GetMapping(path = "/pacotes/{destino}")
    public ResponseEntity<List<Pacote>> lista_com_destino(@PathVariable("destino") String destino) {
        List<Pacote> pacotes = service.buscarPorDestino(destino);
        if (pacotes == null || pacotes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pacotes);
    }

    @PostMapping(path = "/pacotes")
    @ResponseBody
    public ResponseEntity<Pacote> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Pacote pacote = new Pacote();
                parse(pacote, json);
                service.salvar(pacote);
                return ResponseEntity.ok(pacote);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
    @PostMapping(path = "/pacotes/agencias/{id}")
    @ResponseBody
    public ResponseEntity<Pacote> cria_na_agencia(@PathVariable("id") int id, @RequestBody JSONObject json) {
        Agencia agencia = agencia_service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            if (isJSONValid(json.toString())) {
                Pacote pacote = new Pacote();
                parse(pacote, json);
                pacote.setAgencia(agencia);
                service.salvar(pacote);
                return ResponseEntity.ok(pacote);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/pacotes/{id}")
    public ResponseEntity<Pacote> atualiza(@PathVariable("id") int id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Pacote pacote = service.buscarPorId(id);
                if (pacote == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(pacote, json);
                    service.salvar(pacote);
                    return ResponseEntity.ok(pacote);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping(path = "/pacotes/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") int id) {

        Pacote pacote = service.buscarPorId(id);
        if (pacote == null) {
            return ResponseEntity.notFound().build();
        } else {
                service.excluir(id);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }
}
