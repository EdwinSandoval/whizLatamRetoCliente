package com.example.cliente.service;


import com.example.cliente.dto.request.ClienteRequestDto;
import com.example.cliente.dto.response.ClienteResponseDto;
import com.example.cliente.entity.Cliente;
import com.example.cliente.exceptions.exception.ExisteNombre;
import com.example.cliente.exceptions.exception.NoExisteCliente;
import com.example.cliente.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    @Autowired()
    private IClienteRepository iClienteRepository;
    @Override
    public List<ClienteResponseDto> listAll() {
        return this.iClienteRepository.findAll().stream()
                .map(p -> {
                    ClienteResponseDto clienteResponseDto = new ClienteResponseDto();
                    clienteResponseDto.setId(p.getId());
                    clienteResponseDto.setNombres(p.getNombres());
                    clienteResponseDto.setApellidoPaterno(p.getApellidoPaterno());
                    clienteResponseDto.setApellidoMaterno(p.getApellidoMaterno());
                    clienteResponseDto.setFechaNacimiento(p.getFechaNacimiento());
                    clienteResponseDto.setSexo(p.getSexo());
                    clienteResponseDto.setCorreo(p.getCorreo());
                    clienteResponseDto.setTelefono(p.getTelefono());

                    return clienteResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDto save(ClienteRequestDto request) {
        Optional<Cliente> encontrado = iClienteRepository.findByNombres(request.getNombres());
        if (encontrado.isPresent()) {
            throw new ExisteNombre("Ya existe el cliente con ese Nombre");
        }else {

            Cliente cliente = new Cliente();
            cliente.setNombres(request.getNombres());
            cliente.setApellidoPaterno(request.getApellidoPaterno());
            cliente.setApellidoMaterno(request.getApellidoMaterno());
            cliente.setFechaNacimiento(request.getFechaNacimiento());
            cliente.setSexo(request.getSexo());
            cliente.setCorreo(request.getCorreo());
            cliente.setTelefono(request.getTelefono());

            this.iClienteRepository.save(cliente);

            ClienteResponseDto response = new ClienteResponseDto();
            response.setId(cliente.getId());
            response.setNombres(request.getNombres());
            response.setApellidoPaterno(request.getApellidoPaterno());
            response.setApellidoMaterno(request.getApellidoMaterno());
            response.setFechaNacimiento(request.getFechaNacimiento());
            response.setSexo(request.getSexo());
            response.setCorreo(request.getCorreo());
            response.setTelefono(request.getTelefono());
            return response;
        }

    }

    @Override
    public ClienteResponseDto update(ClienteRequestDto request, Integer id) {
        Optional<Cliente> encontrado = iClienteRepository.findById(id);
        if (encontrado.isPresent()) {
            Cliente clienteExiste = encontrado.get();
            clienteExiste.setNombres(request.getNombres());
            clienteExiste.setApellidoPaterno(request.getApellidoPaterno());
            clienteExiste.setApellidoMaterno(request.getApellidoMaterno());
            clienteExiste.setFechaNacimiento(request.getFechaNacimiento());
            clienteExiste.setSexo(request.getSexo());
            clienteExiste.setCorreo(request.getCorreo());
            clienteExiste.setTelefono(request.getTelefono());
            this.iClienteRepository.save(clienteExiste);

            ClienteResponseDto response = new ClienteResponseDto();
            response.setId(encontrado.get().getId());
            response.setNombres(request.getNombres());
            response.setApellidoPaterno(request.getApellidoPaterno());
            response.setApellidoMaterno(request.getApellidoMaterno());
            response.setFechaNacimiento(request.getFechaNacimiento());
            response.setSexo(request.getSexo());
            response.setCorreo(request.getCorreo());
            response.setTelefono(request.getTelefono());
            return response;
        }else{
            throw new NoExisteCliente("No existe cliente con ese Id");
        }

    }

    @Override
    public String eliminar(Integer id) {
        Optional<Cliente> encontrado = iClienteRepository.findById(id);
        if (encontrado.isPresent()) {
            iClienteRepository.deleteById(id);
            return "Usuario eliminado correctamente";
        }
        return "Usuario no se encuentra registrado";
    }

    @Override
    public ClienteResponseDto getClienteById(Integer id) {
        ClienteResponseDto responseDto=new ClienteResponseDto();
        Optional<Cliente> cliente=iClienteRepository.findById(id);
        if (cliente.isPresent()) {
            responseDto.setId(cliente.get().getId());
            responseDto.setNombres(cliente.get().getNombres());
            responseDto.setApellidoPaterno(cliente.get().getApellidoPaterno());
            responseDto.setApellidoMaterno(cliente.get().getApellidoMaterno());
            responseDto.setFechaNacimiento(cliente.get().getFechaNacimiento());
            responseDto.setSexo(cliente.get().getSexo());
            responseDto.setCorreo(cliente.get().getCorreo());
            responseDto.setTelefono(cliente.get().getTelefono());
            return responseDto;
        }else {
            throw new NoExisteCliente("No existe cliente con ese Id");
        }


    }

}
