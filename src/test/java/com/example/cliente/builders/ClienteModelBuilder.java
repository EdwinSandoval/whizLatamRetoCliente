package com.example.cliente.builders;

import com.example.cliente.dto.response.ClienteResponseDto;
import com.example.cliente.entity.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class ClienteModelBuilder {

    public static Cliente getClienteModel(){
        Cliente model = new Cliente();
        model.setId(1);
        model.setNombres("Juan");
        model.setApellidoPaterno("Pérez");
        model.setApellidoMaterno("Gómez");
        model.setFechaNacimiento(new Date());
        model.setSexo("Masculino");
        model.setCorreo("juan@example.com");
        model.setTelefono("123456789");

        return model;
    }
    public static ClienteResponseDto getClienteResponse(){
        ClienteResponseDto model = new ClienteResponseDto();
        model.setId(1);
        model.setNombres("Wilmer");
        model.setApellidoPaterno("Silva");
        model.setApellidoMaterno("Silva");
        model.setFechaNacimiento(new Date());
        model.setSexo("Masculino");
        model.setCorreo("juan@example.com");
        model.setTelefono("123456789");
        return model;
    }

    public static List<Cliente> getAll(){
        Cliente re=new Cliente(1,"Edwin","Sandoval","Rivas",new Date("07/12/2005"),"Masculino","del@gmail.com","985623");
        Cliente re1=new Cliente(2,"Freddy","Sandoval","Rivas",new Date("05/12/2007"),"Masculino","del@gmail.com","985623");
        Cliente re2=new Cliente(3,"Jose","Sandoval","Rivas",new Date("08/12/2009"),"Masculino","del@gmail.com","985623");
        List<Cliente> lista=new ArrayList<>();
        Stream.of(re,re1,re2)
                .forEach(lista::add);

        return lista;
    }

}
