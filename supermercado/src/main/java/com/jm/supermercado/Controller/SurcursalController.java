package com.jm.supermercado.Controller;

import com.jm.supermercado.DTO.SucursalDTO;
import com.jm.supermercado.Entity.Sucursal;
import com.jm.supermercado.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
public class SurcursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping("")
    public ResponseEntity<List<SucursalDTO>> obtenerSucursales(){
        List<SucursalDTO> sucursalDTOs = sucursalService.obtenerSucursales();
        return new ResponseEntity<>(sucursalDTOs, HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO){
        SucursalDTO nueva = sucursalService.CrearSucursal(sucursalDTO);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalDTO sucursalDTO){
        SucursalDTO aActualizar = sucursalService.ModificarSucursal(id, sucursalDTO);
        return new ResponseEntity<>(aActualizar, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable Long id){
        sucursalService.EliminarSucursal(id);
        return new ResponseEntity<>("Sucursal Eliminada Correctamente", HttpStatus.OK);
    }
}
