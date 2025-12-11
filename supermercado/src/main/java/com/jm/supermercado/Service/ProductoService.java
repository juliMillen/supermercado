package com.jm.supermercado.Service;

import com.jm.supermercado.DTO.ProductoDTO;
import com.jm.supermercado.Entity.Producto;
import com.jm.supermercado.Exception.ProductoException;
import com.jm.supermercado.Mapper.Mapper;
import com.jm.supermercado.Repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    public List<ProductoDTO> obtenerTodos(){
        return productoRepository.findAll().stream()
                .map(Mapper::mapToDTO).toList();
    }

    public ProductoDTO crearProducto(ProductoDTO productoDTO){
        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precioUnidad(productoDTO.getPrecioUnitario())
                .cantidad(productoDTO.getCantidad())
                .build();
        return Mapper.mapToDTO(productoRepository.save(producto));
    }

    public ProductoDTO actualizarProducto(Long idProducto,ProductoDTO productoDTO){
        //Buscar si existe el producto
        Producto buscado = productoRepository.findById(idProducto).orElseThrow( () -> new ProductoException("Producto no encontrado"));
        buscado.setNombre(productoDTO.getNombre());
        buscado.setPrecioUnidad(productoDTO.getPrecioUnitario());
        buscado.setCategoria(productoDTO.getCategoria());
        buscado.setCantidad(productoDTO.getCantidad());
        return Mapper.mapToDTO(productoRepository.save(buscado));

    }

    public void eliminarProducto(Long idProducto){
        Producto aEliminar = productoRepository.findById(idProducto).orElseThrow(() -> new ProductoException("Producto no encontrado"));
        productoRepository.delete(aEliminar);
        System.out.println("Producto eliminado correctamente");
    }
}
