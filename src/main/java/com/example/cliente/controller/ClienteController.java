package com.example.cliente.controller;


import com.example.cliente.dto.request.ClienteRequestDto;
import com.example.cliente.dto.response.ClienteResponseDto;
import com.example.cliente.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDto>> listAll(){
        return  ResponseEntity.ok(clienteService.listAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ClienteResponseDto> buscarCliente(@PathVariable(name = "id") Integer idCliente){
        return new ResponseEntity<>(this.clienteService.getClienteById(idCliente), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestBody ClienteRequestDto request){
        return new ResponseEntity<>(this.clienteService.save(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public String delete(@PathVariable("id") Integer id){
        return clienteService.eliminar(id);
    }

    @PutMapping("/actualizar/{id}")
    public ClienteResponseDto actualizar(@RequestBody ClienteRequestDto body, @PathVariable("id") Integer idUsuario) {
        return clienteService.update(body, idUsuario);
    }
}
