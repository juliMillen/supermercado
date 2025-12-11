package com.jm.supermercado.Service;

import com.jm.supermercado.DTO.DetalleVentaDTO;
import com.jm.supermercado.DTO.VentaDTO;
import com.jm.supermercado.Entity.DetalleVenta;
import com.jm.supermercado.Entity.Producto;
import com.jm.supermercado.Entity.Sucursal;
import com.jm.supermercado.Entity.Venta;
import com.jm.supermercado.Exception.ProductoException;
import com.jm.supermercado.Exception.SucursalException;
import com.jm.supermercado.Exception.VentaException;
import com.jm.supermercado.Mapper.Mapper;
import com.jm.supermercado.Repository.IProductoRepository;
import com.jm.supermercado.Repository.ISucursalRepository;
import com.jm.supermercado.Repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    public IVentaRepository ventaRepository;

    @Autowired
    public IProductoRepository productoRepository;

    @Autowired
    public ISucursalRepository sucursalRepository;

    public List<VentaDTO> listarVentas(){

        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();
        VentaDTO ventaDTO;
        for (Venta vent : ventas) {
            ventaDTO = Mapper.mapToDTO(vent);
            ventasDTO.add(ventaDTO);
        }
        return ventasDTO;
    }

    public VentaDTO crearVenta(VentaDTO ventaDTO){
        if(ventaDTO == null){
            throw new VentaException("No se puede crear una venta vacia");
        }
        if(ventaDTO.getIdSucursal() == null){
            throw new VentaException("No se puede crear una venta vacia");
        }
        if(ventaDTO.getDetalles() == null || ventaDTO.getDetalles().isEmpty()){
            throw new VentaException("No se puede crear una venta vacia");
        }

        //buscar sucursal
        Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElseThrow(() ->new SucursalException("Sucursal no encontrado"));
        sucursal.setIdSucursal(ventaDTO.getIdSucursal());


        //crear la venta
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setTotal(ventaDTO.getTotal());
        venta.setSucursal(sucursal);

        //lista de detalles

        List<DetalleVenta> detalles = new ArrayList<>();

        for(DetalleVentaDTO detDTO : ventaDTO.getDetalles()){
            Producto prod = productoRepository.findByNombre(detDTO.getNombreProd()).orElseThrow(() ->new ProductoException("Producto no encontrado"));

            //Crear detalle venta
            DetalleVenta detalleV = new DetalleVenta();
            detalleV.setProducto(prod);
            detalleV.setPrecioUnitario(detDTO.getPrecio());
            detalleV.setCantidad(detDTO.getCantProd());
            detalleV.setVenta(venta);
            detalles.add(detalleV);
        }

        //setear lista de detalle venta
        venta.setDetalleVenta(detalles);

        //guardar en la BD

        ventaRepository.save(venta);

        //mapeo para mostrar la salida

        VentaDTO dto = Mapper.mapToDTO(venta);
        return dto;




    }

    public VentaDTO ModificarVenta(Long id,VentaDTO ventaDTO) {
        Venta buscada = ventaRepository.findById(id).orElseThrow(() -> new VentaException("Venta no encontrada"));

        if (ventaDTO.getFecha() != null && ventaDTO.getEstado() != null && ventaDTO.getTotal() > 0 && ventaDTO.getIdSucursal() != null) {
            buscada.setFecha(ventaDTO.getFecha());
            buscada.setEstado(ventaDTO.getEstado());
            buscada.setTotal(ventaDTO.getTotal());

            Sucursal suc = sucursalRepository.findById(ventaDTO.getIdVenta()).orElseThrow(() -> new SucursalException("Sucursal no encontrada"));
            buscada.setSucursal(suc);
        }

        //guardo en la BD
       ventaRepository.save(buscada);

       //mapeo para mostrar salida

        VentaDTO dto = Mapper.mapToDTO(buscada);
        return dto;
    }

    public void eliminarVenta(Long idVenta){

        Venta aEliminar = ventaRepository.findById(idVenta).orElseThrow(() -> new VentaException("Venta no encontrada"));
        ventaRepository.delete(aEliminar);
    }
}
