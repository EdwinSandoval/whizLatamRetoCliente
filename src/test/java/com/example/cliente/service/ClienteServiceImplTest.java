package com.example.cliente.service;

import com.example.cliente.builders.ClienteModelBuilder;
import com.example.cliente.dto.request.ClienteRequestDto;
import com.example.cliente.dto.response.ClienteResponseDto;
import com.example.cliente.entity.Cliente;
import com.example.cliente.exceptions.exception.ExisteNombre;
import com.example.cliente.exceptions.exception.NoExisteCliente;
import com.example.cliente.repository.IClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private IClienteRepository iClienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombres("Juan");
        cliente.setApellidoPaterno("Pérez");
        cliente.setApellidoMaterno("Gómez");
        cliente.setFechaNacimiento(new Date());
        cliente.setSexo("Masculino");
        cliente.setCorreo("juan@example.com");
        cliente.setTelefono("123456789");

    }

    @Test
    public void testListAll() {
        List<Cliente> esperado= ClienteModelBuilder.getAll();
        when(iClienteRepository.findAll()).thenReturn(esperado);
        List<ClienteResponseDto> resultado = clienteService.listAll();
        assertEquals(3,resultado.size());
        assertEquals("Edwin", resultado.get(0).getNombres());
        assertEquals("Freddy", resultado.get(1).getNombres());

    }

    @Test
    public void testSave() {
        ClienteRequestDto request = new ClienteRequestDto();
        request.setNombres("Juan");
        request.setApellidoPaterno("Pérez");
        request.setApellidoMaterno("Gómez");
        request.setFechaNacimiento(new Date());
        request.setSexo("Masculino");
        request.setCorreo("juan@example.com");
        request.setTelefono("123456789");

        when(iClienteRepository.findByNombres("Juan")).thenReturn(Optional.empty());
        when(iClienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDto response = clienteService.save(request);

        assertEquals("Juan", response.getNombres());
        verify(iClienteRepository).save(any(Cliente.class));
    }

    @Test
    public void testUpdate() {
        ClienteRequestDto request = new ClienteRequestDto();
        request.setNombres("Juanito");
        request.setApellidoPaterno("Pérez");
        request.setApellidoMaterno("Gómez");

        when(iClienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(iClienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDto response = clienteService.update(request, 1);

        assertEquals("Juanito", response.getNombres());
        verify(iClienteRepository).save(cliente);
    }

    @Test
    public void testEliminar() {
        when(iClienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        String result = clienteService.eliminar(1);

        assertEquals("Usuario eliminado correctamente", result);
        verify(iClienteRepository).deleteById(1);
    }

    @Test
    public void testGetClienteById() {
        when(iClienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteResponseDto response = clienteService.getClienteById(1);

        assertEquals("Juan", response.getNombres());
    }

    @Test
    public void testGetClienteById_NoExistente() {
        when(iClienteRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(NoExisteCliente.class, () -> {
            clienteService.getClienteById(999);
        });
    }

    @Test
    public void testSave_ClienteExistente() {
        ClienteRequestDto request = new ClienteRequestDto();
        request.setNombres("Juan");

        when(iClienteRepository.findByNombres("Juan")).thenReturn(Optional.of(cliente));

        assertThrows(ExisteNombre.class, () -> {
            clienteService.save(request);
        });
    }
}