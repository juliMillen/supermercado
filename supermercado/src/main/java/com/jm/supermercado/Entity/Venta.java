package com.jm.supermercado.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private LocalDate fecha;

    private String estado;

    private double total;

    @ManyToOne
    private Sucursal sucursal;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalleVenta = new ArrayList<>();
}
