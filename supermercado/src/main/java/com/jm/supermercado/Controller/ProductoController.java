package com.jm.supermercado.Controller;

import com.jm.supermercado.DTO.ProductoDTO;
import com.jm.supermercado.Entity.Producto;
import com.jm.supermercado.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        List<ProductoDTO> listaDTO = productoService.obtenerTodos();
        return ResponseEntity.ok().body(listaDTO);
    }


    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO dto){

        ProductoDTO productoDTO = productoService.crearProducto(dto);
        return new ResponseEntity<>(productoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id,@RequestBody ProductoDTO dto){
        ProductoDTO aModificar = productoService.actualizarProducto(id, dto);
        return ResponseEntity.ok().body(aModificar);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>("Producto Eliminado Correctamente", HttpStatus.OK);
    }
}
