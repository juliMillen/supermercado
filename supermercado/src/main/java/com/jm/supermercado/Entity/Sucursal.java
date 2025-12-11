package com.jm.supermercado.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSucursal;

    private String nombre;

    private String direccion;

    @OneToMany(mappedBy = "sucursal")
    private List<Venta> listaVenta = new ArrayList<>();
}
