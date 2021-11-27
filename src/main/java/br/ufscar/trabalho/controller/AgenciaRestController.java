package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Agencia;
import br.ufscar.trabalho.service.spec.IAgenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class AgenciaRestController {

    @Autowired
    private IAgenciaService service;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(Agencia agencia, JSONObject json) {

        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                agencia.setId((Integer) id);
            }
        }

        agencia.setCnpj((String) json.get("cnpj"));
        agencia.setNome((String) json.get("nome"));
    }

    @GetMapping(path = "/agencias")
    public ResponseEntity<List<Agencia>> lista() {
        List<Agencia> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/agencias/{id}")
    public ResponseEntity<Agencia> lista(@PathVariable("id") int id) {
        Agencia agencia = service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agencia);
    }

    @PostMapping(path = "/agencias")
    @ResponseBody
    public ResponseEntity<Agencia> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Agencia agencia = new Agencia();
                parse(agencia, json);
                service.salvar(agencia);
                return ResponseEntity.ok(agencia);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/agencias/{id}")
    public ResponseEntity<Agencia> atualiza(@PathVariable("id") int id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Agencia agencia = service.buscarPorId(id);
                if (agencia == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(agencia, json);
                    service.salvar(agencia);
                    return ResponseEntity.ok(agencia);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping(path = "/agencias/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") int id) {

        Agencia agencia = service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (service.agenciaTemPacotes(id)) {
                return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
            } else {
                service.excluir(id);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
        }
    }
}
