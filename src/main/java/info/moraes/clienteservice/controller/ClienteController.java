package info.moraes.clienteservice.controller;


import info.moraes.clienteservice.model.Cliente;
import info.moraes.clienteservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // Novo endpoint para status
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("O serviço está ativo - Instância 1");
    }

    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteService.findAll();
    }

    //@GetMapping("/{id}")
    //public ResponseEntity<Cliente> getClienteById(@PathVariable int id){
    //    Optional<Cliente> cliente = clienteService.findById(id);
    //    return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
    //}

    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable int id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if(clienteOptional.isPresent()){
            Cliente clienteToUpdate = clienteOptional.get();
            clienteToUpdate.setNome(cliente.getNome());
            clienteToUpdate.setCidade(cliente.getCidade());
            clienteToUpdate.setPais(cliente.getPais());
            Cliente clienteUpdated = clienteService.save(clienteToUpdate);
            return ResponseEntity.ok(clienteUpdated);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable int id){
        if(clienteService.findById(id).isPresent()){
            clienteService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
