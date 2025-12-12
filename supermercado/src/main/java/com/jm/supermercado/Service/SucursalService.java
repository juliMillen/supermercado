package com.jm.supermercado.Service;

import com.jm.supermercado.DTO.SucursalDTO;
import com.jm.supermercado.Entity.Sucursal;
import com.jm.supermercado.Exception.SucursalException;
import com.jm.supermercado.Mapper.Mapper;
import com.jm.supermercado.Repository.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private ISucursalRepository sucursalRepository;


    public List<SucursalDTO> obtenerSucursales(){
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::mapToDTO)
                .toList();
    }


    public SucursalDTO CrearSucursal(SucursalDTO dto){
        Sucursal sucursal = Sucursal.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .build();
        return Mapper.mapToDTO(sucursalRepository.save(sucursal));

    }

    public SucursalDTO ModificarSucursal(Long id,SucursalDTO dto){
        Sucursal aModificar = sucursalRepository.findById(id).orElseThrow(() -> new SucursalException("Sucursal no encontrada"));
        aModificar.setNombre(dto.getNombre());
        aModificar.setDireccion(dto.getDireccion());
        return Mapper.mapToDTO(sucursalRepository.save(aModificar));
    }

    public void EliminarSucursal(Long idSucursal){
        Sucursal aEliminar = sucursalRepository.findById(idSucursal).orElseThrow(() -> new SucursalException("Sucursal no encontrada"));
        sucursalRepository.delete(aEliminar);
    }
}
