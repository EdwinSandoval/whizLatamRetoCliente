package com.example.cliente.service;


import com.example.cliente.dto.request.ClienteRequestDto;
import com.example.cliente.dto.response.ClienteResponseDto;

import java.util.List;

public interface IClienteService {
    List<ClienteResponseDto> listAll();

    ClienteResponseDto save(ClienteRequestDto request);

    ClienteResponseDto update(ClienteRequestDto request, Integer id);

    String eliminar(Integer id);
    ClienteResponseDto getClienteById(Integer id);

//    List<ClienteResponseDto> listClienteNombre(String nombre);
}
