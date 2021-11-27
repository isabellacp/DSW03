package br.ufscar.trabalho.controller;

import br.ufscar.trabalho.domain.Cliente;
import br.ufscar.trabalho.service.spec.IClienteService;
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
public class ClienteRestController {

    @Autowired
    private IClienteService service;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(Cliente agencia, JSONObject json) {

        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                agencia.setId((Integer) id);
            }
            if (id instanceof String) {
                agencia.setId(Integer.parseInt((String) id));
            }
        }

        agencia.setCpf((String) json.get("cpf"));
        agencia.setNascimento((String) json.get("nascimento"));
        agencia.setSexo((String) json.get("sexo"));
        agencia.setTelefone((String) json.get("telefone"));
    }

    @GetMapping(path = "/clientes")
    public ResponseEntity<List<Cliente>> lista() {
        List<Cliente> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/clientes/{id}")
    public ResponseEntity<Cliente> lista(@PathVariable("id") int id) {
        Cliente agencia = service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agencia);
    }

    @PostMapping(path = "/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Cliente agencia = new Cliente();
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

    @PutMapping(path = "/clientes/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable("id") int id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Cliente agencia = service.buscarPorId(id);
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

    @DeleteMapping(path = "/clientes/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") int id) {

        Cliente agencia = service.buscarPorId(id);
        if (agencia == null) {
            return ResponseEntity.notFound().build();
        } else {
                service.excluir(id);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }
}
