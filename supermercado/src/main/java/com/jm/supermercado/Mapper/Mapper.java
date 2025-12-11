package com.jm.supermercado.Mapper;

import com.jm.supermercado.DTO.DetalleVentaDTO;
import com.jm.supermercado.DTO.ProductoDTO;
import com.jm.supermercado.DTO.SucursalDTO;
import com.jm.supermercado.DTO.VentaDTO;
import com.jm.supermercado.Entity.Producto;
import com.jm.supermercado.Entity.Sucursal;
import com.jm.supermercado.Entity.Venta;
import com.jm.supermercado.Exception.ProductoException;
import com.jm.supermercado.Exception.SucursalException;
import com.jm.supermercado.Exception.VentaException;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Producto a ProductoDTO

     static public ProductoDTO mapToDTO(Producto producto){
       if(producto == null){
           throw new ProductoException("El producto no puede ser nulo");
       }
       return ProductoDTO.builder()
               .idProducto(producto.getIdProducto())
               .nombre(producto.getNombre())
               .categoria(producto.getCategoria())
               .precioUnitario(producto.getPrecioUnidad())
               .build();

    }

    //Mapeo de Venta a VentaDTO

    static public VentaDTO mapToDTO(Venta venta){
         if(venta == null){
             throw new VentaException("La venta no puede ser nulo");
         }

         var detalle = venta.getDetalleVenta().stream().map(det ->
                 DetalleVentaDTO.builder()
                         .idDetalleVenta(det.getProducto().getIdProducto())
                         .nombreProd(det.getProducto().getNombre())
                         .precio(det.getProducto().getPrecioUnidad())
                         .cantProd(det.getProducto().getCantidad())
                         .subtotal(det.getPrecioUnitario() * det.getCantidad())
                         .build()
         ).collect(Collectors.toList());

         var total = detalle.stream()
                 .map(DetalleVentaDTO::getSubtotal)
                 .reduce(0.0, Double::sum);

         return VentaDTO.builder()
                 .idVenta(venta.getIdVenta())
                 .fecha(venta.getFecha())
                 .idSucursal(venta.getSucursal().getIdSucursal())
                 .estado(venta.getEstado())
                 .detalles(detalle)
                 .total(total)
                 .build();
    }

    //Mapeo de Sucursal a SucursalDTO

    static public SucursalDTO mapToDTO(Sucursal sucursal){
         if(sucursal == null){
             throw new SucursalException("La sucursal no puede ser nulo");
         }
         return SucursalDTO.builder()
                 .idSucursal(sucursal.getIdSucursal())
                 .nombre(sucursal.getNombre())
                 .direccion(sucursal.getDireccion())
                 .build();
    }

}
