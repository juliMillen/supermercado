package com.jm.supermercado.Controller;

import com.jm.supermercado.DTO.VentaDTO;
import com.jm.supermercado.Entity.Venta;
import com.jm.supermercado.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("")
    public ResponseEntity<List<VentaDTO>> obtenerVentas() {
        List<VentaDTO> listaDTO = ventaService.listarVentas();
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto){
        VentaDTO nuevaVenta = ventaService.crearVenta(dto);
        return new ResponseEntity<>(nuevaVenta, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO dto){
        VentaDTO aActualizar = ventaService.ModificarVenta(id, dto);
        return new ResponseEntity<>(aActualizar, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>("Venta Eliminada Correctamente", HttpStatus.OK);
    }
}
