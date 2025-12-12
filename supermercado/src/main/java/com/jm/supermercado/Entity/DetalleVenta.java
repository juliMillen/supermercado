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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ventaID")
    private Venta venta;

    //Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoID")
    private Producto producto;
}
