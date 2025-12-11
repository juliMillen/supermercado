package com.jm.supermercado.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    private int cantidad;

    private double precioUnitario;

    //Venta
    @ManyToOne
    private Venta venta;

    //Producto
    @ManyToOne
    private Producto producto;
}
