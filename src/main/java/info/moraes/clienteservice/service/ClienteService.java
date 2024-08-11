package info.moraes.clienteservice.service;

import info.moraes.clienteservice.model.Cliente;
import info.moraes.clienteservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
    public Optional<Cliente> findById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente save (Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public void deleteById(int id){
        clienteRepository.deleteById(id);
    }




}
